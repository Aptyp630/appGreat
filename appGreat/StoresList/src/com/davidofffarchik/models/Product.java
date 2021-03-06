package com.davidofffarchik.models;

import java.io.Serializable;

public class Product implements Serializable{

    private static final long serialVersionUID = 0L;
    private int productId;
    private String title;
    private String description;
    private double latitude;
    private double longitude;

    public Product(int productId, String title, String description, double latitude, double longitude) {
        this(title, description, latitude, longitude);
        this.productId = productId;
    }

    public Product(String title, String description, double latitude, double longitude) {
        this.title = title;
        this.description = description;
        this.latitude = latitude;
        this.longitude = longitude;
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

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }
}
