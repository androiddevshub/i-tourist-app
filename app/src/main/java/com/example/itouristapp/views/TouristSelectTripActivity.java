package com.example.itouristapp.views;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.itouristapp.R;
import com.example.itouristapp.models.TourGuide;
import com.example.itouristapp.models.Trip;
import com.example.itouristapp.models.responsebean.TourGuideResponse;
import com.example.itouristapp.models.responsebean.TripResponse;
import com.example.itouristapp.utils.ApiClient;
import com.example.itouristapp.utils.NetworkAPI;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TouristSelectTripActivity extends AppCompatActivity {


    private TextView tvTouristTripName, tvTouristTripLocation, tvTouristTripDates, tvTouristTripDescription;
    private Trip trip;
    private ProgressDialog progressDialog;

    private TouristGuidesRecyclerAdapter touristGuidesRecyclerAdapter;
    private RecyclerView tripTourGuidesRecyclerView;
    private ArrayList<TourGuide> tourGuideArrayList;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tourist_select_trip);

        trip = getIntent().getParcelableExtra("trip");
        progressDialog = new ProgressDialog(TouristSelectTripActivity.this);

        tvTouristTripName = findViewById(R.id.tv_tourist_trip_name);
        tvTouristTripLocation = findViewById(R.id.tv_tourist_trip_location);
        tvTouristTripDates = findViewById(R.id.tv_tourist_trip_date);
        tvTouristTripDescription = findViewById(R.id.tv_tourist_trip_description);
        tripTourGuidesRecyclerView = findViewById(R.id.trip_tour_guides_recyclerview);

        tvTouristTripName.setText(trip.getName());
        tvTouristTripLocation.setText(trip.getLocation());
        tvTouristTripDescription.setText(trip.getDescription());

        getTripTourGuides();



    }

    private void getTripTourGuides(){
        progressDialog.setMessage("Loading data...");
        progressDialog.show();
        NetworkAPI networkAPI = ApiClient.getClient().create(NetworkAPI.class);
        final Call<TourGuideResponse> tourGuideResponseCall = networkAPI.getTripTourGuides(Integer.valueOf(trip.getId()));

        tourGuideResponseCall.enqueue(new Callback<TourGuideResponse>() {
            @Override
            public void onResponse(Call<TourGuideResponse> call, Response<TourGuideResponse> response) {
                if (response.isSuccessful()){
                    tourGuideArrayList = response.body().getTourGuideArrayList();

                    tripTourGuidesRecyclerView.setLayoutManager(new LinearLayoutManager(TouristSelectTripActivity.this, LinearLayoutManager.VERTICAL, false));


                    touristGuidesRecyclerAdapter = new TouristGuidesRecyclerAdapter(tourGuideArrayList, TouristSelectTripActivity.this);

                    tripTourGuidesRecyclerView.setAdapter(touristGuidesRecyclerAdapter);
                    progressDialog.dismiss();
                }else{
                    Log.v("something happened", response.toString());
                }
            }

            @Override
            public void onFailure(Call<TourGuideResponse> call, Throwable t) {

            }
        });


    }
}
