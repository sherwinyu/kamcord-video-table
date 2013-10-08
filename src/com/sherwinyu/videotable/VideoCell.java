package com.sherwinyu.videotable;

import java.io.InputStream;

import java.net.URL;

import android.graphics.drawable.Drawable;

import android.util.Log;

public class VideoCell {
  public String imageUrl;
  public String title;
  public Drawable thumbnail;


  public VideoCell(String imageUrl, String title) {
    Log.v("kamcord", "Creating video cell: imageUrl: " + imageUrl + "|| title: " + title);
    this.imageUrl = imageUrl;
    this.thumbnail = this.imageFromUrl(imageUrl);
    this.title = title;
  }
  public Drawable imageFromUrl(String url) {
    Log.v("kamcord", "imageFromUrl entered " + url);
    Drawable drawable = null;
    try {
      InputStream is = (InputStream) new URL(url).getContent();
      drawable = Drawable.createFromStream(is, "src");
      Log.v("kamcord", "Drawable created");
    } catch (Exception e) {
      Log.v("kamcord", e.toString());
    }
    Log.v("kamcord", "imageFromUrl exited " + url);
    return drawable;
  }
}
