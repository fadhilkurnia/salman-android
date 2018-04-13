package com.salmanitb.alumnisalman.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Fadhil Imam Kurnia on 13/04/2018.
 */

public class GeocodingResult {
    @SerializedName("address_component")
    GeocodingAddressComponent addressComponent[];
    @SerializedName("formatted_address")
    String formattedAddress;
    @SerializedName("geometry")
    GeocodingGeometry geometry;
    @SerializedName("place_id")
    String placeId;
    @SerializedName("types")
    String[] types;

    public GeocodingAddressComponent[] getAddressComponent() {
        return addressComponent;
    }

    public void setAddressComponent(GeocodingAddressComponent[] addressComponent) {
        this.addressComponent = addressComponent;
    }

    public String getFormattedAddress() {
        return formattedAddress;
    }

    public void setFormattedAddress(String formattedAddress) {
        this.formattedAddress = formattedAddress;
    }

    public GeocodingGeometry getGeometry() {
        return geometry;
    }

    public void setGeometry(GeocodingGeometry geometry) {
        this.geometry = geometry;
    }

    public String getPlaceId() {
        return placeId;
    }

    public void setPlaceId(String placeId) {
        this.placeId = placeId;
    }

    public String[] getTypes() {
        return types;
    }

    public void setTypes(String[] types) {
        this.types = types;
    }
}
