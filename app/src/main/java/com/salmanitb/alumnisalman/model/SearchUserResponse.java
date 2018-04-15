package com.salmanitb.alumnisalman.model;

import com.google.gson.annotations.SerializedName;

public class SearchUserResponse {

    @SerializedName("nama")
    String name;
    @SerializedName("profile_image")
    String url_img;
    @SerializedName("email")
    String email;
    @SerializedName("kota")
    String city;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrlImg() {
        return url_img;
    }

    public void setUrlImg(String url_img) {
        this.url_img = url_img;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
