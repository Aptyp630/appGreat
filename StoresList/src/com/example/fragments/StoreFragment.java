package com.example.fragments;

import android.app.ListFragment;
import android.os.Bundle;
import com.example.adapter.ProductListAdapter;
import com.example.models.Product;
import com.example.singlton.QueryToServer;

import java.util.List;

public class StoreFragment extends ListFragment{

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //initData();
    }

    private void initData() {
        QueryToServer.OnShowProductListener listener = new QueryToServer.OnShowProductListener() {
            @Override
            public void showProduct(List<Product> p) {
                ProductListAdapter adapter = new ProductListAdapter(getActivity(), p);
                setListAdapter(adapter);
            }
        };
        QueryToServer queryToServer = QueryToServer.getInstance();
        queryToServer.requestQueue(listener);
    }
}

