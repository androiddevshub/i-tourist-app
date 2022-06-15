package com.example.itouristapp.views;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.itouristapp.R;
import com.example.itouristapp.models.Trip;
import com.example.itouristapp.models.responsebean.TripResponse;
import com.example.itouristapp.utils.ApiClient;
import com.example.itouristapp.utils.AppUtils;
import com.example.itouristapp.utils.NetworkAPI;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TourGuideTripActivity extends AppCompatActivity {

    private Trip trip;
    private HashMap<String, String> userDetails;
    private AppUtils appUtils;
    private String tourGuideId, userRole, action;
    private TextView tvGuideTripName, tvGuideTripLocation, tvGuideTripDates, tvGuideTripDescription;
    private Button btnAssignNewTrip, btnCancelTrip;
    private ProgressDialog progressDialog;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tour_guide_trip);

        trip = getIntent().getParcelableExtra("trip");
        action = getIntent().getStringExtra("action");
        btnAssignNewTrip = findViewById(R.id.btn_assign_trip);
        if (action.equals("assigned")){
            findViewById(R.id.layout_cancel_assigned_trip).setVisibility(View.VISIBLE);
        }else if (action.equals("new_trip")){
            btnAssignNewTrip.setVisibility(View.VISIBLE);
        }
        progressDialog = new ProgressDialog(TourGuideTripActivity.this);
        appUtils = new AppUtils(TourGuideTripActivity.this, this);
        userDetails = appUtils.getUserDetails();
        tourGuideId = userDetails.get(AppUtils.KEY_TOUR_GUIDE_ID);
        tvGuideTripName = findViewById(R.id.tv_guide_trip_name);
        tvGuideTripLocation = findViewById(R.id.tv_guide_trip_location);
        tvGuideTripDates = findViewById(R.id.tv_guide_trip_date);
        tvGuideTripDescription = findViewById(R.id.tv_guide_trip_description);

        btnCancelTrip = findViewById(R.id.btn_cancel_trip);

        tvGuideTripName.setText(trip.getName());
        tvGuideTripLocation.setText(trip.getLocation());
//        tvGuideTripDates.setText(trip.getName());
        tvGuideTripDescription.setText(trip.getDescription());

        btnAssignNewTrip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                assignNewTrip();
            }
        });
    }

    private void assignNewTrip(){
        progressDialog.setMessage("Loading data...");
        progressDialog.show();
        NetworkAPI networkAPI = ApiClient.getClient().create(NetworkAPI.class);
        final Call<TripResponse> tripResponseCall = networkAPI.assignNewTrip(trip.getId(), Integer.valueOf(tourGuideId));

        tripResponseCall.enqueue(new Callback<TripResponse>() {
            @Override
            public void onResponse(Call<TripResponse> call, Response<TripResponse> response) {
                if (response.isSuccessful()){
                    startActivity(new Intent(TourGuideTripActivity.this, UnassignedTripsListActivity.class));
                    progressDialog.dismiss();
                }
            }

            @Override
            public void onFailure(Call<TripResponse> call, Throwable t) {

            }
        });

    }
}
