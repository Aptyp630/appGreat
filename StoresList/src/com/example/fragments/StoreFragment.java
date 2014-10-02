package com.example.fragments;

import android.app.ListFragment;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import com.example.adapter.ProductListAdapter;
import com.example.dialogfragment.CreateDialog;
import com.example.models.Product;
import com.example.singlton.QueryToServer;

import java.util.List;

public class StoreFragment extends ListFragment{
    private final static String FRG_LOG = "fragmentLog";

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        callGetStores();
    }

    private void callGetStores() {
        QueryToServer.OnResponseListener listener = new QueryToServer.OnResponseListener() {
            @Override
            public void onProductsReceived(List<Product> p) {
                initList(p);
            }
        };
        QueryToServer queryToServer = QueryToServer.getInstance();
        queryToServer.requestQueue(listener, getActivity());
    }

    public void initList(List<Product> p){
        ProductListAdapter adapter = new ProductListAdapter(getActivity(), p);
        setListAdapter(adapter);
    }

    //Callback for listView in ListFragment
    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        CreateDialog productDialog = new CreateDialog();
        productDialog.show(getFragmentManager(), FRG_LOG);
        Product product = (Product) l.getItemAtPosition(position);
        Bundle bundle = new Bundle();
        bundle.putInt("id", product.getProductId());
        bundle.putString("description", String.valueOf(product.getDescription()));
        productDialog.setArguments(bundle);
    }
}

