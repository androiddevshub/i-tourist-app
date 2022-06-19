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
import com.example.itouristapp.models.TouristBooking;
import com.example.itouristapp.models.responsebean.ReviewResponse;
import com.example.itouristapp.utils.ApiClient;
import com.example.itouristapp.utils.AppUtils;
import com.example.itouristapp.utils.NetworkAPI;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TouristBookingActivity extends AppCompatActivity {

    private TextView tvDestinationName, tvDestinationLocation, tvDestinationDescription, tvTourGuideName, tvTourGuidePhone;
    private EditText etWriteReview;
    private Button btnSubmitReview;
    private TouristBooking touristBooking;
    private String strTourGuideReview;
    private ProgressDialog progressDialog;
    private HashMap<String, String> userDetails;
    private Integer userId;
    private Review review;
    private AppUtils appUtils;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tourist_booking);

        progressDialog = new ProgressDialog(TouristBookingActivity.this);
        appUtils = new AppUtils(TouristBookingActivity.this, this);
        userDetails = appUtils.getUserDetails();
        userId = Integer.valueOf(userDetails.get(AppUtils.KEY_USER_ID));
        touristBooking = getIntent().getParcelableExtra("touristBooking");

        tvDestinationName = findViewById(R.id.tv_tourist_trip_name_booking);
        tvDestinationLocation = findViewById(R.id.tv_tourist_trip_location_booking);
        tvDestinationDescription = findViewById(R.id.tv_tourist_trip_description_booking);
        tvTourGuideName = findViewById(R.id.tv_booking_tour_guide_name_booking);
        tvTourGuidePhone = findViewById(R.id.tv_booking_tour_guide_phone_booking);

        etWriteReview = findViewById(R.id.et_write_review);
        btnSubmitReview = findViewById(R.id.btn_submit_review);


        tvDestinationName.setText(touristBooking.getDestination_name());
        tvDestinationLocation.setText(touristBooking.getDestination_location());
        tvDestinationDescription.setText(touristBooking.getDestination_description());
        tvTourGuideName.setText(touristBooking.getTour_guide_name());
        tvTourGuidePhone.setText(touristBooking.getTour_guide_phone());

        btnSubmitReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                strTourGuideReview = etWriteReview.getText().toString();
                review = new Review(strTourGuideReview, userId, touristBooking.getTour_guide_id());
                submitReview();

            }
        });


    }

    private void submitReview(){
        progressDialog.setMessage("Submitting review");
        progressDialog.show();

        final NetworkAPI networkAPI = ApiClient.getClient().create(NetworkAPI.class);
        Call<ReviewResponse> reviewResponseCall = networkAPI.submitReview(review);

        reviewResponseCall.enqueue(new Callback<ReviewResponse>() {
            @Override
            public void onResponse(Call<ReviewResponse> call, Response<ReviewResponse> response) {
                if (response.isSuccessful()){
                    appUtils.showToast(response.body().getMessage());
                    etWriteReview.setText("");
                    progressDialog.dismiss();
                }
            }

            @Override
            public void onFailure(Call<ReviewResponse> call, Throwable t) {

            }
        });
    }
}
