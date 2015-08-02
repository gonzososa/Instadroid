package com.outlook.gonzasosa.instadroid.models.media;

import android.support.annotation.Nullable;

import com.google.gson.annotations.SerializedName;
import com.outlook.gonzasosa.instadroid.ui.Utils;

public class Location {
    @Nullable
    @SerializedName(Utils.MEDIAELEMENT_SERIALIZED_NAME_LATITUDE)
    private double latitude;

    @Nullable
    @SerializedName (Utils.MEDIAELEMENT_SERIALIZED_NAME_LONGITUDE)
    private double longitude;

    @Nullable
    @SerializedName (Utils.MEDIAELEMENT_SERIALIZED_NAME_ID)
    private long id;

    @Nullable
    @SerializedName (Utils.MEDIAELEMENT_SERIALIZED_NAME_STREET_ADDRESS)
    private String streetAddress;

    @Nullable
    @SerializedName (Utils.MEDIAELEMENT_SERIALIZED_NAME_NAME)
    private String name;

    public String getStreetAddress () {
        return streetAddress;
    }

    public void setStreetAddress (String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public long getId() {
        return id;
    }

    public void setId (long id) {
        this.id = id;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}