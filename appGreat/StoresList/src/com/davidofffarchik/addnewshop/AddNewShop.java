package com.davidofffarchik.addnewshop;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.davidofffarchik.R;
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
    private SharedPreferences sharedPreferences;
    private final static String PRIVATE_TOKEN = "private_token";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_new_shop);
        pickMap = (Button) findViewById(R.id.pickMap);
        createShop = (Button) findViewById(R.id.createShop);
        pickMap.setOnClickListener(this);
        createShop.setOnClickListener(this);
        getLatitude();
        getLongitude();
    }

    public String getLatitude(){
        latitude = (EditText) findViewById(R.id.latitude);
        Bundle extras = getIntent().getExtras();
        double latitudePoint = extras.getDouble("latitude");
        latitude.setText(String.valueOf(latitudePoint));
       return latitude.getText().toString();
    }

    public String getLongitude(){
        longitude = (EditText) findViewById(R.id.longitude);
        Bundle extras = getIntent().getExtras();
        double longitudePoint = extras.getDouble("longitude");
        longitude.setText(String.valueOf(longitudePoint));
        return longitude.getText().toString();
    }

    public String returnToken(){
        Intent intent = getIntent();
        return intent.getExtras().getString("token");
    }

    private void saveToken(){
        sharedPreferences = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putString(PRIVATE_TOKEN, returnToken());
        edit.commit();
        Log.v(PRIVATE_TOKEN, "сохранился " +returnToken());
    }

    public String getSavedToken(){
        sharedPreferences = getPreferences(MODE_PRIVATE);
        return sharedPreferences.getString(PRIVATE_TOKEN, returnToken());
    }

    public void passDataToQuery(){
        productName = (EditText) findViewById(R.id.putNameShop);
        productDescription = (EditText) findViewById(R.id.putDescription);
        String title = productName.getText().toString();
        String  description = productDescription.getText().toString();
        Double latitude = Double.valueOf(getLatitude());
        Double longitude = Double.valueOf(getLongitude());
        String token = getSavedToken();
        WebClientListener webClientListener = new WebClientListener() {
            @Override
            public void onResponseSuccess(Object result) {
                Toast.makeText(getApplicationContext(), "Новый продукт был добавлен", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onResponseError() {
                Toast.makeText(getApplicationContext(), "Вы заполнили не все поля!", Toast.LENGTH_LONG).show();
            }
        };
        //QueryCreateNewProduct queryCreateNewProduct = new QueryCreateNewProduct();
        //queryCreateNewProduct.addNewProductToServer(this, new Product(title, description, latitude, longitude), token);
        WebClient.getInstance().callCreateNewProduct(new NewProductResponse(new User(token), new Product(title, description, latitude, longitude)), webClientListener);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.pickMap :
                Intent intent = new Intent(this, GetMapToAddMarker.class);
                startActivity(intent);
                saveToken();
                Log.v("Token", "" +returnToken());
                break;
            case R.id.createShop :
                passDataToQuery();
                productName.setText("");
                productDescription.setText("");
                latitude.setText("0.0");
                longitude.setText("0.0");
                break;
        }
    }
}
