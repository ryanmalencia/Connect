package com.ryanm.connect;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void toggle1(View view)
    {
        new RetrieveData().execute("toggle1");
    }
    public void toggle2(View view)
    {
        new RetrieveData().execute("toggle2");
    }
    public void turnon(View view)
    {
        new RetrieveData().execute("turnon");
    }
    public void turnoff(View view) {
        new RetrieveData().execute("turnoff");
    }

    private class RetrieveData extends AsyncTask<String, Void, String> {
        protected void onPreExecute() {
        }

        protected String doInBackground(String... api) {
            try{
                URL url = new URL("http://74.109.196.114:9000/api/" + api[0]);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("POST");
                urlConnection.connect();
                try{
                    BufferedReader br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                    StringBuilder sb = new StringBuilder();
                    String line;

                    while((line = br.readLine()) != null) {
                        sb.append(line);
                    }
                    br.close();
                    return sb.toString();
                }
                finally{
                    urlConnection.disconnect();
                }
            }
            catch (Exception e) {
                System.out.println("Error: " + e);
                return null;
            }
        }

        protected void onPostExecute(String response) {

        }
    }
}
