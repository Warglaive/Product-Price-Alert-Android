package com.services;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationManagerCompat;

import com.activities.MainActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.ProductPriceAlert.R;

import java.util.Objects;


public class PushNotificationService extends FirebaseMessagingService {
    private static final String TAG = MainActivity.class.getName();

    @Override
    public void onNewToken(@NonNull String token) {
        FirebaseMessaging.getInstance().getToken()
                .addOnCompleteListener(new OnCompleteListener<String>() {
                    @Override
                    public void onComplete(@NonNull Task<String> task) {
                        if (!task.isSuccessful()) {
                            Log.w(TAG, "Fetching FCM registration token failed", task.getException());
                            return;
                        }

                        // Get new FCM registration token
                        String token = task.getResult();

                        // Log and toast //IT WORKS, FUCK INTELLIJ, Just ignore the error...
                        String msg = getString(R.string.msg_token_fmt, token);
                        Log.d(TAG, msg);
                        //Needed activity
                        MainActivity testActivity = new MainActivity();
                        Toast.makeText(testActivity.getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
                    }
                });
    }

    /**
     * Display on foreground (not background)
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
}
