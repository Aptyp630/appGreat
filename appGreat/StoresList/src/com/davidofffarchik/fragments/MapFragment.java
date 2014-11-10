package com.davidofffarchik.fragments;


import android.os.Bundle;
import com.davidofffarchik.constans.Constans;
import com.davidofffarchik.database.DataBaseAdaptor;
import com.davidofffarchik.dialogfragment.ProductInfoDialog;
import com.davidofffarchik.models.Product;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.*;

import java.util.HashMap;
import java.util.List;

public class MapFragment extends SupportMapFragment implements GoogleMap.OnInfoWindowClickListener{

    private GoogleMap map;
    private HashMap<Integer, Marker> visibleMarkers = new HashMap<Integer, Marker>();

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        map = this.getMap();
        map.setOnCameraChangeListener(getCameraChangeListener());
        //addMarkers();
    }

    public MarkerOptions addMarkers(Product product) {
        //List<Product> productList = getProductsFromDB();
        map.setOnInfoWindowClickListener(this);
        //for (Product product : productList) {
            map.addMarker(new MarkerOptions()
                    .position(new LatLng(product.getLatitude(), product.getLongitude()))
                    .title(product.getTitle())
                    .snippet(product.getDescription()));
        //}
        return new MarkerOptions()
                .position(new LatLng(product.getLatitude(), product.getLongitude()))
                .title(product.getTitle())
                .snippet(product.getDescription());
    }

    public GoogleMap.OnCameraChangeListener getCameraChangeListener(){
        return new GoogleMap.OnCameraChangeListener() {
            @Override
            public void onCameraChange(CameraPosition cameraPosition) {
                List<Product> productList = getProductsFromDB();
                addItemsToMap(productList);
            }
        };
    }

    private void addItemsToMap(List<Product> products)
    {
        if(map != null)
        {
            //This is the current user-viewable region of the map
            LatLngBounds bounds = map.getProjection().getVisibleRegion().latLngBounds;

            //Loop through all the items that are available to be placed on the map
            for(Product product : products)
            {

                //If the item is within the the bounds of the screen
                if(bounds.contains(new LatLng(product.getLatitude(), product.getLongitude())))
                {
                    //If the item isn't already being displayed
                    if(!visibleMarkers.containsKey(product.getProductId()))
                    {
                        //Add the Marker to the Map and keep track of it with the HashMap
                        //getMarkerForItem just returns a MarkerOptions object
                        visibleMarkers.put(product.getProductId(), map.addMarker(addMarkers(product)));
                    }
                }

                //If the marker is off screen
                else
                {
                    //If the course was previously on screen
                    if(visibleMarkers.containsKey(product.getProductId()))
                    {
                        //1. Remove the Marker from the GoogleMap
                        visibleMarkers.get(product.getProductId()).remove();

                        //2. Remove the reference to the Marker from the HashMap
                        visibleMarkers.remove(product.getProductId());
                    }
                }
            }
        }
    }

    private List<Product> getProductsFromDB(){
        DataBaseAdaptor dataBaseAdaptor = new DataBaseAdaptor(getActivity());
        dataBaseAdaptor.openDB();
        List<Product> productList = dataBaseAdaptor.getListOfProducts();
        dataBaseAdaptor.closeDB();
        return productList;
    }

    @Override
    public void onInfoWindowClick(Marker marker) {
        Bundle bundle = new Bundle();
        ProductInfoDialog createDialog = new ProductInfoDialog();
        createDialog.show(getFragmentManager(), Constans.FRG_TAG);
        List<Product> productList = getProductsFromDB();
        for (Product product : productList) {
            createDialog.setArguments(bundle);
            bundle.putSerializable(Constans.PRODUCT, product);
        }

    }
}

/*
 old Map fragment

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
        Bundle bundle = new Bundle();
        CreateDialog createDialog = new CreateDialog();
        createDialog.show(getFragmentManager(), Constans.FRG_LOG);
        List<Product> productList = getProductsFromDB();
        for(Product product : productList){
        bundle.putSerializable(Constans.PRODUCT, product);
        createDialog.setArguments(bundle);
        }
    }
}

*/