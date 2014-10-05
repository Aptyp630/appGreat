package com.example.fragments;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.ListView;
import com.example.adapter.ProductListAdapter;
import com.example.constans.Constans;
import com.example.controller.StoresLoadController;
import com.example.dialogfragment.CreateDialog;
import com.example.models.Product;
import com.example.models.ProductResult;
import com.example.query.QueryToServer;

import java.util.List;

public class StoreFragment extends ListFragment {

    ProductListAdapter productListAdapter;
    ProductResult productResult;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
       StoresLoadController storesLoadController = new StoresLoadController(productListAdapter);
        super.onActivityCreated(savedInstanceState);
        callGetStores();
        storesLoadController.requestProducts();
    }

    private void callGetStores() {
        QueryToServer.OnResponseListener listener = new QueryToServer.OnResponseListener() {
            @Override
            public void onProductsReceived(List<Product> p) {
                initList(p);
            }
        };
        QueryToServer.callGetProducts(listener, getActivity(), productResult);
    }

    public void initList(List<Product> p){
        ProductListAdapter adapter = new ProductListAdapter(getActivity(), p);
        setListAdapter(adapter);
    }

    //Callback for listView in ListFragment
    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        CreateDialog productDialog = new CreateDialog();
        productDialog.show(getFragmentManager(), Constans.FRG_LOG);
        Product product = (Product) l.getItemAtPosition(position);
        Bundle bundle = new Bundle();
        bundle.putSerializable(Constans.PRODUCT, product);
        productDialog.setArguments(bundle);
    }

    /*public void saveOrUpdateProductsInDB (){
        DataBaseAdaptor dbAdaptor = new DataBaseAdaptor(getActivity());
        dbAdaptor.openDB();

        dbAdaptor.insertRow();

        dbAdaptor.closeDB();
    }*/
}

