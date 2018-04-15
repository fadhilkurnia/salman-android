package com.salmanitb.alumnisalman.model;

/**
 * Created by hilmi on 13/03/2018.
 */

public class User {

    private String uid;
    private String name;
    private String email;
    private String image;
    private String sex;
    private String job;
    private String company;
    private String address;
    private String city;
    private String country;
    private String phonenumber;
    private String university;
    private String year_university;
    private String major;
    private String lmd;
    private String activities;
    private String year_activities;
    private double latitude;
    private double longitude;

    public User() {
    }

    public User(String uid, String name, String email) {
        this.uid = uid;
        this.name = name;
        this.email = email;
    }

    public User(String uid, String name, String email, String job) {
        this.uid = uid;
        this.name = name;
        this.email = email;
        this.job = job;
    }

    public User(String uid, String name, String email, String job, String country) {
        this.uid = uid;
        this.name = name;
        this.email = email;
        this.job = job;
        this.country = country;
    }

    public User(String uid, String name, String email, String image, String sex, String job, String company, String address, String city, String country, String phonenumber, String university, String year_university, String major, String lmd, String activities, String year_activities, double latitude, double longitude) {
        this.uid = uid;
        this.name = name;
        this.email = email;
        this.image = image;
        this.sex = sex;
        this.job = job;
        this.company = company;
        this.address = address;
        this.city = city;
        this.country = country;
        this.phonenumber = phonenumber;
        this.university = university;
        this.year_university = year_university;
        this.major = major;
        this.lmd = lmd;
        this.activities = activities;
        this.year_activities = year_activities;
        this.latitude = latitude;
        this.longitude = longitude;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getUniversity() {
        return university;
    }

    public void setUniversity(String university) {
        this.university = university;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getYearUniversity() {
        return year_university;
    }

    public void setYearUniversity(String year_university) {
        this.year_university = year_university;
    }

    public String getLmd() {
        return lmd;
    }

    public void setLmd(String lmd) {
        this.lmd = lmd;
    }

    public String getActivities() {
        return activities;
    }

    public void setActivities(String activities) {
        this.activities = activities;
    }

    public String getYearActivities() {
        return year_activities;
    }

    public void setYearActivities(String year_activities) {
        this.year_activities = year_activities;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
