package com.example.wsr22;

import com.google.gson.annotations.SerializedName;

public class LoginRequest {
    //конструктор для авторизации
    public LoginRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }

    //конструктор для регистрации
    public LoginRequest(String email, String password, String firstName, String lastName) {
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    //конструктор для запроса списков фильмов
    public LoginRequest() {

    }

    @SerializedName("email")
    String email;

    @SerializedName("password")
    String password;

    @SerializedName("firstName")
    String firstName;

    @SerializedName("lastName")
    String lastName;


}
