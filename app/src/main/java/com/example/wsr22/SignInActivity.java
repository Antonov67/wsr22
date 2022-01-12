package com.example.wsr22;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SignInActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        getSupportActionBar().hide();
        //Ретрофит
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(" http://cinema.areas.su/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);

        Button button = findViewById(R.id.button_sign_in_exit);
        Button button2 = findViewById(R.id.button_sign_in);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SignInActivity.this,SignUpActivity.class));
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"начнем",Toast.LENGTH_LONG).show();
                Call<User> call = jsonPlaceHolderApi.authUser();
                call.enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        if (!response.isSuccessful()){
                            Toast.makeText(getApplicationContext(),
                                    "Code:" + response.code(), Toast.LENGTH_LONG).show();
                            return;
                        }
                        User user = response.body();
                        Toast.makeText(getApplicationContext(),
                                user.getEmail() + "\n" +
                                        user.getFirstName() + "\n" +
                                        user.getLastName() + "\n" +
                                        user.getPassword(),
                                Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {
                        Toast.makeText(getApplicationContext(),
                                t.getMessage(),Toast.LENGTH_LONG).show();
                    }
                });
            }
        });

    }
}