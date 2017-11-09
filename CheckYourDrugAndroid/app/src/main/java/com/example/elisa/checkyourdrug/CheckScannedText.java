package com.example.elisa.checkyourdrug;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;

import java.util.ArrayList;

public class CheckScannedText extends AppCompatActivity {
   private CheckBox[]mycheckboxes;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_scanned_text);
        ArrayList<String> tisstring=getIntent().getExtras().getStringArrayList("String");

     mycheckboxes =new CheckBox[]{(CheckBox)findViewById(R.id.checkBox1),(CheckBox)findViewById(R.id.checkBox2),(CheckBox)findViewById(R.id.checkBox3),(CheckBox)findViewById(R.id.checkBox4),(CheckBox)findViewById(R.id.checkBox5)};
        for(int i=0;i<5;i++){

            CheckBox chck= mycheckboxes[i];
            chck.setText(tisstring.get(i));
        }
    }

    public void back(View view) {
        Intent i = new Intent(this, scanner.class);
        startActivity(i);
    }

    public void continueWithText(View view) {
        String name="";
        for(CheckBox box:mycheckboxes){
            if(box.isChecked()){
                name+=" " +box.getText().toString();
            }
            name=name.trim();
        }
        Log.d("FINAL NAME",name);

        SendingToServer sts = new SendingToServer();
        sts.checkServerConnected();
    }
}
