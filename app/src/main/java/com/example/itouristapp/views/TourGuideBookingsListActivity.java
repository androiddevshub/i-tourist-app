package com.example.itouristapp.views;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.itouristapp.R;
import com.example.itouristapp.models.TourGuideBooking;
import com.example.itouristapp.models.TouristBooking;
import com.example.itouristapp.models.responsebean.TourGuideBookingResponse;
import com.example.itouristapp.models.responsebean.TouristBookingResponse;
import com.example.itouristapp.utils.ApiClient;
import com.example.itouristapp.utils.AppUtils;
import com.example.itouristapp.utils.NetworkAPI;

import java.util.ArrayList;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TourGuideBookingsListActivity extends AppCompatActivity {

    private RecyclerView recyclerViewBookingsList;
    private TourGuideBookingRecyclerAdapter tourGuideBookingRecyclerAdapter;
    private ArrayList<TourGuideBooking> tourGuideBookingArrayList;
    private ProgressDialog progressDialog;
    private HashMap<String, String> userDetails;
    private Integer tourGuideId;
    private AppUtils appUtils;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tour_guide_bookings_list);

        progressDialog = new ProgressDialog(TourGuideBookingsListActivity.this);
        appUtils = new AppUtils(TourGuideBookingsListActivity.this, this);
        userDetails = appUtils.getUserDetails();
        tourGuideId = Integer.valueOf(userDetails.get(AppUtils.KEY_TOUR_GUIDE_ID));

        recyclerViewBookingsList = findViewById(R.id.trip_tour_guide_bookings_recyclerview);
        getTouristBookings();

    }

    private void getTouristBookings(){

        progressDialog.setMessage("Loading data...");
        progressDialog.show();

        NetworkAPI networkAPI = ApiClient.getClient().create(NetworkAPI.class);
        final Call<TourGuideBookingResponse> tourGuideBookingResponseCall = networkAPI.getTourGuideBookings(tourGuideId);

       tourGuideBookingResponseCall.enqueue(new Callback<TourGuideBookingResponse>() {
           @Override
           public void onResponse(Call<TourGuideBookingResponse> call, Response<TourGuideBookingResponse> response) {
               if (response.isSuccessful()){

                   tourGuideBookingArrayList = response.body().getTourGuideBookingArrayList();

                   recyclerViewBookingsList.setLayoutManager(new LinearLayoutManager(TourGuideBookingsListActivity.this, LinearLayoutManager.VERTICAL, false));

                   tourGuideBookingRecyclerAdapter = new TourGuideBookingRecyclerAdapter(tourGuideBookingArrayList, TourGuideBookingsListActivity.this);

                   recyclerViewBookingsList.setAdapter(tourGuideBookingRecyclerAdapter);
                   progressDialog.dismiss();
               }
           }

           @Override
           public void onFailure(Call<TourGuideBookingResponse> call, Throwable t) {

           }
       });


    }
}
