package com.example.wsr22;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface JsonPlaceHolderApi {
    @POST("auth/login")
    Call<LoginResponse> login(@Body LoginRequest loginRequest);
}
