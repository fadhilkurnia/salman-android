package com.salmanitb.alumnisalman.model;

/**
 * Created by Fadhil Imam Kurnia on 07/04/2018.
 */

public class BaseResponse<T> {
    Boolean success;
    T data;
    Error error;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Error getError() {
        return error;
    }

    public void setError(Error error) {
        this.error = error;
    }
}
