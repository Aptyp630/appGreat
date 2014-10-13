package com.davidofffarchik.models;

import java.io.Serializable;

public class Product implements Serializable{

    private static final long serialVersionUID = 0L;
    private int productId;
    private String title;
    private String description;

    public Product(int productId, String title, String description) {
        this.productId = productId;
        this.title = title;
        this.description = description;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}