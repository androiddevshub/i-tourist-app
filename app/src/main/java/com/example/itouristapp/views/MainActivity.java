package com.example.itouristapp.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.PopupMenu;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.itouristapp.R;
import com.example.itouristapp.utils.AppUtils;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    private HashMap<String, String> userDetails;
    private AppUtils appUtils;
    private ImageView imageViewMenu;
    private String strUserName, strUserRole;
    private TextView tvUserName, tvUserRole;


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

        imageViewMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopup(v);
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
                        startActivity(new Intent(MainActivity.this, SplashActivity.class));
                        break;
                }
                return true;
            }
        });

        popup.show();
    }
}