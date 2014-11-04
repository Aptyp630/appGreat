package com.davidofffarchik.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import com.davidofffarchik.adapter.ProductListAdapter;
import com.davidofffarchik.constans.Constans;
import com.davidofffarchik.controller.StoresLoadController;
import com.davidofffarchik.dialogfragment.CreateDialog;
import com.davidofffarchik.models.Product;

public class StoreFragment extends ListFragment {

    private SharedPreferences sharedPreferences;
    private final static String PRIVATE_TOKEN = "storeFragmentToken";

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ProductListAdapter productListAdapter = new ProductListAdapter(this.getActivity());
        StoresLoadController storesLoadController = new StoresLoadController(productListAdapter);
        storesLoadController.requestProducts(getActivity());
        setListAdapter(productListAdapter);
        saveToken();
    }

    private String getTokenStoreFragment(){
        Bundle bundle = this.getArguments();
        return bundle.getString("token");
    }

    private void saveToken(){
        sharedPreferences = getActivity().getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putString(PRIVATE_TOKEN, getTokenStoreFragment());
        edit.commit();
        Log.v(PRIVATE_TOKEN, "saved " + getTokenStoreFragment());
    }

    private String getSavedToken(){
        sharedPreferences = getActivity().getPreferences(Context.MODE_PRIVATE);
        String getSavedToken = sharedPreferences.getString(PRIVATE_TOKEN, getTokenStoreFragment());
        Log.v(PRIVATE_TOKEN, "getSaved " +getTokenStoreFragment());
        return getSavedToken;
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        Log.v("Получить сохраненный токен при переходе в диалог", " " +getSavedToken());
        CreateDialog productDialog = new CreateDialog();
        productDialog.show(getFragmentManager(), Constans.FRG_LOG);
        Product product = (Product) l.getItemAtPosition(position);
        Bundle bundleProduct = new Bundle();
        bundleProduct.putSerializable(Constans.PRODUCT, product);
        productDialog.setArguments(bundleProduct);
        saveToken();
        CreateDialog createDialog = new CreateDialog();
        getSavedToken();
        Bundle bundleDialog = new Bundle();
        bundleDialog.putString("token", getSavedToken());
        createDialog.setArguments(bundleDialog);
    }
}

