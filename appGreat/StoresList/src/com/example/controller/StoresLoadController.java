package com.example.controller;

import android.app.ProgressDialog;
import android.content.Context;
import com.example.adapter.ProductListAdapter;
import com.example.constans.Constans;
import com.example.database.DataBaseAdaptor;
import com.example.models.Product;
import com.example.models.ProductResult;
import com.example.query.QueryToServer;

import java.util.ArrayList;
import java.util.List;

public class StoresLoadController{

    private int page = 1;
    private ProductListAdapter adapter;
    private List<Product> productList = new ArrayList <Product>();
    private ProgressDialog dialog;

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
                    return;
                }
                dialog.dismiss();
                initAdapter();
                //Create DB
                DataBaseAdaptor dataBaseAdaptor = new DataBaseAdaptor(context);
                dataBaseAdaptor.openDB();
                dataBaseAdaptor.closeDB();

                //#######################################################################
            }

            @Override
            public void errorInternetConnection(){
                DataBaseAdaptor dataBaseAdaptor = new DataBaseAdaptor(context);
                dataBaseAdaptor.openDB();
                    dataBaseAdaptor.returnProductsFromDB();
                dataBaseAdaptor.closeDB();
            }
         };

        if(dialog == null)
            dialog = ProgressDialog.show(context, Constans.LOAD_TITLE, Constans.LOAD_PRODUCTS);
        dialog.show();
        QueryToServer.callGetProducts(listener, context, page);
        page++;
    }

    private void initAdapter() {
        adapter.addProducts(productList);
        adapter.notifyDataSetChanged();
    }
}
