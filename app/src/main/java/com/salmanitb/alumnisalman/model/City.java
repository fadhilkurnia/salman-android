package com.salmanitb.alumnisalman.model;

import com.google.gson.annotations.SerializedName;

public class City {

    @SerializedName("kota")
    private String name;
    @SerializedName("latitude")
    private double latitute;
    @SerializedName("longitude")
    private double longitude;

    public City() {
        this.name = "Indonesia";
    }

    public City(String name, double latitude, double longitude) {
        this.name = name;
        this.latitute = latitude;
        this.longitude = longitude;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getLatitute() {
        return latitute;
    }

    public void setLatitute(double latitute) {
        this.latitute = latitute;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}
