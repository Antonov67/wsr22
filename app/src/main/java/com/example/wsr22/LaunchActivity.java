package com.example.wsr22;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class LaunchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.launch_activity);
        getSupportActionBar().hide();
        ImageView imageView = findViewById(R.id.imageView);
        //анимация, если нужна
        Animation animation = AnimationUtils.loadAnimation(this,R.anim.combination);
        imageView.startAnimation(animation);
        //через 6 секунд открываем основное окно
        new android.os.Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(LaunchActivity.this, SignUpActivity.class));
            }
        }, 6000);
    }
}