/**
 * Created by nursyahrina on 03/03/18.
 */
package com.salmanitb.alumnisalman.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Post implements Serializable{

    @SerializedName("id")
    private int id;
    @SerializedName("judul")
    private String title;
    @SerializedName("content_url")
    private String contentURL;
    @SerializedName("deskripsi")
    private String shortContent;
    @SerializedName("thumbnail")
    private String imageURL;
    @SerializedName("tanggal")
    private String time;
    @SerializedName("like_count")
    private int loveCount;
    @SerializedName("view_count")
    private int viewCount;
    @SerializedName("likedbyme")
    private boolean likedByMe;

    public Post(String title, String contentURL, String shortContent, String imageURL, String time, int loveCount, int viewCount, boolean likedByMe) {
        this.title = title;
        this.contentURL = contentURL;
        this.shortContent = shortContent;
        this.imageURL = imageURL;
        this.time = time;
        this.loveCount = loveCount;
        this.viewCount = viewCount;
        this.likedByMe = likedByMe;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContentURL() {
        return contentURL;
    }

    public void setContentURL(String contentURL) {
        this.contentURL = contentURL;
    }

    public String getShortContent() {
        return shortContent;
    }

    public void setShortContent(String shortContent) {
        this.shortContent = shortContent;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getLoveCount() {
        return loveCount;
    }

    public void setLoveCount(int loveCount) {
        this.loveCount = loveCount;
    }

    public int getViewCount() {
        return viewCount;
    }

    public void setViewCount(int viewCount) {
        this.viewCount = viewCount;
    }

    public boolean isLikedByMe() {
        return likedByMe;
    }

    public void setLikedByMe(boolean likedByMe) {
        this.likedByMe = likedByMe;
    }
}
