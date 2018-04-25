package com.salmanitb.alumnisalman.helper;

import android.media.Image;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.salmanitb.alumnisalman.model.About;
import com.salmanitb.alumnisalman.model.BaseResponse;
import com.salmanitb.alumnisalman.model.CheckEmailResponse;
import com.salmanitb.alumnisalman.model.City;
import com.salmanitb.alumnisalman.model.MessageResponse;
import com.salmanitb.alumnisalman.model.Post;
import com.salmanitb.alumnisalman.model.ProfileResponse;
import com.salmanitb.alumnisalman.model.User;
import com.salmanitb.alumnisalman.model.SearchUserResponse;
import com.salmanitb.alumnisalman.model.UserAuth;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Fadhil Imam Kurnia on 07/04/2018.
 */

public interface WebService {
    String BASE_URL = "http://pplk2h.if.itb.ac.id/api/";
    String BASE_IMAGE_URL = "";

    @Multipart
    @POST("user/update/image")
    Call<BaseResponse<MessageResponse>> uploadFile(
            @Header("um") String authToken,
            @Part MultipartBody.Part foto,
            @Part("id") RequestBody id
    );

    @FormUrlEncoded
    @POST("login")
    Call<BaseResponse<UserAuth>> doLogin(@Field("email") String email,
                                 @Field("password") String hashedPassword);

    @POST("logout")
    Call<BaseResponse<MessageResponse>> doLogout(@Header("um") String authToken);

    @FormUrlEncoded
    @POST("register")
    Call<BaseResponse<UserAuth>> doRegister(
            @Field("nama") String name,
            @Field("email") String email,
            @Field("password") String hashedPassword,
            @Field("gender") String sex,
            @Field("negara") String country,
            @Field("kota") String city,
            @Field("alamat") String address,
            @Field("latitude") double latitude,
            @Field("longitude") double longitude,
            @Field("no_hp") String phone,
            @Field("univ") String university,
            @Field("jurusan") String major,
            @Field("ang_kuliah") String yearUniversity,
            @Field("ang_LMD") String lmd,
            @Field("pekerjaan") String job,
            @Field("instansi") String company,
            @Field("aktifitas") String activity,
            @Field("tahun_aktif") String yearActivity,
            @Field("pertanyaan1") String question1,
            @Field("pertanyaan2") String question2,
            @Field("jawaban1") String answer1,
            @Field("jawaban2") String answer2);

    @FormUrlEncoded
    @POST("user/update")
    Call<BaseResponse<UserAuth>> doUpdate(
            @Field("id") String id,
            @Field("nama") String name,
            @Field("email") String email,
            @Field("gender") String sex,
            @Field("negara") String country,
            @Field("kota") String city,
            @Field("alamat") String address,
            @Field("latitude") double latitude,
            @Field("longitude") double longitude,
            @Field("no_hp") String phone,
            @Field("univ") String university,
            @Field("jurusan") String major,
            @Field("ang_kuliah") String yearUniversity,
            @Field("ang_LMD") String lmd,
            @Field("pekerjaan") String job,
            @Field("instansi") String company,
            @Field("aktifitas") String activity,
            @Field("tahun_aktif") String yearActivity);

    @FormUrlEncoded
    @POST("email")
    Call<BaseResponse<CheckEmailResponse>> checkEmail(@Field("email") String email);

    @GET("user/get/{id}")
    Call<BaseResponse<ProfileResponse>> getProfil(@Header("um") String authKey, @Path("id") int uid, @Query("format") String format);

    @GET("menyapa/page/{page}?format=json")
    Call<BaseResponse<ArrayList<Post>>> getSalmanMenyapa(@Header("um") String authKey, @Path("page") int page);

    @GET("menyapa/get?format=json")
    Call<BaseResponse<Post>> getSalmanMenyapaDetail(@Header("um") String authKey, @Query("q") int postId, @Query("w") int uid);

    @GET("menyapa/like?format=json")
    Call<BaseResponse<String>> doLoveSalmanMenyapa(@Header("um") String authKey, @Query("q") int postId, @Query("w") int uid);

    @GET("about")
    Call<BaseResponse<About>> getAbout(@Header("um") String authKey, @Query("format") String format);

    @GET("search")
    Call<BaseResponse<ArrayList<SearchUserResponse>>> searchUser(@Header("um") String authKey, @Query("q") String format);

    @GET("persebaran")
    Call<BaseResponse<ArrayList<City>>> getAlumniMapping(@Header("um") String authKey);

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
            builder.readTimeout(20, TimeUnit.SECONDS);
            builder.connectTimeout(20, TimeUnit.SECONDS);
            builder.writeTimeout(20, TimeUnit.SECONDS);

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
