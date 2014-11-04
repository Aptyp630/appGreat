package com.davidofffarchik.models;

public class NewProductResponse {

    private User user;
    private Product product;
    private String message;

    public NewProductResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public NewProductResponse(User user, Product product) {
        this.user = user;
        this.product = product;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
