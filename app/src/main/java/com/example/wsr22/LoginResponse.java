package com.example.wsr22;

import androidx.annotation.Nullable;

import com.google.gson.annotations.SerializedName;

public class LoginResponse {

    @Nullable
    @SerializedName("data")
    public Data data;

    public class Data {

        @Nullable
        @SerializedName("token")
        public String token;

    }

    @Nullable
    @SerializedName("error")
    public String error;

    @Nullable
    @SerializedName("system")
    public System system;

    public class System {

        @Nullable
        @SerializedName("time")
        public double time;

    }
}
