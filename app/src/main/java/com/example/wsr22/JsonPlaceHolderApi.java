package com.example.wsr22;

import retrofit2.Call;
import retrofit2.http.POST;

public interface JsonPlaceHolderApi {
    @POST
    Call<User> authUser();
}
