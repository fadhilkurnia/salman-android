package com.salmanitb.alumnisalman.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Fadhil Imam Kurnia on 13/04/2018.
 */

public class GeocodingArea {
    @SerializedName("northeast")
    GeocodingArea northeast;
    @SerializedName("southwest")
    GeocodingArea southwest;

    public GeocodingArea getNortheast() {
        return northeast;
    }

    public void setNortheast(GeocodingArea northeast) {
        this.northeast = northeast;
    }

    public GeocodingArea getSouthwest() {
        return southwest;
    }

    public void setSouthwest(GeocodingArea southwest) {
        this.southwest = southwest;
    }
}
