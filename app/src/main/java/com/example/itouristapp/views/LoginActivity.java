package com.example.itouristapp.views;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.itouristapp.R;
import com.example.itouristapp.models.User;
import com.example.itouristapp.models.responsebean.UserResponse;
import com.example.itouristapp.utils.ApiClient;
import com.example.itouristapp.utils.AppUtils;
import com.example.itouristapp.utils.NetworkAPI;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private TextView tvANotRegisteredYet;
    private EditText etEmail, etPassword;
    private String email, password;
    private AppUtils appUtils;
    private Button btnLogin;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        progressDialog = new ProgressDialog(LoginActivity.this);
        appUtils = new AppUtils(getApplicationContext(), this);

        etEmail = findViewById(R.id.et_login_email);
        etPassword = findViewById(R.id.et_login_password);

        btnLogin = findViewById(R.id.btn_login);
        tvANotRegisteredYet = findViewById(R.id.txt_not_registered_yet);
        tvANotRegisteredYet.setPaintFlags(tvANotRegisteredYet.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

        tvANotRegisteredYet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegistrationActivity.class);
                startActivity(intent);
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email = etEmail.getText().toString();
                password = etPassword.getText().toString();

                if (email.isEmpty()){
                    appUtils.showToast("Please enter an email");
                }else if(password.isEmpty()){
                    appUtils.showToast("Please enter password");
                }else {
                    userLoginFun();
                }
            }
        });

    }

    private void userLoginFun(){

        progressDialog.setMessage("Logging In");
        progressDialog.show();

        NetworkAPI networkAPI = ApiClient.getClient().create(NetworkAPI.class);

        Call<UserResponse> userLoginResponseCall = networkAPI.userLoginApi(new User(email, password));

        userLoginResponseCall.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                if (response.isSuccessful()){
                    progressDialog.dismiss();
                    appUtils.showToast(response.body().getMessage());

                    appUtils.setLoggedIn(true,
                            String.valueOf(response.body().getUser().getId()),
                            response.body().getUser().getName(),
                            response.body().getUser().getEmail(),
                            response.body().getUser().getPhone(),
                            response.body().getUser().getToken());

                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
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