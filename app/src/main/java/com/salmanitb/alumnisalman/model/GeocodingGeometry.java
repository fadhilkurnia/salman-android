package com.salmanitb.alumnisalman.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Fadhil Imam Kurnia on 13/04/2018.
 */

public class GeocodingGeometry {
    @SerializedName("bounds")
    GeocodingArea bounds;
    @SerializedName("location")
    GeocodingCoordinate location;
    @SerializedName("location_type")
    String locationType;
    @SerializedName("viewport")
    GeocodingArea viewport;

    public GeocodingArea getBounds() {
        return bounds;
    }

    public void setBounds(GeocodingArea bounds) {
        this.bounds = bounds;
    }

    public GeocodingCoordinate getLocation() {
        return location;
    }

    public void setLocation(GeocodingCoordinate location) {
        this.location = location;
    }

    public String getLocationType() {
        return locationType;
    }

    public void setLocationType(String locationType) {
        this.locationType = locationType;
    }

    public GeocodingArea getViewport() {
        return viewport;
    }

    public void setViewport(GeocodingArea viewport) {
        this.viewport = viewport;
    }
}
