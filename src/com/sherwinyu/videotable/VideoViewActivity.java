package com.sherwinyu.videotable;
import android.app.Activity;
import android.app.ProgressDialog;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnPreparedListener;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

public class VideoViewActivity extends Activity {

  ProgressDialog progressDialog;
  VideoView videoView;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.video_view);
    videoView = (VideoView) findViewById(R.id.video_view);

    // Fetch the videoUrl and videoTitle
    Bundle extras = getIntent().getExtras();
    String videoUrl = extras.getString("videoUrl");
    String videoTitle = extras.getString("videoTitle");

    // Show a progress bar
    progressDialog = new ProgressDialog(VideoViewActivity.this);
    progressDialog.setTitle(videoTitle);
    progressDialog.setMessage("Buffering...");
    progressDialog.setIndeterminate(false);
    progressDialog.setCancelable(true);
    progressDialog.show();

    try {
      // Start the MediaController
      MediaController mediacontroller = new MediaController(VideoViewActivity.this);
      mediacontroller.setAnchorView(videoView);
      videoView.setMediaController(mediacontroller);

      // Load the video from the URI
      Uri videoUri = Uri.parse(videoUrl);
      videoView.setVideoURI(videoUri);

    } catch (Exception e) {
      Log.e("Error", e.getMessage());
      e.printStackTrace();
    }

    videoView.requestFocus();
    videoView.setOnPreparedListener(new OnPreparedListener() {
      // Close the progress bar and play the video
      public void onPrepared(MediaPlayer mp) {
        progressDialog.dismiss();
        videoView.start();
      }
    });

  }

}
