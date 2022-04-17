package com.example.itouristapp.views;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.itouristapp.R;
import com.example.itouristapp.utils.AppUtils;

public class SplashActivity extends AppCompatActivity {

    private AppUtils appUtils;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        appUtils = new AppUtils(getApplicationContext(), this);

        Thread myThread = new Thread(){
            @Override
            public void run() {
                try {
                    sleep(3000);
                    if (appUtils.loggedIn()){
                        startActivity(new Intent(SplashActivity.this, MainActivity.class));
                    }else {
                        startActivity(new Intent(SplashActivity.this, LoginActivity.class));
                    }
                    finish();


                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        myThread.start();
    }
}