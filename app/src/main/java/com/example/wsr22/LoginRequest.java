package com.example.wsr22;

import com.google.gson.annotations.SerializedName;

public class LoginRequest {
    public LoginRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }

    @SerializedName("email")
    String email;

    @SerializedName("password")
    String password;
}
