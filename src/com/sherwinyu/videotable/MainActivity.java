package com.sherwinyu.videotable;

import java.util.ArrayList;

import android.app.Activity;

import android.os.Bundle;
import android.view.Menu;

import android.widget.ListView;

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
    // setupAdapter();

    /*
    String[] values = new String[] {"abc", "def", "ghi"};
    list = new ArrayList<VideoCell>();
    for (int i = 0; i < values.length; i++) {

      list.add(new VideoCell("http://images2.wikia.nocookie.net/__cb20120717192903/dickfigures/images/c/cd/Lynn_Wang.png", values[i]));
    }
    */



  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.activity_main, menu);
    return true;
  }



}

