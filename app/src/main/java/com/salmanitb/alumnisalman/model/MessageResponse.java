package com.salmanitb.alumnisalman.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Fadhil Imam Kurnia on 25/04/2018.
 */

public class MessageResponse {
    @SerializedName("message")
    String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
