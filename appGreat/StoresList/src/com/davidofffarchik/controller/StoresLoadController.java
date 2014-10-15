package com.davidofffarchik.controller;

import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;
import com.davidofffarchik.adapter.ProductListAdapter;
import com.davidofffarchik.constans.Constans;
import com.davidofffarchik.database.DataBaseAdaptor;
import com.davidofffarchik.models.Product;
import com.davidofffarchik.models.ProductResult;
import com.davidofffarchik.query.QueryToServer;

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
                /*context.deleteDatabase(ProductTable.DB_NAME);
                Log.v(Constans.LOG_TAG, "Deleted " +ProductTable.DB_NAME);*/
                DataBaseAdaptor dataBaseAdaptor = new DataBaseAdaptor(context);
                dataBaseAdaptor.openDB();
                String mas[] = dataBaseAdaptor.getColumnNames();
                Log.v(Constans.LOG_TAG, "Column names = " + mas[0] + " Column names = " + mas[1] + " Column names = " + mas[2] + " Column names = " + mas[3]);
                int items = dataBaseAdaptor.columnItems();
                Log.v(Constans.LOG_TAG, "Column items = " + items);
                dataBaseAdaptor.closeDB();

                //#######################################################################
            }

            @Override
            public void errorInternetConnection(){
                DataBaseAdaptor dataBaseAdaptor = new DataBaseAdaptor(context);
                dataBaseAdaptor.openDB();
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
