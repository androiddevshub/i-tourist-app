package com.example.itouristapp.views;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.itouristapp.R;
import com.example.itouristapp.models.Review;
import com.example.itouristapp.models.TourGuideBooking;
import com.example.itouristapp.models.TouristBooking;
import com.example.itouristapp.models.responsebean.ReviewResponse;
import com.example.itouristapp.utils.ApiClient;
import com.example.itouristapp.utils.AppUtils;
import com.example.itouristapp.utils.NetworkAPI;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TourGuideBookingActivity extends AppCompatActivity {

    private TextView tvDestinationName, tvDestinationLocation, tvTouristName, tvTouristPhone;
    private TourGuideBooking tourGuideBooking;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tour_guide_booking);

        tourGuideBooking = getIntent().getParcelableExtra("tourGuideBooking");

        tvDestinationName = findViewById(R.id.tv_tour_guide_trip_name_booking);
        tvDestinationLocation = findViewById(R.id.tv_tour_guide_trip_location_booking);
        tvTouristName = findViewById(R.id.tv_booking_tourist_name);
        tvTouristPhone = findViewById(R.id.tv_booking_tourist_phone);


        tvDestinationName.setText(tourGuideBooking.getDestination_name());
        tvDestinationLocation.setText(tourGuideBooking.getDestination_location());
        tvTouristName.setText(tourGuideBooking.getTourist_name());
        tvTouristPhone.setText(tourGuideBooking.getTourist_phone());

    }
}
