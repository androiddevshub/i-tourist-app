package com.example.itouristapp.utils;

import com.example.itouristapp.models.Booking;
import com.example.itouristapp.models.Review;
import com.example.itouristapp.models.User;
import com.example.itouristapp.models.responsebean.BookingResponse;
import com.example.itouristapp.models.responsebean.ReviewResponse;
import com.example.itouristapp.models.responsebean.TourGuideBookingResponse;
import com.example.itouristapp.models.responsebean.TourGuideResponse;
import com.example.itouristapp.models.responsebean.TouristBookingResponse;
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

    // Login API
    @POST("/api/users/login")
    Call<UserResponse> userLoginApi(@Body User user);

    // Update user API
    @PUT("/api/users/{user_id}")
    Call<UserResponse> userUpdateApi(@Path("user_id") int id, @Body User user);

    // update tour guide api
    @FormUrlEncoded
    @PUT("/api/tour_guides/update_info/{id}")
    Call<UserResponse> tourGuideInfoUpdate(@Path("id") int id, @Field("description") String description, @Field("languages") String languages);

    // API for assigning trip to tour guides
    @GET("/api/tour_guides/{id}/assign_new_trips")
    Call<TripResponse> getUnassignedTrips(@Path("id") int id);

    // API for getting unassigned trip to tour guides
    @GET("/api/tour_guides/{id}/assigned_trips")
    Call<TripResponse> getAssignedTrips(@Path("id") int id);

    // API for getting assigned trip to tour guides
    @GET("/api/trips/{id}/tour_guides")
    Call<TourGuideResponse> getTripTourGuides(@Path("id") int id);

    // API for getting trips for tourist
    @GET("/api/destinations")
    Call<TripResponse> destinationsApi();

    // API for assigning trip to tour guides
    @FormUrlEncoded
    @POST("/api/tour_guides/assign_trip")
    Call<TripResponse> assignNewTrip(@Field("destination_id") Integer destinationId, @Field("tour_guide_id") Integer tourGuideId);

    // API for creating review for tour guide
    @POST("/api/reviews")
    Call<ReviewResponse> submitReview(@Body Review review);

    // API for tour guide reviews
    @GET("/api/reviews/{tour_guide_id}")
    Call<ReviewResponse> getTourGuideReviews(@Path("tour_guide_id") int id);

    // API for creating booking
    @POST("/api/bookings")
    Call<BookingResponse> createBooking(@Body Booking booking);

    // API for getting tourist bookings
    @GET("/api/bookings/users/{user_id}")
    Call<TouristBookingResponse> getTouristBookings(@Path("user_id") int id);

    // API for getting tour guide bookings
    @GET("/api/bookings/tour_guides/{tour_guide_id}")
    Call<TourGuideBookingResponse> getTourGuideBookings(@Path("tour_guide_id") int id);



}
