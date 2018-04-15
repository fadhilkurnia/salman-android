package com.salmanitb.alumnisalman.model;

/**
 * Created by Fadhil Imam Kurnia on 07/04/2018.
 */

public class BaseResponse<T> {
    boolean success;
    T data;
    Error error;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
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
