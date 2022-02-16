package com.example.videoplayer;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.net.Uri;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity {
    MyVideoView myVideoView;


    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);
        //myVideoView = new MyVideoView(this);
        VideoView videoView = findViewById(R.id.videoView);
        //myVideoView = new MyVideoView(this);
        myVideoView = findViewById(R.id.videoView);
       // videoView.setVideoURI(Uri.parse("http://www.cats.com/cat-speak.3gp"));
       // videoView.start();
    }
}