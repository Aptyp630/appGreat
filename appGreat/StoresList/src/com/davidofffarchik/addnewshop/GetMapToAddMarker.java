package com.davidofffarchik.addnewshop;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import com.davidofffarchik.R;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class GetMapToAddMarker extends FragmentActivity{

    private SupportMapFragment supportMapFragment;
    private GoogleMap googleMap;
    private Marker marker;
    private Button getCoordinates;
    private double latitude;
    private double longitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.get_map_to_new_product);
        supportMapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        googleMap = supportMapFragment.getMap();
        googleMap.setOnMapClickListener(clickListener);
        googleMap.setOnMarkerDragListener(dragListener);
        getCoordinates = (Button) findViewById(R.id.returnToAddProduct);
        getCoordinates.setOnClickListener(getCoordinatesListener);
    }

    GoogleMap.OnMapClickListener clickListener = new GoogleMap.OnMapClickListener() {
        @Override
        public void onMapClick(LatLng latLng) {
            if(marker == null)
                marker = googleMap.addMarker(new MarkerOptions().position(new LatLng(latLng.latitude, latLng.longitude)).draggable(true));
            Log.v("Get position", " " +marker);
        }
    };

    GoogleMap.OnMarkerDragListener dragListener = new GoogleMap.OnMarkerDragListener() {
        @Override
        public void onMarkerDragStart(Marker marker) {
            Toast.makeText(getApplicationContext(), "Что бы получить координаты, перетащите маркер в любое место!", Toast.LENGTH_LONG).show();
        }

        @Override
        public void onMarkerDrag(Marker marker) {

        }

        @Override
        public void onMarkerDragEnd(Marker marker) {
            latitude = marker.getPosition().latitude;
            longitude = marker.getPosition().longitude;
            Log.v("Get position", " " +latitude);
            Log.v("Get position", " " +longitude);
        }
    };

    View.OnClickListener getCoordinatesListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if(latitude == 0.0 || longitude == 0.0) {
                Toast.makeText(getApplicationContext(), "Прежде чем отправить координаты, Вы должны их получить перетаскиванием маркера!", Toast.LENGTH_SHORT).show();
            }else {
                /*Intent intent = new Intent(getApplicationContext(), AddNewShop.class);
                intent.putExtra("latitude", latitude);
                Log.v("Get lat", " " + latitude);
                intent.putExtra("longitude", longitude);
                Log.v("Get long", " " + longitude);
                startActivity(intent);*/
                Intent intent = new Intent();
                intent.putExtra("latitude", latitude);
                intent.putExtra("longitude", longitude);
                setResult(RESULT_OK, intent);
                finish();
            }
            }
    };
}
