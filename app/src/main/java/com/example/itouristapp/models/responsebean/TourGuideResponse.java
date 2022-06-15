package com.example.itouristapp.models.responsebean;

import com.example.itouristapp.models.TourGuide;
import com.example.itouristapp.models.Trip;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class TourGuideResponse {

    @SerializedName("data")
    @Expose
    private ArrayList<TourGuide> tourGuideArrayList;

    @SerializedName("status")
    @Expose
    private boolean status;

    public ArrayList<TourGuide> getTourGuideArrayList() {
        return tourGuideArrayList;
    }

    public void setTourGuideArrayList(ArrayList<TourGuide> tourGuideArrayList) {
        this.tourGuideArrayList = tourGuideArrayList;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
