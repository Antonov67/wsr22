package com.example.wsr22_clock;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.TextView;

import androidx.viewpager2.widget.ViewPager2;

import com.example.wsr22_clock.databinding.ActivityMoviesBinding;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MoviesActivity extends Activity {

    private  OnBoardingAdapter onBoardingAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies);

        //Ретрофит
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(" http://cinema.areas.su/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);
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
                ViewPager2 viewPager2 = findViewById(R.id.view_pager_clock);
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
        AlertDialog.Builder dialog = new AlertDialog.Builder(MoviesActivity.this);
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