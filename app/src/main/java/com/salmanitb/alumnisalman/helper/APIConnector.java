package com.salmanitb.alumnisalman.helper;

import android.support.annotation.NonNull;

import com.salmanitb.alumnisalman.model.About;
import com.salmanitb.alumnisalman.model.BaseResponse;

import java.math.BigInteger;
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


    public void doLogin(final String email, final String password, final ApiCallback<String> callback) {
        String hashedPassword = getMD5(password);
        WebService.APIServiceImplementation.getInstance().doLogin(email, hashedPassword, new Callback<BaseResponse<String>>() {
            @Override
            public void onResponse(@NonNull Call<BaseResponse<String>> call, @NonNull Response<BaseResponse<String>> response) {
                BaseResponse<String> responseBody = response.body();
                if (responseBody == null) {
                    callback.onFailure(new Throwable("Error"));
                    return;
                }

                if (responseBody.getError() == null) {
                    callback.onFailure(new Throwable(responseBody.getError().getMessage()));
                } else {
                    String token = responseBody.getData();
                    callback.onSuccess(token);
                }
            }

            @Override
            public void onFailure(@NonNull Call<BaseResponse<String>> call, @NonNull Throwable t) {
                callback.onFailure(t);
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


    private static String getMD5(String input) {
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
