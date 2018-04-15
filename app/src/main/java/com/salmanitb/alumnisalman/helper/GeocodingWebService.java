package com.salmanitb.alumnisalman.helper;

import com.salmanitb.alumnisalman.model.GeocodingResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Fadhil Imam Kurnia on 13/04/2018.
 */

public interface GeocodingWebService {
    String BASE_URL = "https://maps.googleapis.com/";
    String GOOGLE_KEY = "AIzaSyC_4iTLKM1ZZYjM-7OxCOAxFFtcNKQOiKA";
    String DEFAULT_LANGUAGE = "id";

    @GET("maps/api/geocode/json")
    Call<GeocodingResponse> checkAddress(@Query("key") String key, @Query("address") String address, @Query("language") String language);

}
