package com.davidofffarchik.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.davidofffarchik.R;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;

public class MapFragment extends Fragment {



   // static final LatLng NKUT = new LatLng(23.979548, 120.696745);
   private GoogleMap map;
   private SupportMapFragment mMapFragment;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.map, container, false);
        map = ((SupportMapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();
        mMapFragment.getMap();
        return view;
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
