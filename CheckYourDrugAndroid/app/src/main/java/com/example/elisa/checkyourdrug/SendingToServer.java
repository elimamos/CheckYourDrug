package com.example.elisa.checkyourdrug;

import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Hashtable;
import java.util.Set;


public class SendingToServer extends AppCompatActivity {
private String  response;

    SendingToServer(){

    }
    Drug responseDrug;
    public Drug checkServerConnected(final String correctName) {
        responseDrug= new Drug("TEST","",0.0,"");
       Thread t= new Thread(new Runnable() {


            @Override
            public void run() {
                try {
                  //  CheckScannedText cst= new CheckScannedText();
                    Log.d("SERVER","Created CST");
                   // String sSelectedDrugName =cst.getName();
                 //   Log.d("SERVER",sSelectedDrugName);
                    HttpURLConnection urlConnection = null;
                    String apiUrl = "http://przetwarzaie.unicloud.pl/CheckDrug" ;// concatenate uri with base url eg: localhost:8080/ + uri
                    URL requestUrl = new URL(apiUrl);
                    urlConnection = (HttpURLConnection) requestUrl.openConnection();

                   // urlConnection.connect();
                 //  Log.d("SERVER","connected!");
                    try {
                        Log.d("SERVER",correctName);
                        response = doPost(urlConnection, correctName);
                        responseDrug=manageResponseString(response);
Log.d("SERVER",responseDrug.getDrugName());

                    }catch(Exception e){
                        e.printStackTrace();
                        Log.d("SERVER"," POST ERROR");
                    }
                    urlConnection.disconnect();
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.d("SERVER","ERROR");
                }



         /* runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        ///Toast.makeText(getApplicationContext(), "Connected to server",Toast.LENGTH_LONG).show();
                    //    Log.d("SERVER", "Connected to server");
                    }
                });*/

            }

        });
        t.start();
        try {
            t.join();
            Log.d("SERVER","OUter space "+responseDrug.getDrugName());
            return responseDrug;
        } catch (InterruptedException e) {
            e.printStackTrace();
            return new Drug("Error","",0.0,"");
        }


    }
    private String doPost(HttpURLConnection urlConnection,String myPostableString) throws Exception {

        //urlConnection.connect(); // no connection is made
        //  Log.d("SERVER","OK!!");
        urlConnection.setRequestMethod("POST");
        urlConnection.setDoOutput(true);
        Hashtable<String,String> params = new Hashtable<String, String>();
        params.put("name", myPostableString);
        OutputStream os = urlConnection.getOutputStream();
        BufferedWriter writer = new BufferedWriter(
                new OutputStreamWriter(os, "UTF-8"));
        writer.write(getQuery(params));
        writer.flush();
        writer.close();
        os.close();

        urlConnection.connect();
        BufferedReader reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
        StringBuilder sb = new StringBuilder();
        String line = null;

        while((line = reader.readLine()) != null)
        {
            sb.append(line + "\n");
        }
       //  Log.d("SERVER","RESPONSE:  "+sb.toString());
        return sb.toString();
    }
    private String getQuery(Hashtable<String,String> params) throws UnsupportedEncodingException
    {
        StringBuilder result = new StringBuilder();
        boolean first = true;
        Set<String> keys= params.keySet();
        for (String key : keys)
        {
            if (first)
                first = false;
            else
                result.append("&");

            result.append(URLEncoder.encode(key, "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(params.get(key), "UTF-8"));
        }

        return result.toString();
    }
    private Drug manageResponseString(String receivedString) {
        String negativeFeedback="";
        try {
            JSONObject jsonElem = new JSONObject(receivedString);
          //  Log.d("SERVER","Jsn element: "+ jsonElem.toString());
            String name=jsonElem.getString("name");
            String similar=jsonElem.getString("similar");
            String substance=jsonElem.getString("substance");
            Double price=jsonElem.getDouble("price");
            Drug myDrug = new Drug(name,similar,price,substance);
           Log.d("SERVER","Jsn element: "+ myDrug.getDrugPrice());
            return myDrug;

        }catch(Exception e){
            negativeFeedback=receivedString;
            Log.d("SERVER","Negative answer: "+ negativeFeedback);
            Drug myDrug = new Drug(negativeFeedback,"",0.0,"");

            return myDrug;
        }

    }

    }

