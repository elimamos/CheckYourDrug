package com.example.elisa.checkyourdrug;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class DrugInfo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drug_info);
        Bundle extras = getIntent().getExtras();
        String drugName=extras.getString("drugName");
        String drugSubstance=extras.getString("drugSubstance");
        String drugSimilar=extras.getString("drugSimilar");
        Double drugPrice= extras.getDouble("drugPrice");
        TextView tv1=(TextView)findViewById(R.id.drugName);
        TextView tv2=(TextView)findViewById(R.id.substance);
        TextView tv3=(TextView)findViewById(R.id.price);
        TextView tv4=(TextView)findViewById(R.id.similar);
        tv1.setText(drugName);
        tv2.setText(drugSubstance);
        tv3.setText(drugPrice.toString()+"zł");
        tv4.setText(drugSimilar);

    }

    public void backToMain(View view) {
        startActivity(new Intent(getApplicationContext(),MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
    }
}
