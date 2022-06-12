package com.example.itouristapp.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.PopupMenu;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
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
    private ArrayList<Trip> tripArrayList;
    private TripDetailsRecyclerAdapter tripDetailsRecyclerAdapter;


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
        if(strUserRole.equals("guide")){
            findViewById(R.id.layout_trip_recycler).setVisibility(View.VISIBLE);
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
        NetworkAPI networkAPI = ApiClient.getClient().create(NetworkAPI.class);
        Call<TripResponse> tripResponseCall = networkAPI.destinationsApi();

        tripResponseCall.enqueue(new Callback<TripResponse>() {
            @Override
            public void onResponse(Call<TripResponse> call, Response<TripResponse> response) {
                tripArrayList = response.body().getTripArrayList();
                recyclerViewTripDetails.setLayoutManager(new LinearLayoutManager(MainActivity.this, LinearLayoutManager.VERTICAL, false));

                tripDetailsRecyclerAdapter = new TripDetailsRecyclerAdapter(tripArrayList, MainActivity.this);
                recyclerViewTripDetails.setAdapter(tripDetailsRecyclerAdapter);
            }

            @Override
            public void onFailure(Call<TripResponse> call, Throwable t) {

            }
        });
    }
}