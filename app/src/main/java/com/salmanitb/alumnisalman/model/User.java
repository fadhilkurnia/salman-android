package com.salmanitb.alumnisalman.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by hilmi on 13/03/2018.
 */

public class User{

    // Personal Info
    @SerializedName("id")
    private String uid;
    @SerializedName("nama")
    private String name;
    @SerializedName("email")
    private String email;
    @SerializedName("password")
    private transient String password;
    @SerializedName("no_hp")
    private String phonenumber;
    @SerializedName("profile_image")
    private String imageURL;
    @SerializedName("gender")
    private String sex;

    // Address info
    @SerializedName("negara")
    private String country;
    @SerializedName("kota")
    private String city;
    @SerializedName("alamat")
    private String address;
    @SerializedName("latitude")
    private double latitude;
    @SerializedName("longitude")
    private double longitude;

    // Profession Info
    @SerializedName("pekerjaan")
    private String job;
    @SerializedName("instansi")
    private String company;

    // Almamater Info
    @SerializedName("univ")
    private String university;
    @SerializedName("ang_kuliah")
    private String year_university;
    @SerializedName("jurusan")
    private String major;

    // Activity in Salman information
    @SerializedName("ang_LMD")
    private String lmd;
    protected transient ArrayList<SalmanActivity> activities;

    // Q&A information
    private transient String question1;
    private transient String question2;
    private transient String answer1;
    private transient String answer2;

    public User() {
    }

    public User(String uid, String name, String email) {
        this.uid = uid;
        this.name = name;
        this.email = email;
    }

    public User(String uid, String name, String email, String img, String city) {
        this.uid = uid;
        this.name = name;
        this.email = email;
        this.imageURL = img;
        this.city = city;
    }

    public User(String uid, String name, String email, String job) {
        this.uid = uid;
        this.name = name;
        this.email = email;
        this.job = job;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getUniversity() {
        return university;
    }

    public void setUniversity(String university) {
        this.university = university;
    }

    public String getYearUniversity() {
        return year_university;
    }

    public void setYearUniversity(String year_university) {
        this.year_university = year_university;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getLmd() {
        return lmd;
    }

    public void setLmd(String lmd) {
        this.lmd = lmd;
    }

    public ArrayList<SalmanActivity> getActivities() {
        return activities;
    }

    public void setActivities(ArrayList<SalmanActivity> activities) {
        this.activities = activities;
    }

    public String getQuestion1() {
        return question1;
    }

    public void setQuestion1(String question1) {
        this.question1 = question1;
    }

    public String getQuestion2() {
        return question2;
    }

    public void setQuestion2(String question2) {
        this.question2 = question2;
    }

    public String getAnswer1() {
        return answer1;
    }

    public void setAnswer1(String answer1) {
        this.answer1 = answer1;
    }

    public String getAnswer2() {
        return answer2;
    }

    public void setAnswer2(String answer2) {
        this.answer2 = answer2;
    }
}
