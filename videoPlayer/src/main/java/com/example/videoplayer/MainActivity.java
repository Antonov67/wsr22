package com.example.videoplayer;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.net.Uri;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity {
    MyVideoView myVideoView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        //setContentView(R.layout.layout);
        // создание LinearLayout
        LinearLayout linLayout = new LinearLayout(this);
        // установим вертикальную ориентацию
        linLayout.setOrientation(LinearLayout.VERTICAL);
        // создаем LayoutParams
        LinearLayout.LayoutParams linLayoutParam = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        // устанавливаем linLayout как корневой элемент экрана
        setContentView(linLayout, linLayoutParam);

       /* VideoView videoView = findViewById(R.id.videoView);
        videoView.setMediaController(new MediaController(this));
        videoView.requestFocus(0);
        //videoView.setVideoPath("C:/111/video2021.mp4");
        videoView.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.video2021));
        videoView.start();*/

       myVideoView = new MyVideoView(this);
        myVideoView.setMediaController(new MediaController(this));
        myVideoView.requestFocus(0);
        linLayout.addView(myVideoView);
        myVideoView.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.video2021));
        myVideoView.start();
    }
}