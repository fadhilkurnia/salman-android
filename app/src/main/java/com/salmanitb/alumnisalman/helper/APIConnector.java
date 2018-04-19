package com.salmanitb.alumnisalman.helper;

import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.salmanitb.alumnisalman.model.About;
import com.salmanitb.alumnisalman.model.BaseResponse;
import com.salmanitb.alumnisalman.model.CheckEmailResponse;
import com.salmanitb.alumnisalman.model.City;
import com.salmanitb.alumnisalman.model.GeocodingResponse;
import com.salmanitb.alumnisalman.model.Post;
import com.salmanitb.alumnisalman.model.ProfileResponse;
import com.salmanitb.alumnisalman.model.SalmanActivity;
import com.salmanitb.alumnisalman.model.User;
import com.salmanitb.alumnisalman.model.SearchUserResponse;
import com.salmanitb.alumnisalman.model.UserAuth;

import java.io.IOException;
import java.lang.reflect.Array;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Fadhil Imam Kurnia on 01/04/2018.
 */

public class APIConnector{
    private static String BASE_URL = "http://pplk2h.if.itb.ac.id";
    private static APIConnector apiConnector;

    public static APIConnector getInstance() {
        if (apiConnector == null)
            apiConnector = new APIConnector();
        return apiConnector;
    }

    public interface ApiCallback<T> {
        void onSuccess(T response);
        void onFailure(Throwable t);
    }


    public void doLogin(final String email, final String password, final ApiCallback<UserAuth> callback) {
        String hashedPassword = getMD5(password);

        Call<BaseResponse<UserAuth>> call = WebService.APIServiceImplementation.getInstance().doLogin(email, hashedPassword);
        call.enqueue(new Callback<BaseResponse<UserAuth>>() {
            @Override
            public void onResponse(Call<BaseResponse<UserAuth>> call, Response<BaseResponse<UserAuth>> response) {
                BaseResponse<UserAuth> responseBody = response.body();
                if (responseBody == null) {
                    callback.onFailure(new Throwable("Terjadi kesalahan pada sistem kami"));
                    return;
                }

                if (!responseBody.isSuccess()) {
                    if (responseBody.getError() != null)
                        callback.onFailure(new Throwable(responseBody.getError().getMessage()));
                    else
                        callback.onFailure(new Throwable("Terjadi kesalahan!"));
                } else {
                    callback.onSuccess(responseBody.getData());
                }
            }

            @Override
            public void onFailure(Call<BaseResponse<UserAuth>> call, Throwable t) {
                Log.e("Error", t.getMessage());
                callback.onFailure(new Throwable("Periksa koneksi anda!"));
            }
        });
    }

    public void doRegister(final User user, final ApiCallback<UserAuth> callback) {
        String hashedPassword = getMD5(user.getPassword());
        Gson gson = new Gson();
        ArrayList<String> activities = new ArrayList<>();
        ArrayList<String> activitiesYear = new ArrayList<>();
        for (SalmanActivity activity : user.getActivities()) {
            activities.add(activity.getTitle());
            StringBuilder sb = new StringBuilder();
            sb.append(activity.getStartYear());
            if (!activity.getEndYear().trim().equals(""))
                sb.append("-");
            sb.append(activity.getEndYear());
            activitiesYear.add(sb.toString());
        }
        Call<BaseResponse<UserAuth>> call = WebService.APIServiceImplementation.getInstance().doRegister(
                user.getName(),
                user.getEmail(),
                hashedPassword,
                user.getSex(),
                user.getCountry(),
                user.getCity(),
                user.getAddress(),
                user.getLatitude(),
                user.getLongitude(),
                user.getPhonenumber(),
                user.getUniversity(),
                user.getMajor(),
                user.getYearUniversity(),
                user.getLmd(),
                user.getJob(),
                user.getCompany(),
                gson.toJson(activities),
                gson.toJson(activitiesYear),
                user.getQuestion1(),
                user.getQuestion2(),
                user.getAnswer1(),
                user.getAnswer2()
        );

        call.enqueue(new Callback<BaseResponse<UserAuth>>() {
            @Override
            public void onResponse(Call<BaseResponse<UserAuth>> call, Response<BaseResponse<UserAuth>> response) {
                BaseResponse<UserAuth> responseBody = response.body();
                if (responseBody != null) {
                    if (!responseBody.isSuccess()) {
                        callback.onFailure(new Throwable(responseBody.getError().getMessage()));
                        return;
                    }
                    callback.onSuccess(responseBody.getData());
                    return;
                }
                callback.onFailure(new Throwable("Terjadi kesahalah sistem, coba beberapa saat lagi"));
            }

            @Override
            public void onFailure(Call<BaseResponse<UserAuth>> call, Throwable t) {
                Log.e("Error", t.getMessage());
                callback.onFailure(new Throwable("Periksa koneksi anda!"));
            }
        });
    }

