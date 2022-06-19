package com.example.itouristapp.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Review {

    @SerializedName("id")
    @Expose
    private Integer id;

    @SerializedName("user_name")
    @Expose
    private String user_name;

    @SerializedName("description")
    @Expose
    private String description;

    @SerializedName("user_id")
    @Expose
    private Integer user_id;

    @SerializedName("tour_guide_id")
    @Expose
    private Integer tour_guide_id;

    public Review(String description, Integer user_id, Integer tour_guide_id) {
        this.description = description;
        this.user_id = user_id;
        this.tour_guide_id = tour_guide_id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
}
