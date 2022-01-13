package com.example.wsr22;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface JsonPlaceHolderApi {
    //авторизация
    @POST("auth/login")
    Call<LoginResponse> auth_login(@Body LoginRequest loginRequest);

    //регистрация
    @POST("auth/register")
    Call<LoginResponse> reg_login(@Body LoginRequest loginRequest);
}
