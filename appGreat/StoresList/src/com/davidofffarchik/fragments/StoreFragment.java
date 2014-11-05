package com.davidofffarchik.fragments;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.ListView;
import com.davidofffarchik.constans.Constans;
import com.davidofffarchik.models.Product;
import com.davidofffarchik.adapter.ProductListAdapter;
import com.davidofffarchik.controller.StoresLoadController;
import com.davidofffarchik.dialogfragment.CreateDialog;

public class StoreFragment extends ListFragment {

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ProductListAdapter productListAdapter = new ProductListAdapter(this.getActivity());
        StoresLoadController storesLoadController = new StoresLoadController(productListAdapter);
        storesLoadController.requestProducts(getActivity());
        setListAdapter(productListAdapter);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        CreateDialog productDialog = new CreateDialog();
        productDialog.show(getFragmentManager(), Constans.FRG_LOG);
        Product product = (Product) l.getItemAtPosition(position);
        Bundle bundle = new Bundle();
        bundle.putSerializable(Constans.PRODUCT, product);
        productDialog.setArguments(bundle);
    }
}
