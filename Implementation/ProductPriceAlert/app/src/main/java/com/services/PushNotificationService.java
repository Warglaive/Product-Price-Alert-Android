package com.services;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.activities.MainActivity;
import com.activities.ProductDetailsCustomerActivity;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.ProductPriceAlert.R;
import com.google.gson.Gson;
import com.models.RequestNotification;
import com.models.SendNotificationModel;
import com.vogella.retrofitgerrit.RestClient;
import com.vogella.retrofitgerrit.interfaces.RestAPI;

import java.util.Objects;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class PushNotificationService extends FirebaseMessagingService {
    private static final String TAG = "MyFirebaseMsgService";
    public RestAPI restAPI;

    public PushNotificationService() {
    }

    /**
     * There are two scenarios when onNewToken is called:
     * 1) When a new token is generated on initial app startup
     * 2) Whenever an existing token is changed
     * Under #2, there are three scenarios when the existing token is changed:
     * A) App is restored to a new device
     * B) User uninstalls/reinstalls the app
     * C) User clears app data
     */
    @Override
    public void onNewToken(@NonNull String token) {
        FirebaseMessaging.getInstance().getToken()
                .addOnCompleteListener(task -> {
                    if (!task.isSuccessful()) {
                        Log.w(TAG, "Fetching FCM registration token failed", task.getException());
                        return;
                    }

                    // Get new FCM registration token
                    String token1 = task.getResult();

                    // Log and toast
                    String msg = String.format(String.valueOf(R.string.msg_token_fmt), token1);
                    Log.d(TAG, msg);
                    //  Toast.makeText(getBaseContext(ProductDetailsCustomerActivity.class), msg, Toast.LENGTH_SHORT).show();
                    //Send token to server
                    Log.d(TAG, "Refreshed token: " + token1);

                    // If you want to send messages to this application instance or
                    // manage this apps subscriptions on the server side, send the
                    // FCM registration token to your app server.
                    sendRegistrationToServer(token1);

                });
    }

    private void sendRegistrationToServer(String token) {
        Log.e(TAG, "sendRegistrationToServer: " + token);
        String notificationTitle = "Product price has dropped!";
        String notificationBody = "Check out our app for details!";
        //TODO: Check
        System.out.println(token);
        RequestNotification requestNotification = new RequestNotification();
        requestNotification.setToken(token);
        SendNotificationModel notificationModel = new SendNotificationModel(notificationBody, notificationTitle);
        requestNotification.setSendNotificationModel(notificationModel);
        this.restAPI = RestClient.getPushNotificationClient();
        //TODO: Call Retrofit enqueue
        //notificationModel as Gson
        Gson gson = new Gson();
        String jsonModel = gson.toJson(notificationModel);

        Call<ResponseBody> call = this.restAPI.sendChatNotification(jsonModel);
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                System.out.println("response");
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                System.out.println("fail");
            }
        });
    }

    /**
     * Display notification on foreground
     *
     * @param message
     */
    @Override
    public void onMessageReceived(@NonNull RemoteMessage message) {
        String title = Objects.requireNonNull(message.getNotification()).getTitle();
        String text = message.getNotification().getBody();
        final String CHANNEL_ID = "HEADS_UP_NOTIFICATION";
        final String channelName = "Heads up notification";

        NotificationChannel channel = new NotificationChannel(
                CHANNEL_ID,
                channelName,
                NotificationManager.IMPORTANCE_HIGH);
        getSystemService(NotificationManager.class).createNotificationChannel(channel);
        //Create a notification
        Notification.Builder notification =
                new Notification.Builder(this, CHANNEL_ID)
                        .setContentTitle(title)
                        .setContentText(text)
                        .setSmallIcon(R.drawable.ic_stat_ic_notification)
                        .setAutoCancel(false);
        NotificationManagerCompat.from(this).notify(1, notification.build());
        //
        super.onMessageReceived(message);
    }

    /**
     * Create and show a simple notification containing the received FCM message.
     *
     * @param messageBody FCM message body received.
     */
    private void sendNotification(String messageBody) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0 /* Request code */, intent,
                PendingIntent.FLAG_ONE_SHOT);

        String channelId = getString(R.string.default_notification_channel_id);
        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder =
                new NotificationCompat.Builder(this, channelId)
                        .setSmallIcon(R.drawable.ic_stat_ic_notification)
                        .setContentTitle(getString(R.string.fcm_message))
                        .setContentText(messageBody)
                        .setAutoCancel(true)
                        .setSound(defaultSoundUri)
                        .setContentIntent(pendingIntent);

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        // Since android Oreo notification channel is needed.
        NotificationChannel channel = new NotificationChannel(channelId,
                "Channel human readable title",
                NotificationManager.IMPORTANCE_DEFAULT);
        notificationManager.createNotificationChannel(channel);

        notificationManager.notify(0 /* ID of notification */, notificationBuilder.build());
    }
}
