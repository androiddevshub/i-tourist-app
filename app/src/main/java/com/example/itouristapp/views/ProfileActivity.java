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

    private EditText etName, etEmail, etPhone;
    private String name, email, phone;
    private Button btnUpdateProfile;
    private AppUtils appUtils;
    private ProgressDialog progressDialog;
    private HashMap<String, String> userDetails;
    private int user_id;

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
        user_id = Integer.valueOf(userDetails.get(AppUtils.KEY_USER_ID));

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

                if (name.isEmpty()){
                    appUtils.showToast("Please enter an email");
                }else if(phone.isEmpty()){
                    appUtils.showToast("Please enter password");
                }else {
                    updateProfile();
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
                            response.body().getUser().getPhone());

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
}