package com.sherwinyu.videotable;

import android.graphics.drawable.Drawable;

public class VideoCell {
  public String imageUrl;
  public String title;
  public Drawable thumbnail;


  public VideoCell(String imageUrl, String title) {
    this.imageUrl = imageUrl;
    this.title = title;
  }
}
