package com.davidofffarchik.addnewshop;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
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
    private SharedPreferences sharedPreferences;
    private final static String PRIVATE_TOKEN = "private_token";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_new_shop);
        productName = (EditText) findViewById(R.id.putNameShop);
        productDescription = (EditText) findViewById(R.id.putDescription);

        productName.setText(getProductTitle());
        productDescription.setText(getProductDescription());
        //getProductLatitude();
        //getProductLongitude();

        //удаляет клавиатуру после загрузки активити
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        //############################
        pickMap = (Button) findViewById(R.id.pickMap);
        createShop = (Button) findViewById(R.id.createShop);
        updateShop = (Button) findViewById(R.id.updateShop);
        pickMap.setOnClickListener(this);
        createShop.setOnClickListener(this);
        updateShop.setOnClickListener(this);
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
        return UserToken.getInstance().getSavedToken();
        //Intent intent = getIntent();
        //return intent.getExtras().getString("token");
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

  /*  private String getProductLatitude(){
        Bundle extras = getIntent().getExtras();
        return extras.getString("latitude");
    }

    private String getProductLongitude(){
        Bundle extras = getIntent().getExtras();
        return extras.getString("longitude");
    }*/
/*
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
*/
    public void passDataToQuery(){
        //productName = (EditText) findViewById(R.id.putNameShop);
        //productDescription = (EditText) findViewById(R.id.putDescription);
        String title = productName.getText().toString();
        String  description = productDescription.getText().toString();
        Double latitude = Double.valueOf(getLatitude());
        Double longitude = Double.valueOf(getLongitude());
        //String token = getSavedToken();
        String token = returnToken();
        WebClientListener webClientListener = new WebClientListener() {
            @Override
            public void onResponseSuccess(Object result) {
                Toast.makeText(getApplicationContext(), "Продукт успешно добавлен!", Toast.LENGTH_LONG).show();
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
                final Intent intentMap = new Intent(this, GetMapToAddMarker.class);
                startActivity(intentMap);
                //saveToken();
                Log.v("Token", "" +returnToken());
                break;

            case R.id.createShop :
                passDataToQuery();
                productName.setText("");
                productDescription.setText("");
                latitude.setText("0.0");
                longitude.setText("0.0");
                Intent intentShop = new Intent(this, Main.class);
                startActivity(intentShop);
                break;

            case R.id.updateShop :
                WebClientListener<NewProductResponse> webClientListener = new WebClientListener<NewProductResponse>(){
                    @Override
                    public void onResponseSuccess(NewProductResponse result) {
                        Toast.makeText(getApplicationContext(), "Update product", Toast.LENGTH_LONG).show();
                        Intent intentUpdate = new Intent(getApplicationContext(), Main.class);
                        startActivity(intentUpdate);
                    }

                    @Override
                    public void onResponseError() {
                        Toast.makeText(getApplicationContext(), "Неполадки с интернетом!", Toast.LENGTH_LONG).show();
                    }
                };
                Product product = new Product(getProductID(), productName.getText().toString(), productDescription.getText().toString(), Double.valueOf(getLatitude()), Double.valueOf(getLongitude()));
                User user = new User(UserToken.getInstance().getSavedToken());
                NewProductResponse newProductResponse = new NewProductResponse(user, product);
                WebClient.getInstance().callUpdateProduct(newProductResponse, webClientListener);
                break;
        }
    }
}
