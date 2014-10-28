package com.davidofffarchik.models;

public class User {

    private String email;
    private String password;
    private String confirmedPassword;
    private String userName;
    private String token;

    public User(String email, String password, String confirmedPassword, String userName) {
        this.email = email;
        this.password = password;
        this.confirmedPassword = confirmedPassword;
        this.userName = userName;
    }

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public User(String token) {
        this.token = token;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmedPassword() {
        return confirmedPassword;
    }

    public void setConfirmedPassword(String confirmedPassword) {
        this.confirmedPassword = confirmedPassword;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
