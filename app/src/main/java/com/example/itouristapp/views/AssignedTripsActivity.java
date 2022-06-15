package com.example.itouristapp.views;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.itouristapp.R;
import com.example.itouristapp.models.Trip;
import com.example.itouristapp.models.responsebean.TripResponse;
import com.example.itouristapp.utils.ApiClient;
import com.example.itouristapp.utils.AppUtils;
import com.example.itouristapp.utils.NetworkAPI;

import java.util.ArrayList;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AssignedTripsActivity extends AppCompatActivity {

    private HashMap<String, String> userDetails;
    private AppUtils appUtils;
    private String tourGuideId, userRole;
    private ProgressDialog progressDialog;
    private RecyclerView recyclerViewTripDetails;
    private ArrayList<Trip> tripArrayList = new ArrayList<>();
    private TripDetailsRecyclerAdapter tripDetailsRecyclerAdapter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assigned_trips);

        appUtils = new AppUtils(AssignedTripsActivity.this, this);
        userDetails = appUtils.getUserDetails();
        tourGuideId = userDetails.get(AppUtils.KEY_TOUR_GUIDE_ID);
        userRole = userDetails.get(AppUtils.KEY_USER_ROLE);
        progressDialog = new ProgressDialog(AssignedTripsActivity.this);
        recyclerViewTripDetails = findViewById(R.id.assigned_trips_recyclerview);

        getAssignedTripDetails();
    }

    private void getAssignedTripDetails(){
        progressDialog.setMessage("Loading data...");
        progressDialog.show();
        NetworkAPI networkAPI = ApiClient.getClient().create(NetworkAPI.class);
        final Call<TripResponse> tripResponseCall = networkAPI.getAssignedTrips(Integer.valueOf(tourGuideId));
        tripResponseCall.enqueue(new Callback<TripResponse>() {
            @Override
            public void onResponse(Call<TripResponse> call, Response<TripResponse> response) {
                if (response.isSuccessful()){
                    tripArrayList = response.body().getTripArrayList();

                    recyclerViewTripDetails.setLayoutManager(new LinearLayoutManager(AssignedTripsActivity.this, LinearLayoutManager.VERTICAL, false));

                    Log.v("destinations data", tripArrayList.toString());

                    tripDetailsRecyclerAdapter = new TripDetailsRecyclerAdapter(tripArrayList, AssignedTripsActivity.this, userRole, "assigned");

                    recyclerViewTripDetails.setAdapter(tripDetailsRecyclerAdapter);
                    progressDialog.dismiss();
                }else{
                    Log.v("something happened", response.toString());
                }
            }

            @Override
            public void onFailure(Call<TripResponse> call, Throwable t) {
                Log.v("something err", t.getLocalizedMessage().toString());
            }
        });

    }
}
