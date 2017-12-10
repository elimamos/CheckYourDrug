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
                    Log.d("SERVER","Created CST");

                    HttpURLConnection urlConnection = null;
                    String apiUrl = "http://env-8117487.unicloud.pl/CheckDrug" ;
                    URL requestUrl = new URL(apiUrl);
                    urlConnection = (HttpURLConnection) requestUrl.openConnection();

                  nn  
                    try {
                        response = doPost(urlConnection, correctName);
                        responseDrug=manageResponseString(response);

                    }catch(Exception e){
                        e.printStackTrace();
                    }
                    urlConnection.disconnect();
                } catch (Exception e) {
                    e.printStackTrace();
                }




            }

        });
        t.start();
        try {
            t.join();

            return responseDrug;
        } catch (InterruptedException e) {
            e.printStackTrace();
            return new Drug("Error","",0.0,"");
        }


    }
    private String doPost(HttpURLConnection urlConnection,String myPostableString) throws Exception {


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
            String name=jsonElem.getString("name");
            String similar=jsonElem.getString("similar");
            String substance=jsonElem.getString("substance");
            Double price=jsonElem.getDouble("price");
            Drug myDrug = new Drug(name,similar,price,substance);
            return myDrug;

        }catch(Exception e){
            negativeFeedback=receivedString;

            Drug myDrug = new Drug(negativeFeedback,"",0.0,"");

            return myDrug;
        }

    }

    }

