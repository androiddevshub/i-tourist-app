package com.example.itouristapp.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class User {

    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("email")
    @Expose
    private String email;

    @SerializedName("phone")
    @Expose
    private String phone;

    @SerializedName("tour_guide_id")
    @Expose
    private String tour_guide_id;

    @SerializedName("role")
    @Expose
    private String role;

    @SerializedName("active")
    @Expose
    private Boolean active;

    @SerializedName("password")
    @Expose
    private String password;

    @SerializedName("token")
    @Expose
    private String token;

    public User() {
    }

    public User(String name, String email, String phone, String role, String password) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.role = role;
        this.password = password;
    }

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public User(String name, String email, String phone) {
        this.name = name;
        this.email = email;
        this.phone = phone;
    }

    public String getId() {
        return id;
    }

    public void setId(String user_id) {
        this.id = user_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public String getTour_guide_id() {
        return tour_guide_id;
    }

    public void setTour_guide_id(String tour_guide_id) {
        this.tour_guide_id = tour_guide_id;
    }
}
