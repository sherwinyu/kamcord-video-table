package com.sherwinyu.videotable;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.client.methods.HttpGet;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;

import org.apache.http.impl.client.DefaultHttpClient;

import org.apache.http.params.BasicHttpParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.AsyncTask;

import android.util.Log;

public class RetrieveJsonTask extends AsyncTask<VideoCellAdapter, Void, String> {
  // TODO(syu): change parameterized return type from String to Void

  MainActivity activity;

  public RetrieveJsonTask(MainActivity activity) {
    this.activity = activity;
  }

  /**
   * Fetch JSON and convert the video data response into video cell objects,
   * and periodically notify the adapter of new items
   * @param params a param array containing a single item, the VideoCellAdapter
   * @return an empty string (ignored)
   */
  protected String doInBackground(VideoCellAdapter... params) {
    Log.v("kamcord", "Back ground processing started");
    final VideoCellAdapter adapter = params[0];
    String json = this.getJson();
    try {
      JSONArray videos = new JSONArray(json);

      // Iterate over all JSON objets
      // For each object, create a video cell
      for (int i = 0; i <  videos.length(); i++) {
        JSONObject video = videos.getJSONObject(i);
        VideoCell videoCell = new VideoCell(
            video.getString("thumbnail_url"),
            video.getString("title"),
            video.getString("video_url")
            );

        // Add to the adapter and tell it to update
        adapter.add(videoCell);
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                adapter.notifyDataSetChanged();
            }
        });
      }
    } catch (JSONException e) {
      Log.v("kamcord", e.toString());
      Log.v("kamcord", "Error in processing JSON");
    }
    Log.v("kamcord", "Finished processing JSON");
    return "";
  }

  /**
   * Makes a request to the json api
   * @return string representation of the JSON
   */
  protected String getJson() {
    Log.v("kamcord", "starting getJson");

    // TODO(syu): Don't hardcode the url
    String url = "http://kamcord.com/api/ingameviewer/feed/?developer_key=e1bf908bf86de49cfe925ca183ef5205&type=trending";

    DefaultHttpClient httpclient = new DefaultHttpClient(new BasicHttpParams());
    HttpGet httppost = new HttpGet(url);
    httppost.setHeader("Content-type", "application/json");
    InputStream inputStream = null;

    String result = null;
    try {
      HttpResponse response = httpclient.execute(httppost);
      HttpEntity entity = response.getEntity();

      inputStream = entity.getContent();
      BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"), 8);
      StringBuilder sb = new StringBuilder();
      String line = null;
      // Append the JSON response
      while ((line = reader.readLine()) != null)
        sb.append(line + "\n");
      result = sb.toString();
    } catch (Exception e) {
      Log.v("kamcord", e.getStackTrace() + e.toString());
    } finally {
      try {
        if (inputStream != null)
          inputStream.close();
      } catch (Exception squish) {
      }
    }
    Log.v("kamcord", "getJson successful");
    return result;
  }
}
