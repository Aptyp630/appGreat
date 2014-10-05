package com.example.controller;

import android.content.Context;
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

    public void requestProducts(final Context context) {
        ProductResult productResult = new ProductResult();
        final QueryToServer.OnResponseListener listener = new QueryToServer.OnResponseListener() {
            @Override
            public void onProductsReceived(ProductResult products) {
                int currentPage = Integer.parseInt(String.valueOf(products.getCurrentPage()));
                do{
                    QueryToServer.callGetProducts(this, context, currentPage);                }
                while(currentPage == 12);
                currentPage++;
                adapter = new ProductListAdapter(context, products.getProduct());
            }
        };
        requestProducts(context);
    }
}
