package com.example.controller;

import android.app.ProgressDialog;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
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
    private List<Product> productList = new ArrayList<Product>();
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
            }

            @Override
            public void errorConnectInternet(DataBaseAdaptor dataBaseAdaptor) {
                SQLiteDatabase sqLiteDatabase;
                sqLiteDatabase.
                dataBaseAdaptor.openDB();
                DataBaseAdaptor.DataBase dataBase = new DataBaseAdaptor.DataBase(context);
                dataBase.onCreate();

                dataBaseAdaptor.closeDB();
            }
            //будет переопределен метод для генерации оишбки
            //в этом методе я буду работать с базой данных

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
