package com.example.controller;

import com.example.adapter.ProductListAdapter;
import com.example.models.Pagination;
import com.example.models.Product;
import com.example.models.ProductResult;
import com.example.query.QueryToServer;

import java.util.List;

public class StoresLoadController {

    Pagination currentPage;
    ProductListAdapter adapter;
    List<Product> productList;

    public StoresLoadController(ProductListAdapter adapter) {
        this.adapter = adapter;
    }

    public void requestProducts() {
        ProductResult productResult = new ProductResult();
        QueryToServer.OnResponseListener listener = new QueryToServer.OnResponseListener() {
            @Override
            public void onProductsReceived(List<Product> products) {

            }
        };
    }
}
