package com.salmanitb.alumnisalman.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Fadhil Imam Kurnia on 13/04/2018.
 */

public class GeocodingResponse {
    @SerializedName("results")
    GeocodingResult[] results;
    @SerializedName("status")
    String status;

    public GeocodingResult[] getResults() {
        return results;
    }

    public void setResults(GeocodingResult[] results) {
        this.results = results;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
