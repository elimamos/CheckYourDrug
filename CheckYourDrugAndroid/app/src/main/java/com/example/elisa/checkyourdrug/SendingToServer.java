package com.example.elisa.checkyourdrug;

import android.support.v7.app.AppCompatActivity;
import android.util.Log;

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

    public void checkServerConnected() {
        new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    HttpURLConnection urlConnection = null;
                    String apiUrl = "http://przetwarzaie.unicloud.pl/CheckPassword" ;// concatenate uri with base url eg: localhost:8080/ + uri
                    URL requestUrl = new URL(apiUrl);
                    urlConnection = (HttpURLConnection) requestUrl.openConnection();
                    urlConnection.connect();
                    Log.d("SERVER","connected!");
                  // String response= doPost(urlConnection, " SOME STRING");
                    urlConnection.disconnect();
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.d("SERVER","ERROR");
                }

                runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        ///Toast.makeText(getApplicationContext(), "Connected to server",Toast.LENGTH_LONG).show();
                    //    Log.d("SERVER", "Connected to server");
                    }
                });
            }
        }).start();

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
        Log.d("SERVER","POST Response "+sb.toString());
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
    }

