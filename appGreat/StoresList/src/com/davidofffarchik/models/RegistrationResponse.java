package com.davidofffarchik.models;

public class RegistrationResponse {

    private User user;
    private boolean success;

    public RegistrationResponse(User user, boolean success) {
        this.user = user;
        this.success = success;
    }

    public RegistrationResponse(User user) {
        this.user = user;
    }

    public RegistrationResponse(boolean success) {
        this.success = success;
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
