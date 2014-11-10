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
import com.davidofffarchik.UserToken;
import com.davidofffarchik.addnewshop.AddNewShop;
import com.davidofffarchik.constans.Constans;
import com.davidofffarchik.database.DataBaseAdaptor;
import com.davidofffarchik.main.Main;
import com.davidofffarchik.models.NewProductResponse;
import com.davidofffarchik.models.Product;
import com.davidofffarchik.models.User;
import com.davidofffarchik.webclient.WebClient;
import com.davidofffarchik.webclient.WebClientListener;

public class ProductInfoDialog extends DialogFragment implements View.OnClickListener{

    public View onCreateView (LayoutInflater layoutInflater, ViewGroup container, Bundle savedInstanceState){

        Product product = getProduct();
        String productTitle = product.getTitle();
        String productDescription = product.getDescription();

        View view = layoutInflater.inflate(R.layout.dialog_fragment, null);
        getDialog().setTitle(getString(R.string.product_overview));

        TextView textTitle = (TextView) view.findViewById(R.id.productTitle);
        TextView textDesc = (TextView) view.findViewById(R.id.productDesc);

        textTitle.setText(getString(R.string.product_title_dialog) + " " + productTitle);
        textDesc.setText(getString(R.string.product_description_dialog) + " " +productDescription);

        Button btnCancel = (Button) view.findViewById(R.id.btnCancel);
        Button btnUpdate = (Button) view.findViewById(R.id.btnUpdate);
        Button btnDelete = (Button) view.findViewById(R.id.btnDelete);
        btnDelete.setOnClickListener(this);
        btnUpdate.setOnClickListener(this);
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
            case R.id.btnUpdate :
                Intent intent = new Intent(getActivity().getApplicationContext(), AddNewShop.class);
                intent.putExtra("id", getProduct().getProductId());
                intent.putExtra("title", getProduct().getTitle());
                intent.putExtra("description", getProduct().getDescription());
                intent.putExtra("latitude", getProduct().getLatitude());
                Log.v("Lat передаю", " " +getProduct().getLatitude());
                intent.putExtra("longitude", getProduct().getLongitude());
                Log.v("Lon передаю", " " +getProduct().getLongitude());
                startActivity(intent);
                break;
            case R.id.btnDelete :
                WebClientListener<NewProductResponse> newProductResponseWebClientListener = new WebClientListener<NewProductResponse>() {
                    @Override
                    public void onResponseSuccess(NewProductResponse result) {
                        String message = "product deleted";
                        if(result.getMessage().equals(message)) {
                            Toast.makeText(StoresListApp.getInstance().getApplicationContext(), result.getMessage(), Toast.LENGTH_SHORT).show();
                            DataBaseAdaptor dataBaseAdaptor = new DataBaseAdaptor(getActivity().getApplicationContext());
                            dataBaseAdaptor.openDB();
                            dataBaseAdaptor.deleteRow(getProduct().getProductId());
                            dataBaseAdaptor.closeDB();
                            Intent intent = new Intent(StoresListApp.getInstance().getApplicationContext(), Main.class);
                            intent.putExtra("token", UserToken.getInstance().getSavedToken());
                            startActivity(intent);
                        }else
                            Toast.makeText(getActivity().getApplicationContext(), result.getMessage(), Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onResponseError() {
                        DataBaseAdaptor dataBaseAdaptor = new DataBaseAdaptor(getActivity().getApplicationContext());
                        dataBaseAdaptor.openDB();
                        dataBaseAdaptor.deleteRow(getProduct().getProductId());
                        dataBaseAdaptor.closeDB();
                        Intent intent = new Intent(StoresListApp.getInstance().getApplicationContext(), Main.class);
                        intent.putExtra("token", UserToken.getInstance().getSavedToken());
                        Log.v("Передаем токен в мейн, после удаления товара", " " +UserToken.getInstance().getSavedToken());
                        startActivity(intent);
                    }
                };
                String token = UserToken.getInstance().getSavedToken();
                Log.v("Токен при удалении товара", "" +token);
                User user = new User(token);
                Product product = getProduct();
                NewProductResponse newProductResponse = new NewProductResponse(user, product);
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