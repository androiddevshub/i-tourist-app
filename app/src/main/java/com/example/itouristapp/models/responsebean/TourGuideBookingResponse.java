package com.example.itouristapp.models.responsebean;

import com.example.itouristapp.models.TourGuideBooking;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class TourGuideBookingResponse {

    @SerializedName("data")
    @Expose
    private ArrayList<TourGuideBooking> tourGuideBookingArrayList;

    @SerializedName("status")
    @Expose
    private boolean status;

    public ArrayList<TourGuideBooking> getTourGuideBookingArrayList() {
        return tourGuideBookingArrayList;
    }

    public void setTourGuideBookingArrayList(ArrayList<TourGuideBooking> tourGuideBookingArrayList) {
        this.tourGuideBookingArrayList = tourGuideBookingArrayList;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
