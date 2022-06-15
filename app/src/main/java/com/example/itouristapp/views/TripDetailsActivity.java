package com.example.itouristapp.views;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.itouristapp.R;
import com.example.itouristapp.models.Trip;

public class TripDetailsActivity extends AppCompatActivity {

    private Trip trip;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tour_guide_trip);

        trip = getIntent().getParcelableExtra("trip");

        Log.v("something I got", trip.getName());


    }
}
