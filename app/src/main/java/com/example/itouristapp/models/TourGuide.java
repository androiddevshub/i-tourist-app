package com.example.itouristapp.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TourGuide implements Parcelable{

    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("description")
    @Expose
    private String description;

    @SerializedName("languages")
    @Expose
    private String languages;


    protected TourGuide(Parcel in) {
        id = in.readString();
        name = in.readString();
        description = in.readString();
        languages = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(name);
        dest.writeString(description);
        dest.writeString(languages);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<TourGuide> CREATOR = new Creator<TourGuide>() {
        @Override
        public TourGuide createFromParcel(Parcel in) {
            return new TourGuide(in);
        }

        @Override
        public TourGuide[] newArray(int size) {
            return new TourGuide[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLanguages() {
        return languages;
    }

    public void setLanguages(String languages) {
        this.languages = languages;
    }
}
