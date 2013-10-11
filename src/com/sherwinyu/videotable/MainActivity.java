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

  ListView listView;
  VideoCellAdapter adapter;
  ArrayList<VideoCell> list ;
  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    setContentView(R.layout.activity_main);

    listView = (ListView) findViewById(R.id.listView);

    list = new ArrayList<VideoCell>();
    adapter = new VideoCellAdapter(this, R.layout.activity_list_item, list);
    listView.setAdapter(adapter);

    new RetrieveJsonTask(this).execute(adapter);


    listView.setOnItemClickListener(new OnItemClickListener() {
      public void onItemClick(AdapterView<?> parent, View view,
        int position, long id) {
        // String text = listView.data.get(position)
        String text = "Sup";
        VideoCell videoCell = adapter.data.get(position);
        text = "Playing video: " + videoCell.title;
        Toast.makeText(getApplicationContext(),
          text, Toast.LENGTH_SHORT).show();
        launchVideoActivity(MainActivity.this, videoCell);
      }
    });

  }
  public void launchVideoActivity(MainActivity context, VideoCell videoCell) {
    Intent intent = new Intent(context, VideoViewActivity.class);
    intent.putExtra("videoUrl", videoCell.videoUrl);
    startActivity(intent);
  }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
      getMenuInflater().inflate(R.menu.activity_main, menu);
      return true;
    }
  

}

