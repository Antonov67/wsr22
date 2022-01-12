package com.example.wsr22;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignUpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        getSupportActionBar().hide();
        Button button = findViewById(R.id.button_sign_up_exit);
        Button button2 = findViewById(R.id.button);
        EditText email = findViewById(R.id.editTextTextEmailAddress2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               startActivity(new Intent(SignUpActivity.this,SignInActivity.class));
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*проверим что все символы маленькие и после точки не больше 3х символов, общая маска
                проверяется с помощью готового паттерна
                String stroka = email.getText().toString();
                if (email.getText().toString().toLowerCase().equals(email.getText().toString())
                        && (email.getText().toString().length() - email.getText().toString().indexOf(".")<=4)
                        && Patterns.EMAIL_ADDRESS.matcher(email.getText()).matches()) {
                    Toast.makeText(getApplicationContext(), email.getText(), Toast.LENGTH_LONG).show();
                }
                else
                    Toast.makeText(getApplicationContext(), "Нет", Toast.LENGTH_LONG).show();

                 */
                Pattern VALID_EMAIL_ADDRESS_REGEX =
                        Pattern.compile("^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{1,3}$");
                Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(email.getText().toString());
                Toast.makeText(getApplicationContext(),String.valueOf(matcher.find()),Toast.LENGTH_LONG).show();
            }
        });
    }
}