package com.sherwinyu.videotable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.Activity;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends Activity {

  ListView listView;
  VideoCellAdapter adapter;
  ArrayList<VideoCell> list ;
  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    setContentView(R.layout.activity_main);

    new RetrieveJsonTask().execute("asdf");
    // setupAdapter();

    String[] values = new String[] {"abc", "def", "ghi"};
    list = new ArrayList<VideoCell>();
    for (int i = 0; i < values.length; i++) {

      list.add(new VideoCell("", values[i]));
    }

    listView = (ListView) findViewById(R.id.listView);
    adapter = new VideoCellAdapter(this, android.R.layout.simple_list_item_1, list);
    listView.setAdapter(adapter);


  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.activity_main, menu);
    return true;
  }

  private class VideoCellAdapter extends ArrayAdapter<VideoCell> {

    Context context;
    int layoutResourceId;
    ArrayList<VideoCell> data = null;

    public VideoCellAdapter(Context context, int layoutResourceId, ArrayList<VideoCell> data) {
      super(context, layoutResourceId, data);
      this.layoutResourceId = layoutResourceId;
      this.context = context;
      this.data = data;
  }


    /*
    public VideoCellAdapter(Context context, int textViewResourceId,
        List<String> objects) {
      super(context, textViewResourceId, objects);
      for (int i = 0; i < objects.size(); ++i) {
        mIdMap.put(objects.get(i), i);
      }
    }

    @Override
    public long getItemId(int position) {
      VideoCell item = getItem(position);
      return mIdMap.get(item);
    }
    */

    @Override
    public View getView(int idx, View convertView, ViewGroup parent) {
      View rowView = convertView;
      VideoCellHolder holder = null;

      if (rowView == null) {
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        rowView = inflater.inflate(layoutResourceId, parent, false);
        holder = new VideoCellHolder();
        holder.thumbnail = (ImageView) rowView.findViewById(R.id.thumbnail);
        holder.title = (TextView) rowView.findViewById(R.id.title);

        rowView.setTag(holder);
      }
      else {
        holder = (VideoCellHolder) rowView.getTag();
      }
      VideoCell videoCell =  data.get(idx);
      if (videoCell.thumbnail != null)
        holder.thumbnail.setImageDrawable(videoCell.thumbnail);
      holder.title.setText(videoCell.title);

      return rowView;
    }

  }

  private class VideoCellHolder {
    ImageView thumbnail;
    TextView title;
  }

}

