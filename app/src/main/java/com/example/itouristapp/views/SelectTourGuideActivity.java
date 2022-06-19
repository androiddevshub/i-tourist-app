package com.example.itouristapp.views;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.itouristapp.R;
import com.example.itouristapp.models.Booking;
import com.example.itouristapp.models.Review;
import com.example.itouristapp.models.TourGuide;
import com.example.itouristapp.models.responsebean.BookingResponse;
import com.example.itouristapp.models.responsebean.ReviewResponse;
import com.example.itouristapp.models.responsebean.TourGuideResponse;
import com.example.itouristapp.utils.ApiClient;
import com.example.itouristapp.utils.AppUtils;
import com.example.itouristapp.utils.NetworkAPI;

import java.util.ArrayList;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SelectTourGuideActivity extends AppCompatActivity {

    private TextView tvTourGuideName, tvTourGuideLanguages, tvTourGuideDescription;
    private Button btnBookTrip;
    private TourGuideReviewsRecyclerAdapter tourGuideReviewsRecyclerAdapter;
    private RecyclerView tourGuideReviewRecyclerView;
    private ArrayList<Review> reviewArrayList;
    private ProgressDialog progressDialog;
    private TourGuide tourGuide;
    private HashMap<String, String> userDetails;
    private AppUtils appUtils;
    private Integer userId, destinationId;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tour_select_guide);

        appUtils = new AppUtils(SelectTourGuideActivity.this, this);
        userDetails = appUtils.getUserDetails();

        userId = Integer.valueOf(userDetails.get(AppUtils.KEY_USER_ID));
        destinationId = getIntent().getIntExtra("destinationId", 0);


        tourGuide = getIntent().getParcelableExtra("tourGuide");
        progressDialog = new ProgressDialog(SelectTourGuideActivity.this);
        tvTourGuideName = findViewById(R.id.tv_select_tg_name);
        tvTourGuideLanguages = findViewById(R.id.tv_select_tg_languages);
        tvTourGuideDescription = findViewById(R.id.tv_select_tg_description);
        btnBookTrip = findViewById(R.id.btn_book_trip);
        tourGuideReviewRecyclerView = findViewById(R.id.tour_guides_reviews_recyclerview);

        tvTourGuideName.setText(tourGuide.getName());
        tvTourGuideLanguages.setText(tourGuide.getLanguages());
        tvTourGuideDescription.setText(tourGuide.getDescription());

        getTourGuideReviews();

        btnBookTrip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                createBooking();
            }
        });

    }

    private void createBooking() {
        progressDialog.setMessage("Booking...");
        progressDialog.show();
        NetworkAPI networkAPI = ApiClient.getClient().create(NetworkAPI.class);
        final Call<BookingResponse> bookingResponseCall = networkAPI.createBooking(new Booking(userId, tourGuide.getId(),destinationId ));

        bookingResponseCall.enqueue(new Callback<BookingResponse>() {
            @Override
            public void onResponse(Call<BookingResponse> call, Response<BookingResponse> response) {
                if (response.isSuccessful()){
                    startActivity(new Intent(SelectTourGuideActivity.this, MainActivity.class));

                }
            }

            @Override
            public void onFailure(Call<BookingResponse> call, Throwable t) {

            }
        });

    }

    private void getTourGuideReviews(){

        progressDialog.setMessage("Loading reviews...");
        progressDialog.show();
        NetworkAPI networkAPI = ApiClient.getClient().create(NetworkAPI.class);
        final Call<ReviewResponse> reviewResponseCall = networkAPI.getTourGuideReviews(tourGuide.getId());

        reviewResponseCall.enqueue(new Callback<ReviewResponse>() {
            @Override
            public void onResponse(Call<ReviewResponse> call, Response<ReviewResponse> response) {

                if (response.isSuccessful()){
                    reviewArrayList = response.body().getReviewArrayList();

                    tourGuideReviewRecyclerView.setLayoutManager(new LinearLayoutManager(SelectTourGuideActivity.this, LinearLayoutManager.VERTICAL, false));

                    tourGuideReviewsRecyclerAdapter = new TourGuideReviewsRecyclerAdapter(reviewArrayList, SelectTourGuideActivity.this);

                    tourGuideReviewRecyclerView.setAdapter(tourGuideReviewsRecyclerAdapter);
                    progressDialog.dismiss();
                }
            }

            @Override
            public void onFailure(Call<ReviewResponse> call, Throwable t) {

            }
        });

    }
}
