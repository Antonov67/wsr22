package com.example.wsr22;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ProfileScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_screen);
    }

    public void glavnoe_click2(View view) {
        startActivity(new Intent(ProfileScreen.this,MainScreen.class));
    }

    public void podborka_click(View view) {
    }
}