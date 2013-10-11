package com.sherwinyu.videotable;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends Activity {

  // TODO(syu): refactor to extend ListActivity directly
  ListView listView;
  VideoCellAdapter adapter;
  ArrayList<VideoCell> list;

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    setContentView(R.layout.activity_main);

    listView = (ListView) findViewById(R.id.listView);

    list = new ArrayList<VideoCell>();
    adapter = new VideoCellAdapter(this, R.layout.activity_list_item, list);
    listView.setAdapter(adapter);

    // Start an AsyncTask, passing in the adapter so the task can
    // update the UI asynchronously as video thumbnails come in
    new RetrieveJsonTask(this).execute(adapter);

    // Set up the list item click listener to launch the corresponding
    // video view activity
    listView.setOnItemClickListener(new OnItemClickListener() {
      public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        VideoCell videoCell = adapter.data.get(position);

        String text = "Playing video: " + videoCell.title;
        Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT).show();
        launchVideoActivity(MainActivity.this, videoCell);
      }
    });

  }

  /**
   * Launches the video view activity, which can be `back` out of to
   * return to the MainActivity list view.
   *
   * @param context the parent context
   * @param videoCell a `VideoCell` that specifies the video title and video url
   */
  public void launchVideoActivity(MainActivity context, VideoCell videoCell) {
    Intent intent = new Intent(context, VideoViewActivity.class);
    intent.putExtra("videoTitle", videoCell.title);
    intent.putExtra("videoUrl", videoCell.videoUrl);
    startActivity(intent);
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.activity_main, menu);
    return true;
  }

}

