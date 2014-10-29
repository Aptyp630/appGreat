package com.davidofffarchik.models;

public class UserSuccess {

    private User user;
    private boolean success;

    public UserSuccess(User user, boolean success) {
        this.user = user;
        this.success = success;
    }

    public UserSuccess(User user) {
        this.user = user;
    }

    public UserSuccess(boolean success) {
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
