package com.davidofffarchik.dialogfragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.davidofffarchik.R;
import com.davidofffarchik.StoresListApp;
import com.davidofffarchik.addnewshop.AddNewShop;
import com.davidofffarchik.constans.Constans;
import com.davidofffarchik.main.Main;
import com.davidofffarchik.models.NewProductResponse;
import com.davidofffarchik.models.Product;
import com.davidofffarchik.models.User;
import com.davidofffarchik.webclient.WebClient;
import com.davidofffarchik.webclient.WebClientListener;

public class CreateDialog extends DialogFragment implements View.OnClickListener{

    public static CreateDialog newInstance(String num) {
        CreateDialog f = new CreateDialog();
        // Supply num input as an argument.
        Bundle args = new Bundle();
        args.putString("num", num);
        f.setArguments(args);
        return f;
    }

    public View onCreateView (LayoutInflater layoutInflater, ViewGroup container, Bundle savedInstanceState){

        Product product = getProduct();
        String productTitle = product.getTitle();
        String productDescription = product.getDescription();
        /*Product product = (Product) this.getArguments().getSerializable(Constans.PRODUCT);
        String productTitle = product.getTitle();
        String productDescription = product.getDescription();*/

        View view = layoutInflater.inflate(R.layout.dialog_fragment, null);
        getDialog().setTitle(getString(R.string.product_overview));

        TextView textTitle = (TextView) view.findViewById(R.id.productTitle);
        TextView textDesc = (TextView) view.findViewById(R.id.productDesc);

        textTitle.setText(getString(R.string.product_title_dialog) + " " + productTitle);
        textDesc.setText(getString(R.string.product_description_dialog) + " " +productDescription);

        Button btnCancel = (Button) view.findViewById(R.id.btnCancel);
        Button btnDelete = (Button) view.findViewById(R.id.btnDelete);
        btnDelete.setOnClickListener(this);
        btnCancel.setOnClickListener(this);
    return view;
    }

    private Product getProduct(){
        return (Product) this.getArguments().getSerializable(Constans.PRODUCT);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.btnCancel : dismiss();
                break;
            case R.id.btnDelete :
                WebClientListener<NewProductResponse> newProductResponseWebClientListener = new WebClientListener<NewProductResponse>() {
                    @Override
                    public void onResponseSuccess(NewProductResponse result) {
                        Toast.makeText(StoresListApp.getInstance().getApplicationContext(), "Продукт был удален!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(StoresListApp.getInstance().getApplicationContext(), Main.class);
                        startActivity(intent);
                    }

                    @Override
                    public void onResponseError() {

                    }
                };
                String token = getArguments().getString("token");
                Product product = getProduct();
                //String token = addNewShop.getSavedToken();
                Log.v("Токен при удалении товара", "" +token);
                User user = new User(token);
                NewProductResponse newProductResponse = new NewProductResponse(user, product);
                Log.v("Модель при удалении товара", "" +newProductResponse);
                WebClient.getInstance().callDeleteProduct(newProductResponse, newProductResponseWebClientListener);
                break;
        }
    }

    public void onDismiss(DialogInterface dialog){
        super.onDismiss(dialog);
    }

    public void onCancel(DialogInterface dialog){
        super.onCancel(dialog);
    }
}
