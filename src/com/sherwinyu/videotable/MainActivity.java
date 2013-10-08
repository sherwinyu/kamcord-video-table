package com.sherwinyu.videotable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.Activity;

import android.content.Context;
import android.os.Bundle;
import android.view.Menu;

import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends Activity {

  ListView listView;
  StableArrayAdapter adapter;
  ArrayList<String> list ;
  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    setContentView(R.layout.activity_main);

    new RetrieveJsonTask().execute("asdf");
    // setupAdapter();

    String[] values = new String[] {"abc", "def", "ghi"};
    list = new ArrayList<String>();
    for (int i = 0; i < values.length; i++) {
      list.add(values[i]);
    }

    listView = (ListView) findViewById(R.id.listView);
    adapter = new StableArrayAdapter(this, android.R.layout.simple_list_item_1, list);
    listView.setAdapter(adapter);


  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.activity_main, menu);
    return true;
  }

  private class StableArrayAdapter extends ArrayAdapter<String> {

    HashMap<String, Integer> mIdMap = new HashMap<String, Integer>();

    public StableArrayAdapter(Context context, int textViewResourceId,
        List<String> objects) {
      super(context, textViewResourceId, objects);
      for (int i = 0; i < objects.size(); ++i) {
        mIdMap.put(objects.get(i), i);
      }
    }

    @Override
    public long getItemId(int position) {
      String item = getItem(position);
      return mIdMap.get(item);
    }

    @Override
    public boolean hasStableIds() {
      return true;
    }

  }

}

