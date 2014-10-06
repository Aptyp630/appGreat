package com.example.models;

import java.util.List;

public class ProductResult {

    private Pagination pagination;
    private List<Product> products;

    public ProductResult(Pagination pagination, List<Product> products) {
        this.pagination = pagination;
        this.products = products;
    }

    public Pagination getPagination() {
        return pagination;
    }

    public List<Product> getProduct() {
        return products;
    }
}