    public void doUpdate(final User user, final ApiCallback<UserAuth> callback) {
        Gson gson = new Gson();
        ArrayList<String> activities = new ArrayList<>();
        ArrayList<String> activitiesYear = new ArrayList<>();
        for (SalmanActivity activity : user.getActivities()) {
            activities.add(activity.getTitle());
            StringBuilder sb = new StringBuilder();
            sb.append(activity.getStartYear());
            if (!activity.getEndYear().trim().equals(""))
                sb.append("-");
            sb.append(activity.getEndYear());
            activitiesYear.add(sb.toString());
        }

        String uid = String.valueOf(user.getUid());
        Log.d("UID_UPDATE", uid);

        Call<BaseResponse<UserAuth>> call = WebService.APIServiceImplementation.getInstance().doUpdate(
                uid,
                user.getName(),
                user.getEmail(),
                user.getSex(),
                user.getCountry(),
                user.getCity(),
                user.getAddress(),
                user.getLatitude(),
                user.getLongitude(),
                user.getPhonenumber(),
                user.getUniversity(),
                user.getMajor(),
                user.getYearUniversity(),
                user.getLmd(),
                user.getJob(),
                user.getCompany(),
                gson.toJson(activities),
                gson.toJson(activitiesYear)
        );

        call.enqueue(new Callback<BaseResponse<UserAuth>>() {
            @Override
            public void onResponse(Call<BaseResponse<UserAuth>> call, Response<BaseResponse<UserAuth>> response) {
                BaseResponse<UserAuth> responseBody = response.body();
                if (responseBody != null) {
                    if (!responseBody.isSuccess()) {
                        callback.onFailure(new Throwable(responseBody.getError().getMessage()));
                        return;
                    }
                    callback.onSuccess(responseBody.getData());
                    return;
                }
                callback.onFailure(new Throwable("Terjadi kesahalah sistem, coba beberapa saat lagi"));
            }

            @Override
            public void onFailure(Call<BaseResponse<UserAuth>> call, Throwable t) {
                Log.e("Error", t.getMessage());
                callback.onFailure(new Throwable("Periksa koneksi anda!"));
            }
        });
    }

    public void checkEmail(String email, final ApiCallback<CheckEmailResponse> callback) {
        Call<BaseResponse<CheckEmailResponse>> call = WebService.APIServiceImplementation.getInstance().checkEmail(email);
        call.enqueue(new Callback<BaseResponse<CheckEmailResponse>>() {
            @Override
            public void onResponse(Call<BaseResponse<CheckEmailResponse>> call, Response<BaseResponse<CheckEmailResponse>> response) {
                BaseResponse<CheckEmailResponse> responseBody = response.body();
                if (responseBody == null) {
                    callback.onFailure(new Throwable("Terjadi kesalahan pada sistem kami"));
                    return;
                }

                if (!responseBody.isSuccess()) {
                    if (responseBody.getError() != null)
                        callback.onFailure(new Throwable(responseBody.getError().getMessage()));
                    else
                        callback.onFailure(new Throwable("Terjadi kesalahan!"));
                } else {
                    callback.onSuccess(responseBody.getData());
                }
            }

            @Override
            public void onFailure(Call<BaseResponse<CheckEmailResponse>> call, Throwable t) {
                Log.e("Error", t.getMessage());
                callback.onFailure(new Throwable("Periksa koneksi anda!"));
            }
        });
    }

