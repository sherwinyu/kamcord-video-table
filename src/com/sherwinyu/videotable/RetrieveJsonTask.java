package com.sherwinyu.videotable;


import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;

import org.apache.http.impl.client.DefaultHttpClient;

import org.apache.http.params.BasicHttpParams;

import android.os.AsyncTask;

import android.util.Log;

public class RetrieveJsonTask extends AsyncTask<String, Void, String> {

  protected String doInBackground(String... urls) {
    String ret = this.getJson();
    return "";
  }


  protected String getJson() {
    String url = "http://kamcord.com/api/ingameviewer/feed/?developer_key=e1bf908bf86de49cfe925ca183ef5205&type=trending";
    DefaultHttpClient   httpclient = new DefaultHttpClient(new BasicHttpParams());
    HttpGet httppost = new HttpGet(url);
    // Depends on your web service
    httppost.setHeader("Content-type", "application/json");

    InputStream inputStream = null;
    String result = null;
    try {
      HttpResponse response = httpclient.execute(httppost);
      HttpEntity entity = response.getEntity();

      inputStream = entity.getContent();
      // json is UTF-8 by default
      BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"), 8);
      StringBuilder sb = new StringBuilder();

      String line = null;
      while ((line = reader.readLine()) != null)
      {
        sb.append(line + "\n");
      }
      result = sb.toString();
    } catch (Exception e) {
      Log.v("kamcord", e.getStackTrace() + e.toString());
    }
    finally {
      try{if(inputStream != null)inputStream.close();}catch(Exception squish){}
    }
    return result;
  }
}