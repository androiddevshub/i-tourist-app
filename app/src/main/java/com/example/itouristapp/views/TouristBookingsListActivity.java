package com.example.itouristapp.views;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.itouristapp.R;
import com.example.itouristapp.models.Review;
import com.example.itouristapp.models.TouristBooking;
import com.example.itouristapp.models.responsebean.TourGuideResponse;
import com.example.itouristapp.models.responsebean.TouristBookingResponse;
import com.example.itouristapp.utils.ApiClient;
import com.example.itouristapp.utils.AppUtils;
import com.example.itouristapp.utils.NetworkAPI;

import java.util.ArrayList;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TouristBookingsListActivity extends AppCompatActivity {

    private RecyclerView recyclerViewBookingsList;
    private TouristBookingRecyclerAdapter touristBookingRecyclerAdapter;
    private ArrayList<TouristBooking> touristBookingArrayList;
    private ProgressDialog progressDialog;
    private HashMap<String, String> userDetails;
    private Integer userId;
    private AppUtils appUtils;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tourist_bookings_list);

        progressDialog = new ProgressDialog(TouristBookingsListActivity.this);
        appUtils = new AppUtils(TouristBookingsListActivity.this, this);
        userDetails = appUtils.getUserDetails();
        userId = Integer.valueOf(userDetails.get(AppUtils.KEY_USER_ID));

        recyclerViewBookingsList = findViewById(R.id.trip_tourist_bookings_recyclerview);
        getTouristBookings();

    }

    private void getTouristBookings(){

        progressDialog.setMessage("Loading data...");
        progressDialog.show();

        NetworkAPI networkAPI = ApiClient.getClient().create(NetworkAPI.class);
        final Call<TouristBookingResponse> touristBookingCall = networkAPI.getTouristBookings(userId);

        touristBookingCall.enqueue(new Callback<TouristBookingResponse>() {
            @Override
            public void onResponse(Call<TouristBookingResponse> call, Response<TouristBookingResponse> response) {
                if (response.isSuccessful()){

                    touristBookingArrayList = response.body().getTouristBookingArrayList();

                    recyclerViewBookingsList.setLayoutManager(new LinearLayoutManager(TouristBookingsListActivity.this, LinearLayoutManager.VERTICAL, false));

                    touristBookingRecyclerAdapter = new TouristBookingRecyclerAdapter(touristBookingArrayList, TouristBookingsListActivity.this);

                    recyclerViewBookingsList.setAdapter(touristBookingRecyclerAdapter);
                    progressDialog.dismiss();
                }
            }

            @Override
            public void onFailure(Call<TouristBookingResponse> call, Throwable t) {

            }
        });


    }
}
