package com.example.fragments;

import android.app.ListFragment;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import com.example.R;

public class MapFragment extends ListFragment{

    private String data[] = { "There must be Map" };

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), R.layout.text_review, data);
        setListAdapter(adapter);
    }

}
