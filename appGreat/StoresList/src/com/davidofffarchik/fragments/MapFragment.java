package com.davidofffarchik.fragments;


import android.os.Bundle;
import android.util.Log;
import com.davidofffarchik.constans.Constans;
import com.davidofffarchik.database.DataBaseAdaptor;
import com.davidofffarchik.dialogfragment.CreateDialog;
import com.davidofffarchik.models.Product;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

public class MapFragment extends SupportMapFragment implements GoogleMap.OnInfoWindowClickListener{

    private GoogleMap map;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        map = this.getMap();
        addMarkers();
    }

    public void addMarkers() {
        List<Product> productList = getProductsFromDB();
        map.setOnInfoWindowClickListener(this);
        for (Product product : productList) {
            map.addMarker(new MarkerOptions()
                    .position(new LatLng(product.getLatitude(), product.getLongitude()))
                    .title(product.getTitle())
                    .snippet(product.getDescription()));
        }
    }

    private List<Product> getProductsFromDB(){
        DataBaseAdaptor dataBaseAdaptor = new DataBaseAdaptor(getActivity());
        dataBaseAdaptor.openDB();
        List<Product> productList = dataBaseAdaptor.getProductTitle();
        dataBaseAdaptor.closeDB();
        return productList;
    }

    @Override
    public void onInfoWindowClick(Marker marker) {
            CreateDialog createDialog = new CreateDialog();
            createDialog.show(getFragmentManager(), Constans.FRG_LOG);
            List<Product> productList = getProductsFromDB();
            for (Product product : productList) {
                product = new Product(
                productList.iterator().next().getProductId(),
                marker.getTitle(),
                marker.getSnippet(),
                marker.getPosition().latitude,
                marker.getPosition().longitude
                );
                Bundle bundle = new Bundle();
                bundle.putSerializable(Constans.PRODUCT, product);
                createDialog.setArguments(bundle);
            }
    }
}