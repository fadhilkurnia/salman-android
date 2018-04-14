package com.salmanitb.alumnisalman.model;

/**
 * Created by hilmi on 13/03/2018.
 */

public class User {

    private String uid;
    private String name;
    private String email;
    private String password;
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

    private String question1;
    private String question2;
    private String answer1;
    private String answer2;

    public User() {
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

    public String getYear_university() {
        return year_university;
    }

    public void setYear_university(String year_university) {
        this.year_university = year_university;
    }

    public String getYear_activities() {
        return year_activities;
    }

    public void setYear_activities(String year_activities) {
        this.year_activities = year_activities;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
