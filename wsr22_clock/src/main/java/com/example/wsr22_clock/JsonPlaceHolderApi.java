package com.example.wsr22_clock;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface JsonPlaceHolderApi {
    //авторизация
    @POST("auth/login")
    Call<LoginResponse> auth_login(@Body LoginRequest loginRequest);

   //регистрация
    @POST("auth/register")
    Call<Void> reg_login(@Body LoginRequest loginRequest);

    //список фильмов новинок пользователя
    @GET("/movies")
    Call<List<Film>> get_films(@Query("filter") String filter);

    //получение данных для профиля пользователя, авторизация типа Bearer
    @GET("/user")
    Call<List<UserProfile>> getUser( @Header("Authorization") String authHeader);

}
