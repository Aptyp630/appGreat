package com.davidofffarchik.fragments;


import android.os.Bundle;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;

public class MapFragment extends SupportMapFragment {

    GoogleMap googleMap;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        googleMap = getMap();
        //googleMap.addMarker(new MarkerOptions().position(new LatLng(90, 30)).title("Android").snippet("Hello World"));
    }


    /*

    private String data[] = { "There must be Map" };

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), R.layout.text_review, data);
        setListAdapter(adapter);
    }
*/

}
