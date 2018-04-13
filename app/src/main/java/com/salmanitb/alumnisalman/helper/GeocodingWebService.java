package com.salmanitb.alumnisalman.helper;

import com.salmanitb.alumnisalman.model.GeocodingResponse;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Fadhil Imam Kurnia on 13/04/2018.
 */

public interface GeocodingWebService {
    String BASE_URL = "https://maps.googleapis.com/";

    @GET("maps/api/geocode/json")
    Call<GeocodingResponse> checkAddress(String address);

}
