package com.example.dialogfragment;

import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import com.example.R;

public class CreateDialog extends DialogFragment implements View.OnClickListener{
    private final static String LOG_TAG = "showTag";

    public View onCreateView (LayoutInflater layoutInflater, ViewGroup container, Bundle savedInstanceState){
        int productID = this.getArguments().getInt("id");
        String productDescription = this.getArguments().getString("description");

        View view = layoutInflater.inflate(R.layout.dialog_fragment, null);
        getDialog().setTitle("Product overview:");

        TextView textId = (TextView) view.findViewById(R.id.productId);
        TextView textDesc = (TextView) view.findViewById(R.id.productDesc);

        textId.setText("Product ID: " +productID);
        textDesc.setText("Product Description: " +productDescription);

        Button btnCancel = (Button) view.findViewById(R.id.btnCancel);
        btnCancel.setOnClickListener(this);
    return view;
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.btnCancel : dismiss();
                break;
        }
        Log.v(LOG_TAG, "Dialog Dismissed");
    }

    public void onDismiss(DialogInterface dialog){
        super.onDismiss(dialog);
    }

    public void onCancel(DialogInterface dialog){
        super.onCancel(dialog);
    }
}
