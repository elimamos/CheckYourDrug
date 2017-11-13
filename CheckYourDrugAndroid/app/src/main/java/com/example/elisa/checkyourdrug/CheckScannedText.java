package com.example.elisa.checkyourdrug;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;
import android.widget.Toast;

import java.util.ArrayList;

public class CheckScannedText extends AppCompatActivity {
   private RadioButton[]mycheckboxes;
    private String correctName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_scanned_text);
        ArrayList<String> tisstring=getIntent().getExtras().getStringArrayList("String");

     mycheckboxes =new RadioButton[]{(RadioButton)findViewById(R.id.checkBox1),(RadioButton)findViewById(R.id.checkBox2),(RadioButton)findViewById(R.id.checkBox3),(RadioButton)findViewById(R.id.checkBox4),(RadioButton)findViewById(R.id.checkBox5)};
        for(int i=0;i<5;i++){

            RadioButton chck= mycheckboxes[i];

            chck.setText(tisstring.get(i));
        }
    }

    public void back(View view) {
        Intent i = new Intent(this, scanner.class);
        startActivity(i);
    }

    public void continueWithText(View view) {
        String name="";
        for(RadioButton box:mycheckboxes){
            if(box.isChecked()){
                name+=" " +box.getText().toString();

            }
            name=name.trim();
        }
        correctName = "";
        if(name!="") {
            name = name.toLowerCase();
            correctName = name.substring(0, 1).toUpperCase();
            correctName += name.substring(1);
            Log.d("SERVER",correctName);

            SendingToServer sts = new SendingToServer();


           Drug myDrug =  sts.checkServerConnected(correctName);
               //    Log.d("SERVER","Response drug name "+myDrug.getDrugName());
            if(myDrug.getDrugSubstance().equals("")) {
                Log.d("SERVER", "Drug substance zero");

                AlertDialog alertDialog = new AlertDialog.Builder(this).create();
                alertDialog.setTitle("Missing Drug!");
                alertDialog.setMessage(myDrug.getDrugName() + "We're working at it! Futher information about your drug should be updated soon!");
                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                startActivity(new Intent(getApplicationContext(),MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
                            }
                        });
                alertDialog.show();
            }
            else{
                Log.d("SERVER","substance not zero");
                Intent i = new Intent(this, DrugInfo.class);
                i.putExtra("drugName",myDrug.getDrugName());
                i.putExtra("drugSubstance",myDrug.getDrugSubstance());
                i.putExtra("drugSimilar",myDrug.getDrugSimilar());
                i.putExtra("drugPrice",myDrug.getDrugPrice());
                startActivity(i);
            }
        }
        else{
            Toast.makeText(getBaseContext(),"Please select a name or retry by pressing 'back'!",Toast.LENGTH_LONG).show();
        }
      //  Log.d("SERVER",correctName);



    }
    String getName(){
        return correctName;
    }
}
