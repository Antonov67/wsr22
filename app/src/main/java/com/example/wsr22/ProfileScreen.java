package com.example.wsr22;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ipsec.ike.IkeSession;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ProfileScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_screen);
        getSupportActionBar().hide();

        //Ретрофит
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(" http://cinema.areas.su/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);
        Call<List<UserProfile>> call = jsonPlaceHolderApi.getUser(SignInActivity.token);
        call.enqueue(new Callback<List<UserProfile>>() {
            @Override
            public void onResponse(Call<List<UserProfile>> call, Response<List<UserProfile>> response) {
                if (!response.isSuccessful()) {
                    createDialog("Code:" + response.code()).show();
                    return;
                }
            List<UserProfile> userProfileList = response.body();
                ImageView imageView = findViewById(R.id.profile_imageView);
                TextView name = findViewById(R.id.name_user_profile);
                TextView email = findViewById(R.id.email_euser_profile);
               // Picasso.get().load("http://cinema.areas.su/up/images/"+userProfileList.get(0).avatar).into(imageView);
                name.setText(userProfileList.get(0).firstName + " " + userProfileList.get(0).lastName);
                email.setText(userProfileList.get(0).email);

            }

            @Override
            public void onFailure(Call<List<UserProfile>> call, Throwable t) {
                createDialog(t.getMessage()).show();
            }
        });
    }

    public void glavnoe_click2(View view) {
        startActivity(new Intent(ProfileScreen.this,MainScreen.class));
    }


    //метод создания диалогового окна
    Dialog createDialog(String message){
        AlertDialog.Builder dialog = new AlertDialog.Builder(ProfileScreen.this);
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