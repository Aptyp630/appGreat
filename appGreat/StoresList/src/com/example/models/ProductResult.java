package com.example.models;

import java.util.List;

public class ProductResult {

    private Pagination currentPage;
    private List<Product> product;

    public Pagination getCurrentPage() {
        return currentPage;
    }

    public List<Product> getProduct() {
        return product;
    }
}
