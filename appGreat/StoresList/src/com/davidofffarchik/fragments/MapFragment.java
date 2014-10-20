package com.davidofffarchik.fragments;


import android.os.Bundle;
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
        for (int i = 0; i < productList.size(); i++) {
            //ИЗМЕНЕНИЯ СОГЛАСНО СТРАНИЦАМ ЛАТИТУД И ЛОНГИТУД
            //double latitude = (double) Math.round(Math.random()*(-50));
            //double longitude = (double) Math.round(Math.random()*50);
            map.addMarker(new MarkerOptions()
                    .position(new LatLng(productList.get(i).getLatitude(), productList.get(i).getLongitude()))
                    .title(productList.get(i).getTitle())
                    .snippet(productList.get(i).getDescription())
                    .draggable(true));
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
        //ИЗМЕНЕНИЯ СОГЛАСНО СТРАНИЦАМ ЛАТИТУД И ЛОНГИТУД
            for (int i = 0; i < productList.size(); i++) {
                Product product = new Product(
                        productList.get(i).getProductId(),
                        marker.getTitle(),
                        marker.getSnippet(),
                        productList.get(i).getLatitude(),
                        productList.get(i).getLongitude()
                );
                Bundle bundle = new Bundle();
                bundle.putSerializable(Constans.PRODUCT, product);
                createDialog.setArguments(bundle);
            }
    }
}