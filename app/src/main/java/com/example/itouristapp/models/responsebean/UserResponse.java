package com.example.itouristapp.models.responsebean;

import com.example.itouristapp.models.User;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserResponse {

    @SerializedName("data")
    @Expose
    private User user;

    @SerializedName("message")
    @Expose
    private String message;

    @SerializedName("status")
    @Expose
    private boolean status;

    public UserResponse() {
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
