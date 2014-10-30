package com.davidofffarchik.models;

public class RegistrationResponse {

    private User user;
    private boolean success;
    private String message;

    public RegistrationResponse(User user) {
        this.user = user;
    }

    public RegistrationResponse(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
