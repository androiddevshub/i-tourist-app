package com.example.itouristapp.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class TouristBooking implements Parcelable {

    @SerializedName("id")
    @Expose
    private Integer id;

    @SerializedName("destination_name")
    @Expose
    private String destination_name;

    @SerializedName("destination_location")
    @Expose
    private String destination_location;

    @SerializedName("destination_description")
    @Expose
    private String destination_description;

    @SerializedName("tour_guide_id")
    @Expose
    private Integer tour_guide_id;

    @SerializedName("tour_guide_name")
    @Expose
    private String tour_guide_name;

    @SerializedName("tour_guide_phone")
    @Expose
    private String tour_guide_phone;


    protected TouristBooking(Parcel in) {
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readInt();
        }
        destination_name = in.readString();
        destination_location = in.readString();
        destination_description = in.readString();
        if (in.readByte() == 0) {
            tour_guide_id = null;
        } else {
            tour_guide_id = in.readInt();
        }
        tour_guide_name = in.readString();
        tour_guide_phone = in.readString();
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
        dest.writeString(destination_description);
        if (tour_guide_id == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(tour_guide_id);
        }
        dest.writeString(tour_guide_name);
        dest.writeString(tour_guide_phone);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<TouristBooking> CREATOR = new Creator<TouristBooking>() {
        @Override
        public TouristBooking createFromParcel(Parcel in) {
            return new TouristBooking(in);
        }

        @Override
        public TouristBooking[] newArray(int size) {
            return new TouristBooking[size];
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

    public String getDestination_description() {
        return destination_description;
    }

    public void setDestination_description(String destination_description) {
        this.destination_description = destination_description;
    }

    public Integer getTour_guide_id() {
        return tour_guide_id;
    }

    public void setTour_guide_id(Integer tour_guide_id) {
        this.tour_guide_id = tour_guide_id;
    }

    public String getTour_guide_name() {
        return tour_guide_name;
    }

    public void setTour_guide_name(String tour_guide_name) {
        this.tour_guide_name = tour_guide_name;
    }

    public String getTour_guide_phone() {
        return tour_guide_phone;
    }

    public void setTour_guide_phone(String tour_guide_phone) {
        this.tour_guide_phone = tour_guide_phone;
    }
}
