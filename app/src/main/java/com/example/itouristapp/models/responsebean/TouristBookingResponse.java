package com.example.itouristapp.models.responsebean;

import com.example.itouristapp.models.TouristBooking;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class TouristBookingResponse {

    @SerializedName("data")
    @Expose
    private ArrayList<TouristBooking> touristBookingArrayList;

    @SerializedName("status")
    @Expose
    private boolean status;

    public ArrayList<TouristBooking> getTouristBookingArrayList() {
        return touristBookingArrayList;
    }

    public void setTouristBookingArrayList(ArrayList<TouristBooking> touristBookingArrayList) {
        this.touristBookingArrayList = touristBookingArrayList;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
