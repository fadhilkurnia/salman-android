package com.salmanitb.alumnisalman.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Fadhil Imam Kurnia on 07/04/2018.
 */

public class About {
    @SerializedName("text_about")
    String about;
    @SerializedName("text_alamat")
    String address;
    @SerializedName("text_no_hp")
    String phone;
    @SerializedName("text_email")
    String email;
    @SerializedName("detail")
    String error;


    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
