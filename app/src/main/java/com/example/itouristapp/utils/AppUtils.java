package com.example.itouristapp.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.itouristapp.R;
import com.example.itouristapp.views.LoginActivity;

import java.util.HashMap;

public class AppUtils {

    private SharedPreferences pref = null;
    private SharedPreferences.Editor editor;
    Context ctx;
    Activity activity;



    public static final String KEY_USER_ID = "user_id";
    public static final String KEY_USER_NAME = "name";
    public static final String KEY_USER_EMAIL = "email";
    public static final String KEY_USER_PHONE = "phone";
    public static final String KEY_USER_ROLE = "role";
    public static final String KEY_USER_TOKEN = "user_token";
    public static final String KEY_TOUR_GUIDE_ID = "tour_guide_id";
    public static final String KEY_TOUR_GUIDE_DESCRIPTION = "tour_guide_description";
    public static final String KEY_TOUR_GUIDE_LANGUAGES = "tour_guide_languages";

    public AppUtils(Context ctx, Activity activity) {
        this.ctx = ctx;
        this.activity = activity;
        pref = ctx.getSharedPreferences("itourist", Context.MODE_PRIVATE);
        editor = pref.edit();
    }

    // function for setting up signed in user
    public void setLoggedIn(boolean loggedIn, String userId ,String name, String email, String phone, String tour_guide_id, String role, String token, String description, String languages){
        editor.putBoolean("loggedInMode", loggedIn);
        editor.putString(KEY_USER_ID, userId);
        editor.putString(KEY_USER_NAME, name);
        editor.putString(KEY_USER_EMAIL, email);
        editor.putString(KEY_USER_PHONE, phone);
        editor.putString(KEY_TOUR_GUIDE_ID, tour_guide_id);
        editor.putString(KEY_USER_ROLE, role);
        editor.putString(KEY_USER_TOKEN, token);
        editor.putString(KEY_TOUR_GUIDE_DESCRIPTION, description);
        editor.putString(KEY_TOUR_GUIDE_LANGUAGES, languages);

        editor.commit();

    }

    // function for updating details
    public void updateProfile(String name, String phone, String description, String languages){
        editor.putString(KEY_USER_NAME, name);
        editor.putString(KEY_USER_PHONE, phone);
        editor.putString(KEY_TOUR_GUIDE_DESCRIPTION, description);
        editor.putString(KEY_TOUR_GUIDE_LANGUAGES, languages);
        editor.commit();
    }


    public boolean loggedIn(){
        return pref.getBoolean("loggedInMode", false);
    }

    // function for getting user details
    public HashMap<String, String> getUserDetails(){
        HashMap<String, String> user = new HashMap<String, String>();
        // user email id
        user.put(KEY_USER_ID, pref.getString(KEY_USER_ID, null));
        user.put(KEY_USER_NAME, pref.getString(KEY_USER_NAME, null));
        user.put(KEY_USER_EMAIL, pref.getString(KEY_USER_EMAIL, null));
        user.put(KEY_USER_TOKEN, pref.getString(KEY_USER_TOKEN, null));
        user.put(KEY_USER_PHONE, pref.getString(KEY_USER_PHONE, null));
        user.put(KEY_TOUR_GUIDE_ID, pref.getString(KEY_TOUR_GUIDE_ID, null));
        user.put(KEY_USER_ROLE, pref.getString(KEY_USER_ROLE, null));
        user.put(KEY_TOUR_GUIDE_DESCRIPTION, pref.getString(KEY_TOUR_GUIDE_DESCRIPTION, null));
        user.put(KEY_TOUR_GUIDE_LANGUAGES, pref.getString(KEY_TOUR_GUIDE_LANGUAGES, null));

        // return user
        return user;
    }

    // function for logging out user
    public void logoutUser(){
        // Clearing all data from Shared Preferences
        editor.clear();
        editor.commit();

        // After logout redirect user to Loing Activity
        Intent i = new Intent(ctx, LoginActivity.class);
        // Closing all the Activities
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        // Add new Flag to start new Activity
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        // Staring Login Activity
        ctx.startActivity(i);
    }

    // function for showing toast
    public void showToast(String msg){
        LayoutInflater inflater =  (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layout = inflater.inflate(R.layout.custom_toast,
                (ViewGroup) activity.findViewById(R.id.custom_toast_container));

        TextView text = layout.findViewById(R.id.text);
        text.setText(msg);

        Toast toast = new Toast(ctx);
        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(layout);
        toast.show();
    }

}


