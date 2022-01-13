package com.example.wsr22;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;

public class LaunchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //проверяем на первый запуск
        if (isFirstZapusk()){
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
        else {
            startActivity(new Intent(LaunchActivity.this, SignUpActivity.class));
        }
        //сделаем запись, что приложение уже запущено не первый раз
        SharedPreferences sPref = getSharedPreferences("",MODE_PRIVATE);
        SharedPreferences.Editor ed = sPref.edit();
        ed.putString("first","no");
        ed.commit();


    }
    private boolean isFirstZapusk(){
        //анализируем SharedPreferences нашего приложения
        SharedPreferences sPref = getSharedPreferences("",MODE_PRIVATE);
        String str = sPref.getString("first","");
        if (str.equals("no")) return false;
        return true;
    }
}