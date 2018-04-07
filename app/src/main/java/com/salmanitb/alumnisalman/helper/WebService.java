package com.salmanitb.alumnisalman.helper;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.salmanitb.alumnisalman.model.About;
import com.salmanitb.alumnisalman.model.BaseResponse;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by Fadhil Imam Kurnia on 07/04/2018.
 */

public interface WebService {
    String BASE_URL = "http://pplk2h.if.itb.ac.id/api/";

    @POST("login")
    BaseResponse<String> doLogin(@Field("email") String email,
                                 @Field("password") String hashedPassword,
                                 Callback<BaseResponse<String>> callback);

    @GET("abouts/1/?format=json")
    Call<About> getAbout();


    public class APIServiceImplementation {
        private static WebService webService;

        public static WebService getInstance() {
            if (webService == null) {
                webService = new APIServiceImplementation().create();
            }
            return webService;
        }

        private WebService create() {

            OkHttpClient.Builder builder = new OkHttpClient().newBuilder();
            builder.readTimeout(60, TimeUnit.SECONDS);
            builder.connectTimeout(60, TimeUnit.SECONDS);
            builder.writeTimeout(60, TimeUnit.SECONDS);

            Gson gson = new GsonBuilder().create();

            OkHttpClient client = builder.build();

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(WebService.BASE_URL)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();

            return retrofit.create(WebService.class);
        }

    }

}
