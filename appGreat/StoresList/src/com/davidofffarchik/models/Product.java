package com.davidofffarchik.models;

import java.io.Serializable;

public class Product implements Serializable{

    private static final long serialVersionUID = 0L;
    private int productId;
    private String title;
    private String description;
    private Double latitude;
    private Double longitude;

    //ИЗМЕНЕНИЯ СОГЛАСНО СТРАНИЦАМ ЛАТИТУД И ЛОНГИТУД

    public Product(int productId, String title, String description, Double latitude, Double longitude) {
        this.productId = productId;
        this.title = title;
        this.description = description;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
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
