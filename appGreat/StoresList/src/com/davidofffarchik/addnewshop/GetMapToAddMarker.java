package com.davidofffarchik.addnewshop;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import com.davidofffarchik.R;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;

public class GetMapToAddMarker extends FragmentActivity{

    SupportMapFragment supportMapFragment;
    GoogleMap googleMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.get_map_to_new_product);
        supportMapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        googleMap = supportMapFragment.getMap();
    }
}
