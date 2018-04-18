package com.salmanitb.alumnisalman.model;

import com.google.gson.annotations.SerializedName;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by Fadhil Imam Kurnia on 12/04/2018.
 */

public class UserAuth {

    @SerializedName("id")
    int id;
    @SerializedName("email")
    String email;
    @SerializedName("password")
    String password;
    @SerializedName("token")
    String token;
    @SerializedName("verified")
    boolean isVerified;

    private transient boolean isPasswordHashed;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public boolean isVerified() {
        return isVerified;
    }

    public void setVerified(boolean verified) {
        isVerified = verified;
    }

}
