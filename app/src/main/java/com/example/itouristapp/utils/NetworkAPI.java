package com.example.itouristapp.utils;

import com.example.itouristapp.models.User;
import com.example.itouristapp.models.responsebean.TripResponse;
import com.example.itouristapp.models.responsebean.UserResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface NetworkAPI {


    // Registration
    @POST("/api/users")
    Call<UserResponse> userRegisterApi(@Body User user);

    @POST("/api/users/login")
    Call<UserResponse> userLoginApi(@Body User user);

    @PUT("/api/users/{user_id}")
    Call<UserResponse> userUpdateApi(@Path("user_id") int id, @Body User user);

    @GET("/api/tour_guides/{id}/assign_new_trips")
    Call<TripResponse> getUnassignedTrips(@Path("id") int id);

    @GET("/api/tour_guides/{id}/assigned_trips")
    Call<TripResponse> getAssignedTrips(@Path("id") int id);


    @GET("/api/destinations")
    Call<TripResponse> destinationsApi();

    @FormUrlEncoded
    @POST("/api/tour_guides/assign_trip")
    Call<TripResponse> assignNewTrip(@Field("destination_id") Integer destinationId, @Field("tour_guide_id") Integer tourGuideId);


}
