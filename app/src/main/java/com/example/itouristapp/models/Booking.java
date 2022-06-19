package com.example.itouristapp.models;

import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Booking{

    @SerializedName("user_id")
    @Expose
    private Integer user_id;

    @SerializedName("tour_guide_id")
    @Expose
    private Integer tour_guide_id;

    @SerializedName("destination_id")
    @Expose
    private Integer destination_id;

    public Booking(Integer user_id, Integer tour_guide_id, Integer destination_id) {
        this.user_id = user_id;
        this.tour_guide_id = tour_guide_id;
        this.destination_id = destination_id;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Integer getTour_guide_id() {
        return tour_guide_id;
    }

    public void setTour_guide_id(Integer tour_guide_id) {
        this.tour_guide_id = tour_guide_id;
    }

    public Integer getDestination_id() {
        return destination_id;
    }

    public void setDestination_id(Integer destination_id) {
        this.destination_id = destination_id;
    }
}
