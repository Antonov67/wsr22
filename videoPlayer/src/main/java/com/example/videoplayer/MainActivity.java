package com.example.videoplayer;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.net.Uri;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.Button;
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
        LinearLayout.LayoutParams linLayoutParam = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        // устанавливаем linLayout как корневой элемент экрана
        linLayoutParam.topMargin = 100;
        linLayoutParam.leftMargin = 100;
        linLayoutParam.rightMargin = 100;
        setContentView(linLayout, linLayoutParam);

        myVideoView = new MyVideoView(this);
        myVideoView.setMediaController(new MediaController(this));
        myVideoView.requestFocus(0);
        linLayout.addView(myVideoView);
        Button button = new Button(this);
        button.setText("нажми на меня");
        linLayout.addView(button,linLayoutParam);


        //проигрывание видеофайла из ресурсов проекта
        //myVideoView.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.video2021));
        //проигрывание внешней ссылки url
        myVideoView.setVideoURI(Uri.parse("rtsp://wowzaec2demo.streamlock.net/vod/mp4:BigBuckBunny_115k.mov"));
        myVideoView.start();
    }
}