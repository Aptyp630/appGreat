package com.example.fragments;

import android.app.ListFragment;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import com.example.StoresList.R;
import com.example.singlton.QueryToServer;

public class StoreListFragment extends ListFragment {

    /*
    QueryToServer queryToServer = QueryToServer.getInstance().parseJsonArray();
    private String data[] = {"Zero", "One", "Two", "Three", "Four"};\
    */

    QueryToServer queryToServer = QueryToServer.getInstance();

    public void setQueryToServer(QueryToServer queryToServer) {
        this.queryToServer = queryToServer;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), R.layout.text_review, data);
        setListAdapter(adapter);
    }
}

