package com.example.controller;

import android.content.Context;
import com.example.adapter.ProductListAdapter;
import com.example.models.Product;
import com.example.models.ProductResult;
import com.example.query.QueryToServer;

import java.util.List;

public class StoresLoadController{

    private int page = 1;
    private ProductListAdapter adapter;
    private List<Product> productList;

    public StoresLoadController(ProductListAdapter adapter) {
        this.adapter = adapter;
    }

    public void requestProducts(final Context context) {
        final QueryToServer.OnResponseListener listener = new QueryToServer.OnResponseListener() {
            @Override
            public void onProductsReceived(ProductResult productResult) {
                productList.addAll(productResult.getProduct());
                if(page <= productResult.getPagination().getTotalPage()) {
                    requestProducts(context);
                }
            }
        };
        QueryToServer.callGetProducts(listener, context, page);
        page++;
    }
}
