package com.example.itouristapp.views;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
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

public class RegistrationActivity extends AppCompatActivity {

    private TextView tvAlreadyRegistered;

    private EditText etName, etEmail, etPhone, etPassword;
    private Button btnRegister;
    private AppUtils appUtils;
    private String name, email, phone, password;

    private ProgressDialog progressDialog;
    private RadioGroup radioGroupRole;
    private RadioButton radioButtonRole;
    private int selectRoleId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);


        appUtils = new AppUtils(getApplicationContext(), this);

        progressDialog = new ProgressDialog(RegistrationActivity.this);

        tvAlreadyRegistered = findViewById(R.id.txt_already_registered);
        tvAlreadyRegistered.setPaintFlags(tvAlreadyRegistered.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

        etName = findViewById(R.id.et_register_name);
        etEmail = findViewById(R.id.et_register_email);
        etPhone = findViewById(R.id.et_register_phone);
        etPassword = findViewById(R.id.et_register_password);
        btnRegister = findViewById(R.id.btn_register);
        radioGroupRole = findViewById(R.id.radio_group_role);

        tvAlreadyRegistered.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegistrationActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                name = etName.getText().toString();
                email = etEmail.getText().toString();
                phone = etPhone.getText().toString();
                password = etPassword.getText().toString();
                selectRoleId = radioGroupRole.getCheckedRadioButtonId();
                radioButtonRole = (RadioButton) findViewById(selectRoleId);


                if (selectRoleId == -1){
                    appUtils.showToast("Please enter role");
                }else if (name.isEmpty()){
                    appUtils.showToast("Please enter name");
                }else if(email.isEmpty()){
                    appUtils.showToast("Please enter email");
                }else if(phone.isEmpty()){
                    appUtils.showToast("Please enter phone");
                }else if(password.isEmpty()){
                    appUtils.showToast("Please enter password");
                }else if(password.length() < 8){
                    appUtils.showToast("Password should be more than 8 characters");
                }
                else {
                    userRegisterFun(radioButtonRole);
                }

            }
        });

    }

    public void userRegisterFun(RadioButton radioButton){

        int roleId = radioButton.getText().equals("Tourist") ? 1 : 2;

        progressDialog.setMessage("Registering...");
        progressDialog.show();

        final NetworkAPI networkAPI = ApiClient.getClient().create(NetworkAPI.class);

        Call<UserResponse> userRegisterResponseCall = networkAPI.userRegisterApi(new User( name, email, phone, String.valueOf(roleId), password));

        userRegisterResponseCall.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {

                if (response.body().isStatus()){
                    progressDialog.dismiss();
                    appUtils.showToast(response.body().getMessage());
                    if (roleId == 2){
                        startActivity(new Intent(getApplicationContext(), AdditionalInfoActivity.class));
                    }else{
                        startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                    }

                }
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                Log.v("UserRegistration", t.getLocalizedMessage());

            }
        });
    }
}