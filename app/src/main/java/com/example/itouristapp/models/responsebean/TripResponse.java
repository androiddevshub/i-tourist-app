package com.example.itouristapp.models.responsebean;

import com.example.itouristapp.models.Trip;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class TripResponse {

    @SerializedName("data")
    @Expose
    private ArrayList<Trip> tripArrayList;

    @SerializedName("status")
    @Expose
    private boolean status;


    public ArrayList<Trip> getTripArrayList() {
        return tripArrayList;
    }

    public void setTripArrayList(ArrayList<Trip> tripArrayList) {
        this.tripArrayList = tripArrayList;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
