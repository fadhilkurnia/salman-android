package com.salmanitb.alumnisalman.helper;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.salmanitb.alumnisalman.model.About;
import com.salmanitb.alumnisalman.model.BaseResponse;
import com.salmanitb.alumnisalman.model.CheckEmailResponse;
import com.salmanitb.alumnisalman.model.UserAuth;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by Fadhil Imam Kurnia on 07/04/2018.
 */

public interface WebService {
    String BASE_URL = "http://pplk2h.if.itb.ac.id/api/";

    @FormUrlEncoded
    @POST("login")
    Call<BaseResponse<UserAuth>> doLogin(@Field("email") String email,
                                 @Field("password") String hashedPassword);

    @FormUrlEncoded
    @POST("email")
    Call<BaseResponse<CheckEmailResponse>> checkEmail(@Field("email") String email);


    @GET("about")
    Call<BaseResponse<About>> getAbout(@Query("format") String format);


    public class APIServiceImplementation {
        private static WebService webService;
        private static GeocodingWebService geocodingWebService;

        public static WebService getInstance() {
            if (webService == null) {
                webService = new APIServiceImplementation().create();
            }
            return webService;
        }

        public static GeocodingWebService getGeocodingInstance() {
            if (geocodingWebService == null)
                geocodingWebService = new APIServiceImplementation().createGeocodingWebService();

            return geocodingWebService;
        }

        private WebService create() {
            OkHttpClient.Builder builder = new OkHttpClient().newBuilder();
            builder.readTimeout(60, TimeUnit.SECONDS);
            builder.connectTimeout(60, TimeUnit.SECONDS);
            builder.writeTimeout(60, TimeUnit.SECONDS);

            Gson gson = new GsonBuilder().serializeNulls().create();

            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

            OkHttpClient client = builder.addInterceptor(interceptor).build();

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(WebService.BASE_URL)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();

            return retrofit.create(WebService.class);
        }

        private GeocodingWebService createGeocodingWebService() {
            OkHttpClient.Builder builder = new OkHttpClient().newBuilder();
            builder.readTimeout(60, TimeUnit.SECONDS);
            builder.connectTimeout(60, TimeUnit.SECONDS);
            builder.writeTimeout(60, TimeUnit.SECONDS);

            Gson gson = new GsonBuilder().serializeNulls().create();

            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

            OkHttpClient client = builder.addInterceptor(interceptor).build();

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(GeocodingWebService.BASE_URL)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();

            return retrofit.create(GeocodingWebService.class);
        }

    }

}
