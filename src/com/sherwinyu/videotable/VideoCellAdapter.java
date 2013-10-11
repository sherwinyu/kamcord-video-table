package com.sherwinyu.videotable;

import java.util.ArrayList;

import android.app.Activity;

import android.content.Context;

import android.util.Log;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class VideoCellAdapter extends ArrayAdapter<VideoCell> {

  Context context;
  int layoutResourceId;

  // TODO(syu): ArrayAdapter probably has a built-in way to
  // manipulate its underlying data store
  ArrayList<VideoCell> data = null;

  public VideoCellAdapter(Context context, int layoutResourceId, ArrayList<VideoCell> data) {
    super(context, layoutResourceId, data);
    this.layoutResourceId = layoutResourceId;
    this.context = context;
    this.data = data;
  }

  @Override
  // TODO(syu): Probably not the idiomatic way to do this
  public void add(VideoCell videoCell) {
    this.data.add(videoCell);
  }

  /**
   * Inflates a list item view (containing the video cell) if this is
   * the first time fetching the view Otherwise, looks it up via
   * a holder
   *
   * @param idx the position in the list
   * @param convertView the view to be fetched
   * @param parent the parent view (unused)
   * @return the corresponding view
   */
  @Override
  public View getView(int idx, View convertView, ViewGroup parent) {
    View rowView = convertView;
    VideoCellHolder holder = null;
    VideoCell videoCell = data.get(idx);

    Log.v("kamcord", "VCAdapter.getView(" + idx + "): title: " + videoCell.title);

    // Set up the holder
    if (rowView == null) {
      LayoutInflater inflater = ((Activity) context).getLayoutInflater();
      rowView = inflater.inflate(layoutResourceId, parent, false);
      holder = new VideoCellHolder();
      holder.thumbnail = (ImageView) rowView.findViewById(R.id.videoCellThumbnail);
      holder.title = (TextView) rowView.findViewById(R.id.videoCellTitle);

      Log.v("kamcord", "holder miss: " + holder.title.getText());
      rowView.setTag(holder);
    }
    else {
      holder = (VideoCellHolder) rowView.getTag();
      Log.v("kamcord", "holder hit: " + holder.title.getText());
    }

    // Manipulate the UI through the holder

    // If the videoCell has a thumbnail, display it
    if (videoCell.thumbnail != null) {
      holder.thumbnail.setImageDrawable(videoCell.thumbnail);
      Log.v("kamcord", "getView: settingImageDrawable to " + videoCell.imageUrl);
    } else {
      Log.v("kamcord", "getView: noImageFound for video: " + videoCell.title);
    }
    // Set the title of the view to the video title
    holder.title.setText(videoCell.title);
    Log.v("kamcord", "holder.title.getText(): " + holder.title.getText());

    return rowView;
  }

  /**
   * A simple class that provides direct access to a list item view's
   * ImageView and TextView (view holder pattern)
   */
  private class VideoCellHolder {
    ImageView thumbnail;
    TextView title;
  }
}
