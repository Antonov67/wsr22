package com.example.wsr22;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainScreen extends AppCompatActivity {
    private  OnBoardingAdapter onBoardingAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_screen_layout);
        getSupportActionBar().hide();

        //Ретрофит
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(" http://cinema.areas.su/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);
        LoginRequest loginRequest = new LoginRequest();
        Call<List<Film>> call = jsonPlaceHolderApi.get_films("new");
        call.enqueue(new Callback<List<Film>>() {
            @Override
            public void onResponse(Call<List<Film>> call, Response<List<Film>> response) {
                if (!response.isSuccessful()) {
                    createDialog("Code:" + response.code()).show();
                    return;
                }
                List<Film> filmList = response.body();
                //добавим список в адаптер
                onBoardingAdapter = new OnBoardingAdapter(filmList);
                ViewPager2 viewPager2 = findViewById(R.id.view_pager);
                viewPager2.setAdapter(onBoardingAdapter);

            }

            @Override
            public void onFailure(Call<List<Film>> call, Throwable t) {
                createDialog(t.getMessage()).show();
            }
        });



    }
    //метод создания диалогового окна
    Dialog createDialog(String message){
        AlertDialog.Builder dialog = new AlertDialog.Builder(MainScreen.this);
        dialog.setTitle("Предупреждение")
                .setMessage(message)
                .setPositiveButton("ОК", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
        return dialog.create();

    }
}
