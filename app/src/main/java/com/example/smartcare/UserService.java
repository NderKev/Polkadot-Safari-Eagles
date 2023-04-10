package com.example.smartcare;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UserService {

    @POST("v1/btc/usr/login")
    Call<LoginResponce> loginUser(@Body LoginRequest loginRequest);

    @POST("v1/btc/usr/register")
    Call<RegisterResponse> registerUsers(@Body RegisterRequest loginRequest);
}
