package com.example.dialogfragment;

import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.R;

public class Dialog extends DialogFragment{

    public View onCreateView (LayoutInflater layoutInflater, ViewGroup container, Bundle savedInstanceState){

        View view = layoutInflater.inflate(R.layout.dialog_fragment, null);
        view.findViewById(R.id.text_title);
        view.findViewById(R.id.text_id);
        view.findViewById(R.id.text_description);

    return view;
    }
}
