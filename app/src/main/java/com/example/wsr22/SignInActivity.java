package com.example.wsr22;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


import androidx.appcompat.app.AppCompatActivity;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SignInActivity extends AppCompatActivity {
    public static String token;
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
        EditText email = findViewById(R.id.emailAddress_sign_in);
        EditText password = findViewById(R.id.password_sign_in);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SignInActivity.this,SignUpActivity.class));
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                if (emailIsCorrect(email.getText().toString())
                        && password.length()!=0) {
                    LoginRequest loginRequest = new LoginRequest(email.getText().toString(), password.getText().toString());
                    Call<LoginResponse> call = jsonPlaceHolderApi.auth_login(loginRequest);
                    call.enqueue(new Callback<LoginResponse>() {
                        @Override
                        public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                            if (!response.isSuccessful()) {
                                createDialog("Code:" + response.code()).show();
                                return;
                            }
                            LoginResponse loginResponse = response.body();
                            token = loginResponse.token;
                            Log.d("cinema",SignInActivity.token);
                            //после успешной авторизации переходим на MainScreen
                            startActivity(new Intent(SignInActivity.this, MainScreen.class));
                        }

                        @Override
                        public void onFailure(Call<LoginResponse> call, Throwable t) {
                            createDialog(t.getMessage()).show();
                        }
                    });
                }
                else {
                    createDialog("Неверный email").show();
                }
            }
        });
    }
    //метод проверки корректности почты
    public boolean emailIsCorrect(String email){
     /*
                Требования к email: email должен соответствовать паттерну "name@domenname.ru",
                где имя и домен второго уровня могут состоять только из маленьких букв и цифр,
                домен верхнего уровня - только из маленьких букв. Длина домена верхнего уровня -
                 не более 3х символов и поле ввода почты не пустое.
                 */
        Pattern VALID_EMAIL_ADDRESS_REGEX =
                Pattern.compile("^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{1,3}$");
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(email);
        return (matcher.find() && email.length()!=0);
    }
    //метод создания диалогового окна
    Dialog createDialog(String message){
        AlertDialog.Builder dialog = new AlertDialog.Builder(SignInActivity.this);
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