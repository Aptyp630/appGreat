package com.davidofffarchik.models;

/**
 * Created by root on 27.10.14.
 */
public class Success {

    private String message;
    private boolean success;

    public Success(String message, boolean success) {
        this.message = message;
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
