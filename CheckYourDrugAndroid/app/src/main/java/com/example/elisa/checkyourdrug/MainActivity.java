package com.example.elisa.checkyourdrug;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    private static final int MY_REQUEST_CODE =1 ;
     @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (checkSelfPermission(Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {

            requestPermissions(new String[]{Manifest.permission.CAMERA},
                    MY_REQUEST_CODE);

        }
         if (checkSelfPermission(Manifest.permission.INTERNET)
                 != PackageManager.PERMISSION_GRANTED) {

             requestPermissions(new String[]{Manifest.permission.INTERNET},
                     MY_REQUEST_CODE);

         }
    }

    public void openScanner(View view) {
        openNewActivity(scanner.class);
    }

    public void openInfo(View view) {
        openNewActivity(info.class);
    }
    public void openNewActivity(Class myclass){
        Intent i = new Intent(this, myclass);
        startActivity(i);
    }

}
