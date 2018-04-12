package com.salmanitb.alumnisalman.helper;

import android.support.annotation.NonNull;
import android.util.Log;

import com.salmanitb.alumnisalman.model.About;
import com.salmanitb.alumnisalman.model.BaseResponse;
import com.salmanitb.alumnisalman.model.CheckEmailResponse;
import com.salmanitb.alumnisalman.model.UserAuth;

import java.math.BigInteger;
import java.net.ConnectException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

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

    public void getAbout(final ApiCallback<About> callback) {
        WebService.APIServiceImplementation.getInstance().getAbout().enqueue(new Callback<About>() {
            @Override
            public void onResponse(@NonNull Call<About> call, @NonNull Response<About> response) {
                if (response.body() == null) {
                    callback.onFailure(new Throwable("Empty response"));
                    return;
                }
                callback.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<About> call, Throwable t) {
                callback.onFailure(t);
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
