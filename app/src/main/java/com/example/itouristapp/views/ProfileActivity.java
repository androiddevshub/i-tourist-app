package com.example.itouristapp.views;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.itouristapp.R;
import com.example.itouristapp.models.User;
import com.example.itouristapp.models.responsebean.UserResponse;
import com.example.itouristapp.utils.ApiClient;
import com.example.itouristapp.utils.AppUtils;
import com.example.itouristapp.utils.NetworkAPI;
import com.google.android.material.button.MaterialButton;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileActivity extends AppCompatActivity {

    private EditText etName, etEmail, etPhone, etDescription, etLanguages;
    private String name, email, phone, role, description, languages;
    private Button btnUpdateProfile;
    private AppUtils appUtils;
    private ProgressDialog progressDialog;
    private HashMap<String, String> userDetails;
    private int user_id, tour_guide_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_activity);

        progressDialog = new ProgressDialog(ProfileActivity.this);
        appUtils = new AppUtils(getApplicationContext(), this);
        userDetails = appUtils.getUserDetails();

        etName = findViewById(R.id.et_profile_name);
        etEmail = findViewById(R.id.et_profile_email);
        etPhone = findViewById(R.id.et_profile_phone);
        etDescription = findViewById(R.id.et_profile_description);
        etLanguages = findViewById(R.id.et_profile_languages);
        role = userDetails.get(AppUtils.KEY_USER_ROLE);
        user_id = Integer.valueOf(userDetails.get(AppUtils.KEY_USER_ID));
        tour_guide_id = userDetails.get(AppUtils.KEY_TOUR_GUIDE_ID) != null ? Integer.valueOf(userDetails.get(AppUtils.KEY_TOUR_GUIDE_ID)) : null;
        description = userDetails.get(AppUtils.KEY_TOUR_GUIDE_DESCRIPTION) != null ? userDetails.get(AppUtils.KEY_TOUR_GUIDE_DESCRIPTION) : null;
        languages = userDetails.get(AppUtils.KEY_TOUR_GUIDE_LANGUAGES) != null ? userDetails.get(AppUtils.KEY_TOUR_GUIDE_LANGUAGES) : null;

        if (role.equals("guide")){
            findViewById(R.id.txt_profile_description).setVisibility(View.VISIBLE);
            etDescription.setVisibility(View.VISIBLE);
            findViewById(R.id.txt_profile_languages).setVisibility(View.VISIBLE);
            etLanguages.setVisibility(View.VISIBLE);
            etDescription.setText(description);
            etLanguages.setText(languages);
        }

        etName.setText(userDetails.get(AppUtils.KEY_USER_NAME));
        etEmail.setText(userDetails.get(AppUtils.KEY_USER_EMAIL));
        etPhone.setText(userDetails.get(AppUtils.KEY_USER_PHONE));


        btnUpdateProfile = findViewById(R.id.btn_update_profile);

        btnUpdateProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name = etName.getText().toString();
                email = etEmail.getText().toString();
                phone = etPhone.getText().toString();
                if (role.equals("guide")){
                    description = etDescription.getText().toString();
                    languages = etLanguages.getText().toString();
                }

                if (name.isEmpty()){
                    appUtils.showToast("Please enter an email");
                }else if(phone.isEmpty()){
                    appUtils.showToast("Please enter password");
                }else if(role.equals("guide") && description.isEmpty()){
                    appUtils.showToast("Please enter password");
                }else if(role.equals("guide") && languages.isEmpty()){
                    appUtils.showToast("Please enter password");
                }else {
                    if(role.equals("guide")){
                        updateTourGuideInfo();
                    }else{
                        updateProfile();
                    }

                }
            }
        });

        findViewById(R.id.close_profile).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ProfileActivity.this, MainActivity.class));
            }
        });
    }

    private void updateProfile(){

        progressDialog.setMessage("Updating Profile");
        progressDialog.show();

        NetworkAPI networkAPI = ApiClient.getClient().create(NetworkAPI.class);

        Call<UserResponse> userUpdateResponseCall = networkAPI.userUpdateApi(user_id,new User(name, email, phone));

        userUpdateResponseCall.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                if (response.isSuccessful()){
                    progressDialog.dismiss();
                    appUtils.showToast(response.body().getMessage());

                    appUtils.updateProfile(
                            response.body().getUser().getName(),
                            response.body().getUser().getPhone(),
                            response.body().getUser().getDescription(),
                            response.body().getUser().getLanguages());

                    startActivity(new Intent(ProfileActivity.this, MainActivity.class));
                }else{
                    progressDialog.dismiss();
                    appUtils.showToast("Something went wrong");
                }
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {

            }
        });

    }

    private void updateTourGuideInfo(){

        progressDialog.setMessage("Updating Profile");
        progressDialog.show();

        NetworkAPI networkAPI = ApiClient.getClient().create(NetworkAPI.class);

        Call<UserResponse> userUpdateResponseCall = networkAPI.tourGuideInfoUpdate(tour_guide_id,description, languages);

        userUpdateResponseCall.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                if (response.isSuccessful()){
                    updateProfile();
                }else{
                    progressDialog.dismiss();
                    appUtils.showToast("Something went wrong");
                }
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {

            }
        });

    }
}