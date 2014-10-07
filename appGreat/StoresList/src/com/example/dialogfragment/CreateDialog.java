package com.example.dialogfragment;

import android.support.v4.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import com.example.R;
import com.example.constans.Constans;
import com.example.models.Product;

public class CreateDialog extends DialogFragment implements View.OnClickListener{

    public View onCreateView (LayoutInflater layoutInflater, ViewGroup container, Bundle savedInstanceState){

        Product product = (Product) this.getArguments().getSerializable(Constans.PRODUCT);
        String productTitle = product.getTitle();
        String productDescription = product.getDescription();

        View view = layoutInflater.inflate(R.layout.dialog_fragment, null);
        getDialog().setTitle(getString(R.string.product_overview));

        TextView textId = (TextView) view.findViewById(R.id.productId);
        TextView textDesc = (TextView) view.findViewById(R.id.productDesc);

        textId.setText(getString(R.string.product_title_dialog) +productTitle);
        textDesc.setText(getString(R.string.product_description_dialog) +productDescription);

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
        Log.v(Constans.LOG_TAG, "Dialog Dismissed");
    }

    public void onDismiss(DialogInterface dialog){
        super.onDismiss(dialog);
    }

    public void onCancel(DialogInterface dialog){
        super.onCancel(dialog);
    }
}
