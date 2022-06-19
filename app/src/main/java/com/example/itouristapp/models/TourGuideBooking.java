package com.example.itouristapp.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class TourGuideBooking implements Parcelable {

    @SerializedName("id")
    @Expose
    private Integer id;

    @SerializedName("destination_name")
    @Expose
    private String destination_name;

    @SerializedName("destination_location")
    @Expose
    private String destination_location;

    @SerializedName("tourist_id")
    @Expose
    private Integer tourist_id;

    @SerializedName("tourist_name")
    @Expose
    private String tourist_name;

    @SerializedName("tourist_phone")
    @Expose
    private String tourist_phone;


    protected TourGuideBooking(Parcel in) {
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readInt();
        }
        destination_name = in.readString();
        destination_location = in.readString();
        if (in.readByte() == 0) {
            tourist_id = null;
        } else {
            tourist_id = in.readInt();
        }
        tourist_name = in.readString();
        tourist_phone = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (id == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(id);
        }
        dest.writeString(destination_name);
        dest.writeString(destination_location);
        if (tourist_id == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(tourist_id);
        }
        dest.writeString(tourist_name);
        dest.writeString(tourist_phone);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<TourGuideBooking> CREATOR = new Creator<TourGuideBooking>() {
        @Override
        public TourGuideBooking createFromParcel(Parcel in) {
            return new TourGuideBooking(in);
        }

        @Override
        public TourGuideBooking[] newArray(int size) {
            return new TourGuideBooking[size];
        }
    };

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDestination_name() {
        return destination_name;
    }

    public void setDestination_name(String destination_name) {
        this.destination_name = destination_name;
    }

    public String getDestination_location() {
        return destination_location;
    }

    public void setDestination_location(String destination_location) {
        this.destination_location = destination_location;
    }

    public Integer getTourist_id() {
        return tourist_id;
    }

    public void setTourist_id(Integer tourist_id) {
        this.tourist_id = tourist_id;
    }

    public String getTourist_name() {
        return tourist_name;
    }

    public void setTourist_name(String tourist_name) {
        this.tourist_name = tourist_name;
    }

    public String getTourist_phone() {
        return tourist_phone;
    }

    public void setTourist_phone(String tourist_phone) {
        this.tourist_phone = tourist_phone;
    }
}
