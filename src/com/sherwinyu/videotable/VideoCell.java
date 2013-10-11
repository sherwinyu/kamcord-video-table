package com.sherwinyu.videotable;

import java.io.InputStream;

import java.net.URL;

import android.graphics.drawable.Drawable;

import android.util.Log;

/**
 * Class representing the a single video.
 * Includes the title, thumbnail, and videoUrl.
 */
public class VideoCell {
  public String imageUrl;
  public String title;
  public Drawable thumbnail;
  public String videoUrl;

  public VideoCell(String imageUrl, String title, String videoUrl) {
    Log.v("kamcord", "Creating video cell: imageUrl: " + imageUrl + "|| title: " + title);
    this.imageUrl = imageUrl;
    this.thumbnail = this.imageFromUrl(imageUrl);
    this.title = title;
    this.videoUrl = videoUrl;
  }
  /**
   * Makes a request for the image url. Note that this method is only
   * called in an AsyncTask thread, so we can do blocking network
   * activity.
   *
   * @return a Drawable, representing the image contents.
   */
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