    public void getProfil(int uid, final ApiCallback<User> callback) {
        WebService.APIServiceImplementation.getInstance().getProfil(uid, "json").enqueue(new Callback<BaseResponse<ProfileResponse>>() {
            @Override
            public void onResponse(Call<BaseResponse<ProfileResponse>> call, Response<BaseResponse<ProfileResponse>> response) {
                if (response.body() == null) {
                    callback.onFailure(new Throwable("Terjadi kesalahan sistem"));
                    return;
                }
                if (!response.body().isSuccess()) {
                    callback.onFailure(new Throwable(response.body().getError().getMessage()));
                    return;
                }
                ProfileResponse user = response.body().getData();
                user.decodeActivityData();
                callback.onSuccess(user);
            }

            @Override
            public void onFailure(Call<BaseResponse<ProfileResponse>> call, Throwable t) {
                Log.e("Error", t.getMessage());
                callback.onFailure(new Throwable("Periksa koneksi anda!"));
            }
        });
    }

    public void getAlumniMapping(final ApiCallback<ArrayList<City>> callback) {
        Call<BaseResponse<ArrayList<City>>> call = WebService.APIServiceImplementation.getInstance().getAlumniMapping();
        call.enqueue(new Callback<BaseResponse<ArrayList<City>>>() {
            @Override
            public void onResponse(Call<BaseResponse<ArrayList<City>>> call, Response<BaseResponse<ArrayList<City>>> response) {
                BaseResponse<ArrayList<City>> responseBody = response.body();
                if (responseBody == null) {
                    callback.onFailure(new Throwable("Terjadi kesalahan pada sistem kami"));
                    return;
                }

                if (!responseBody.isSuccess()) {
                    if (responseBody.getError() != null)
                        callback.onFailure(new Throwable(responseBody.getError().getMessage()));
                    else
                        callback.onFailure(new Throwable("Terjadi kesalahan!"));
                } else {
                    callback.onSuccess(responseBody.getData());
                    Log.e("API Persebaran", responseBody.getData().toString());

                }
            }

            @Override
            public void onFailure(Call<BaseResponse<ArrayList<City>>> call, Throwable t) {
                Log.e("Error", t.getMessage());
                callback.onFailure(new Throwable("Periksa koneksi anda!"));
            }
        });
    }

    public void searchUser(String query, final ApiCallback<ArrayList<SearchUserResponse>> callback) {
        Call<BaseResponse<ArrayList<SearchUserResponse>>> call = WebService.APIServiceImplementation.getInstance().searchUser(query);
        call.enqueue(new Callback<BaseResponse<ArrayList<SearchUserResponse>>>() {
            @Override
            public void onResponse(Call<BaseResponse<ArrayList<SearchUserResponse>>> call, Response<BaseResponse<ArrayList<SearchUserResponse>>> response) {
                BaseResponse<ArrayList<SearchUserResponse>> responseBody = response.body();
                if (responseBody == null) {
                    callback.onFailure(new Throwable("Terjadi kesalahan pada sistem kami"));
                    return;
                }

                if (!responseBody.isSuccess()) {
                    if (responseBody.getError() != null)
                        callback.onFailure(new Throwable(responseBody.getError().getMessage()));
                    else
                        callback.onFailure(new Throwable("Terjadi kesalahan!"));
                } else {
                    callback.onSuccess(responseBody.getData());
                    Log.e("API Search", responseBody.getData().toString());

                }
            }

            @Override
            public void onFailure(Call<BaseResponse<ArrayList<SearchUserResponse>>> call, Throwable t) {
                Log.e("Error", t.getMessage());
                callback.onFailure(new Throwable("Periksa koneksi anda!"));
            }
        });
    }

    public void getSalmanMenyapa(final int page, final ApiCallback<ArrayList<Post>> callback) {
        Call<BaseResponse<ArrayList<Post>>> call = WebService.APIServiceImplementation.getInstance().getSalmanMenyapa(page);
        call.enqueue(new Callback<BaseResponse<ArrayList<Post>>>() {
            @Override
            public void onResponse(Call<BaseResponse<ArrayList<Post>>> call, Response<BaseResponse<ArrayList<Post>>> response) {
                BaseResponse<ArrayList<Post>> responseBody = response.body();
                if (responseBody == null) {
                    callback.onFailure(new Throwable("Terjadi kesalahan sistem"));
                    return;
                }
                if (!responseBody.isSuccess()) {
                    callback.onFailure(new Throwable(responseBody.getError().getMessage()));
                    return;
                }
                callback.onSuccess(responseBody.getData());
            }

            @Override
            public void onFailure(Call<BaseResponse<ArrayList<Post>>> call, Throwable t) {
                Log.e("Error", t.getMessage());
                callback.onFailure(new Throwable("Periksa koneksi anda!"));
            }
        });
    }

