package com.example.wsr22;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SignUpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        getSupportActionBar().hide();


        //Ретрофит
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(" http://cinema.areas.su/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);

        Button button = findViewById(R.id.button_sign_up_exit);
        Button button2 = findViewById(R.id.reg_button);
        EditText email = findViewById(R.id.editTextTextEmailAddress2);
        EditText password1 = findViewById(R.id.password_sign_up);
        EditText password2 = findViewById(R.id.password_sign_up_2);
        EditText firstName = findViewById(R.id.sign_up_name);
        EditText lastName  = findViewById(R.id.sign_up_surname);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               startActivity(new Intent(SignUpActivity.this,SignInActivity.class));
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               //создаём данные для запроса
                if (password1.getText().toString().equals(password2.getText().toString())
                        && emailIsCorrect(email.getText().toString())
                        && password1.length()!=0
                        && firstName.length()!=0
                        && lastName.length()!=0) {
                   LoginRequest loginRequest = new LoginRequest(
                            email.getText().toString(),
                            password1.getText().toString(),
                            firstName.getText().toString(),
                            lastName.getText().toString());
                    Call<LoginResponse> call = jsonPlaceHolderApi.auth_login(loginRequest);
                    call.enqueue(new Callback<LoginResponse>() {
                        @Override
                        public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                            if (!response.isSuccessful()) {
                                createDialog("Code:" + response.code()).show();
                                return;
                            }
                        }

                        @Override
                        public void onFailure(Call<LoginResponse> call, Throwable t) {
                            Toast.makeText(getApplicationContext(),
                                    t.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    });
                }
                else {
                    createDialog("Некорректно введены данные").show();
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
        AlertDialog.Builder dialog = new AlertDialog.Builder(SignUpActivity.this);
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