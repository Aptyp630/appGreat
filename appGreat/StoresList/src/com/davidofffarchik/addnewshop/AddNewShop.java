package com.davidofffarchik.addnewshop;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.davidofffarchik.R;
import com.davidofffarchik.UserToken;
import com.davidofffarchik.main.Main;
import com.davidofffarchik.models.NewProductResponse;
import com.davidofffarchik.models.Product;
import com.davidofffarchik.models.User;
import com.davidofffarchik.webclient.WebClient;
import com.davidofffarchik.webclient.WebClientListener;

public class AddNewShop extends Activity implements View.OnClickListener{

    private EditText productName;
    private EditText productDescription;
    private EditText latitude;
    private EditText longitude;
    private Button pickMap;
    private Button createShop;
    private Button updateShop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_new_shop);
        //удаляет клавиатуру после загрузки активити
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        //############################

        productName = (EditText) findViewById(R.id.putNameShop);
        productDescription = (EditText) findViewById(R.id.putDescription);
        productName.setText(getProductTitle());
        productDescription.setText(getProductDescription());
        pickMap = (Button) findViewById(R.id.pickMap);
        createShop = (Button) findViewById(R.id.createShop);
        updateShop = (Button) findViewById(R.id.updateShop);
        pickMap.setOnClickListener(this);
        createShop.setOnClickListener(this);
        updateShop.setOnClickListener(this);
    }

    private int getProductID(){
        Bundle extras = getIntent().getExtras();
        return extras.getInt("id");
    }

    private String getProductTitle(){
        Bundle extras = getIntent().getExtras();
        return extras.getString("title");
    }

    private String getProductDescription(){
        Bundle extras = getIntent().getExtras();
        return extras.getString("description");
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()){
            case R.id.pickMap :
                intent = new Intent(this, GetMapToAddMarker.class);
                startActivityForResult(intent, 1);
                break;

            case R.id.createShop :
                String titleCreate = productName.getText().toString();
                String  descriptionCreate = productDescription.getText().toString();
                Double latCreate = Double.valueOf((latitude.getText().toString()));
                Double lonCreate = Double.valueOf(longitude.getText().toString());
                String tokenCreate = UserToken.getInstance().getSavedToken();
                WebClientListener webCreateShop = new WebClientListener() {
                    @Override
                    public void onResponseSuccess(Object result) {
                        Toast.makeText(getApplicationContext(), "Продукт успешно добавлен!", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(getApplicationContext(), Main.class);
                        startActivity(intent);
                    }

                    @Override
                    public void onResponseError() {
                        Toast.makeText(getApplicationContext(), "Вы заполнили не все поля!", Toast.LENGTH_LONG).show();
                    }
                };
                WebClient.getInstance().callCreateNewProduct(new NewProductResponse(new User(tokenCreate), new Product(titleCreate, descriptionCreate, latCreate, lonCreate)), webCreateShop);
                break;

            case R.id.updateShop :
                String titleUP = productName.getText().toString();
                String  descriptionUP = productDescription.getText().toString();
                Double latUP = Double.valueOf((latitude.getText().toString()));
                Double lonUP = Double.valueOf(longitude.getText().toString());
                WebClientListener<NewProductResponse> webUpdateShop = new WebClientListener<NewProductResponse>(){
                    @Override
                    public void onResponseSuccess(NewProductResponse result) {
                        if(result.getProduct() != null) {
                            Toast.makeText(getApplicationContext(), "Update product", Toast.LENGTH_LONG).show();
                            Intent intentUpdate = new Intent(getApplicationContext(), Main.class);
                            startActivity(intentUpdate);
                        }else{
                            Toast.makeText(getApplicationContext(), result.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onResponseError() {
                        Toast.makeText(getApplicationContext(), "Прежде чем обновить - создайте продукт!!", Toast.LENGTH_LONG).show();
                    }
                };
                Product product = new Product(getProductID(), titleUP, descriptionUP, latUP, lonUP);
                User user = new User(UserToken.getInstance().getSavedToken());
                NewProductResponse newProductResponse = new NewProductResponse(user, product);
                WebClient.getInstance().callUpdateProduct(newProductResponse, webUpdateShop);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if(data == null){
            return;
        }
        latitude = (EditText) findViewById(R.id.latitude);
        longitude = (EditText) findViewById(R.id.longitude);
        double latitudeToUpdate = data.getDoubleExtra("latitude", 1);
        latitude.setText(String.valueOf(latitudeToUpdate));
        double longitudeToUpdate = data.getDoubleExtra("longitude", 2);
        longitude.setText(String.valueOf(longitudeToUpdate));
   }
}