package com.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.productpricealert.R;
import com.vogella.retrofitgerrit.Controller;
import com.vogella.retrofitgerrit.UserData;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    private void getAllUsers() {
        //TODO: add annotations if buggy
        Controller userController = new Controller();
        //
        retrofit2.Call<List<UserData>> call = userController.getAllUsers();

        call.enqueue(new Callback<List<UserData>>() {
            @Override
            public void onResponse(retrofit2.Call<List<UserData>> call, Response<List<UserData>> response) {
                List<UserData> listResult = response.body();
                String[] users = new String[listResult.size()];

                for (int i = 0; i < listResult.size(); i++) {
                    users[i] = listResult.get(i).getName();
                }

                //  superListView.setAdapter(new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, oneHeroes));
            }

            @Override
            public void onFailure(Call<List<UserData>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "An error has occured", Toast.LENGTH_LONG).show();
            }

        });
    }

}