    public void getSalmanMenyapaDetail(final int id, final int uid, final ApiCallback<Post> callback) {
        Call<BaseResponse<Post>> call = WebService.APIServiceImplementation.getInstance().getSalmanMenyapaDetail(id, uid);
        call.enqueue(new Callback<BaseResponse<Post>>() {
            @Override
            public void onResponse(Call<BaseResponse<Post>> call, Response<BaseResponse<Post>> response) {
                BaseResponse<Post> responseBody = response.body();
                if (responseBody == null) {
                    callback.onFailure(new Throwable("Terjadi kesalahan sistem"));
                    return;
                }
                if (!responseBody.isSuccess()) {
                    callback.onFailure(new Throwable(responseBody.getError().getMessage()));
                    return;
                }
                callback.onSuccess(responseBody.getData());
            }

            @Override
            public void onFailure(Call<BaseResponse<Post>> call, Throwable t) {
                Log.e("Error", t.getMessage());
                callback.onFailure(new Throwable("Periksa koneksi anda!"));
            }
        });
    }

    public void doLoveSalmanMenyapa(final int postId, final int uid, final ApiCallback<String> callback) {
        Call<BaseResponse<String>> call = WebService.APIServiceImplementation.getInstance().doLoveSalmanMenyapa(postId, uid);
        call.enqueue(new Callback<BaseResponse<String>>() {
            @Override
            public void onResponse(Call<BaseResponse<String>> call, Response<BaseResponse<String>> response) {
                BaseResponse<String> responseBody = response.body();
                if (responseBody == null) {
                    callback.onFailure(new Throwable("Terjadi kesalahan sistem"));
                    return;
                }
                if (!responseBody.isSuccess()) {
                    callback.onFailure(new Throwable(responseBody.getError().getMessage()));
                    return;
                }
                callback.onSuccess(responseBody.getData());
            }

            @Override
            public void onFailure(Call<BaseResponse<String>> call, Throwable t) {
                Log.e("Error", t.getMessage());
                callback.onFailure(new Throwable("Periksa koneksi anda!"));
            }
        });
    }

    public void getAbout(final ApiCallback<About> callback) {
        Call<BaseResponse<About>> call = WebService.APIServiceImplementation.getInstance().getAbout("json");
        call.enqueue(new Callback<BaseResponse<About>>() {
            @Override
            public void onResponse(Call<BaseResponse<About>> call, Response<BaseResponse<About>> response) {
                BaseResponse<About> responseBody = response.body();
                if (responseBody == null) {
                    callback.onFailure(new Throwable("Terjadi kesalahan sistem"));
                    return;
                }
                callback.onSuccess(responseBody.getData());
            }

            @Override
            public void onFailure(Call<BaseResponse<About>> call, Throwable t) {
                Log.e("Error", t.getMessage());
                callback.onFailure(new Throwable("Periksa koneksi anda!"));
            }
        });
    }

    public void checkAddress(final String address, final ApiCallback<GeocodingResponse> callback) {
        Call<GeocodingResponse> call = WebService.APIServiceImplementation
                .getGeocodingInstance()
                .checkAddress(
                        GeocodingWebService.GOOGLE_KEY,
                        address,
                        GeocodingWebService.DEFAULT_LANGUAGE);
        call.enqueue(new Callback<GeocodingResponse>() {
            @Override
            public void onResponse(Call<GeocodingResponse> call, Response<GeocodingResponse> response) {
                GeocodingResponse responseBody = response.body();
                if (responseBody == null || !responseBody.getStatus().equals("OK")) {
                    callback.onFailure(new Throwable("Terjadi kesalahan sistem"));
                    return;
                }
                callback.onSuccess(responseBody);
            }

            @Override
            public void onFailure(Call<GeocodingResponse> call, Throwable t) {
                Log.e("Error", t.getMessage());
                callback.onFailure(new Throwable("Periksa koneksi anda!"));
            }
        });
    }


    public static String getMD5(String input) {
        if (input == null)
            input = "";
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(input.getBytes());
            BigInteger number = new BigInteger(1, messageDigest);
            String hashtext = number.toString(16);
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        }
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }


}
