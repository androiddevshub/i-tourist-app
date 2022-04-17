package com.example.itouristapp.utils;

import com.example.itouristapp.models.User;
import com.example.itouristapp.models.responsebean.UserResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface NetworkAPI {

    @POST("/api/users")
    Call<UserResponse> userRegisterApi(@Body User user);

    @POST("/api/users/login")
    Call<UserResponse> userLoginApi(@Body User user);

}
