package com.example.fouzia.weatherapp;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {


    String json_string;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void getJSON(View view) {

        new BackgroundTask().execute();
    }

        class BackgroundTask extends AsyncTask<Void,Void,String>
        {
            String json_url;
            String JSON_STRING;

            @Override
            protected void onPreExecute() {

                json_url="http://api.androidhive.info/contacts/";

            }

            @Override
            protected String doInBackground(Void... params) {
                try {
                    URL url=new URL(json_url);
                    HttpURLConnection httpURLConnection=(HttpURLConnection)url.openConnection();
                    InputStream inputStream=httpURLConnection.getInputStream();
                    BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inputStream));
                    StringBuilder stringBuilder=new StringBuilder();
                    while((JSON_STRING=bufferedReader.readLine())!=null)
                    {
                        stringBuilder.append(JSON_STRING+"\n");

                    }
                    bufferedReader.close();
                    inputStream.close();
                    httpURLConnection.disconnect();
                    return stringBuilder.toString().trim();

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onProgressUpdate(Void... values) {
                super.onProgressUpdate(values);
            }

            @Override
            protected void onPostExecute(String result) {
                super.onPostExecute(result);
                TextView textView=(TextView)findViewById(R.id.textView);
                textView.setText(result);
                json_string=result;

                }
        }


    public void parseJSON(View view)
    {
        if(json_string==null)
        {
            Toast.makeText(getApplicationContext(),"First get JSON",Toast.LENGTH_SHORT).show();
        }

        else
        {
            Intent intent= new Intent(this,DisplayListView.class);
            intent.putExtra("json_data",json_string);
            startActivity(intent);
        }



    }

}
