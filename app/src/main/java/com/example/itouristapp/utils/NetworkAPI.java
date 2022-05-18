package com.example.itouristapp.utils;

import com.example.itouristapp.models.User;
import com.example.itouristapp.models.responsebean.UserResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface NetworkAPI {

    @POST("/api/users")
    Call<UserResponse> userRegisterApi(@Body User user);

    @POST("/api/users/login")
    Call<UserResponse> userLoginApi(@Body User user);

    @PUT("/api/users/{user_id}")
    Call<UserResponse> userUpdateApi(@Path("user_id") int id, @Body User user);


}
