/**
 * Created by nursyahrina on 03/03/18.
 */
package com.salmanitb.alumnisalman;


import java.io.Serializable;

public class Post implements Serializable {
    private String datetime, headline, videoLocation, content, imageLocation;

    public Post() {

    }
    public Post(String datetime, String headline, String videoLocation, String content, String imageLocation) {
        this.datetime = datetime;
        this.headline = headline;
        this.videoLocation = videoLocation;
        this.content = content;
        this.imageLocation = imageLocation;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public String getHeadline() {
        return headline;
    }

    public void setHeadline(String headline) {
        this.headline = headline;
    }

    public String getVideoLocation() {
        return videoLocation;
    }

    public void setVideoLocation(String videoLocation) {
        this.videoLocation = videoLocation;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImageLocation() {
        return imageLocation;
    }

    public void setImageLocation(String imageLocation){
        this.imageLocation = imageLocation;
    }
}
