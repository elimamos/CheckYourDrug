package com.example.elisa.checkyourdrug;

import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;
import android.util.*;
import java.net.*;

public class SendingToServer extends AppCompatActivity {

    public void checkServerConnected() {
        new Thread(new Runnable() {

            @Override
            public void run() {
                try{
                    URL myUrl = new URL("http://google.com/");
                    URLConnection connection = myUrl.openConnection();
                    connection.setConnectTimeout(2000);
                    connection.connect();
                } catch (Exception e) {
                    Log.e("--","--checkServerConnected NO-- ");
                }
                runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(), "Connected to server",Toast.LENGTH_LONG).show();
                    }
                });
            }
        }).start();

    }
}

