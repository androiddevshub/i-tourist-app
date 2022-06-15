package com.example.itouristapp.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.PopupMenu;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.itouristapp.R;
import com.example.itouristapp.models.Trip;
import com.example.itouristapp.models.responsebean.TripResponse;
import com.example.itouristapp.utils.ApiClient;
import com.example.itouristapp.utils.AppUtils;
import com.example.itouristapp.utils.NetworkAPI;
import com.google.android.material.card.MaterialCardView;

import java.util.ArrayList;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private HashMap<String, String> userDetails;
    private AppUtils appUtils;
    private ImageView imageViewMenu;
    private String strUserName, strUserRole;
    private TextView tvUserName, tvUserRole;
    private ProgressDialog progressDialog;
    private RecyclerView recyclerViewTripDetails;
    private ArrayList<Trip> tripArrayList = new ArrayList<>();
    private TripDetailsRecyclerAdapter tripDetailsRecyclerAdapter;
    private MaterialCardView mCardViewAssignNewTrip, mCardViewAssignedTrips, mCardViewGuideBookings;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageViewMenu = findViewById(R.id.image_view_menu);
        appUtils = new AppUtils(MainActivity.this, this);
        userDetails = appUtils.getUserDetails();
        tvUserName = findViewById(R.id.tv_hello_user);
        tvUserRole = findViewById(R.id.tv_user_role);
        strUserName = userDetails.get(AppUtils.KEY_USER_NAME);
        strUserRole = userDetails.get(AppUtils.KEY_USER_ROLE);
        tvUserName.setText("Hey "+strUserName);
        tvUserRole.setText("Role: "+ (strUserRole.equals("tourist") ? "Tourist" : "Tour Guide"));
        mCardViewAssignNewTrip = findViewById(R.id.cardView_assign_new_trips);
        mCardViewAssignedTrips = findViewById(R.id.cardView_assigned_trips);
        mCardViewGuideBookings = findViewById(R.id.cardView_bookings);
        if(strUserRole.equals("guide")){

            findViewById(R.id.layout_options_tour_guides).setVisibility(View.VISIBLE);
        }else{
            findViewById(R.id.layout_trip_list_recycler).setVisibility(View.VISIBLE);
        }
        progressDialog = new ProgressDialog(MainActivity.this);

        recyclerViewTripDetails = findViewById(R.id.trip_details_recyclerview);

        imageViewMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopup(v);
            }
        });

        getTripDetailsData();

        mCardViewAssignNewTrip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, UnassignedTripsListActivity.class));
            }
        });

        mCardViewAssignedTrips.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, AssignedTripsActivity.class));
            }
        });
    }

    public void showPopup(View v) {
        PopupMenu popup = new PopupMenu(this, v);
        popup.getMenuInflater().inflate(R.menu.main_menu, popup.getMenu());

        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            public boolean onMenuItemClick(MenuItem item) {
                switch(item.getItemId()) {
                    case R.id.logout:
                        appUtils.logoutUser();
                        break;
                    case R.id.profile:
                        startActivity(new Intent(MainActivity.this, ProfileActivity.class));
                        break;
                }
                return true;
            }
        });

        popup.show();
    }

    private void getTripDetailsData(){
        progressDialog.setMessage("Loading data...");
        progressDialog.show();
        NetworkAPI networkAPI = ApiClient.getClient().create(NetworkAPI.class);
        final Call<TripResponse> tripResponseCall = networkAPI.destinationsApi();
        tripResponseCall.enqueue(new Callback<TripResponse>() {
            @Override
            public void onResponse(Call<TripResponse> call, Response<TripResponse> response) {
                if (response.isSuccessful()){
                    tripArrayList = response.body().getTripArrayList();

                    recyclerViewTripDetails.setLayoutManager(new LinearLayoutManager(MainActivity.this, LinearLayoutManager.VERTICAL, false));

                    Log.v("destinations data", tripArrayList.toString());

                    tripDetailsRecyclerAdapter = new TripDetailsRecyclerAdapter(tripArrayList, MainActivity.this, strUserRole, "booking");

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