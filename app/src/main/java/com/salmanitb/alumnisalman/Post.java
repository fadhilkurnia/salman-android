/**
 * Created by nursyahrina on 03/03/18.
 */
package com.salmanitb.alumnisalman;


import java.io.Serializable;

public class Post implements Serializable {
    private String datetime, headline, youtubeVideoID, content, imageLocation;

    public Post() {

    }
    public Post(String datetime, String headline, String youtubeVideoID, String content, String imageLocation) {
        this.datetime = datetime;
        this.headline = headline;
        this.youtubeVideoID = youtubeVideoID;
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

    public String getYoutubeVideoID() {
        return youtubeVideoID;
    }

    public void setYoutubeVideoID(String youtubeVideoID) {
        this.youtubeVideoID = youtubeVideoID;
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
