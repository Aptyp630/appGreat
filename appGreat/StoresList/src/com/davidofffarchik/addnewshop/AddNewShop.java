package com.davidofffarchik.addnewshop;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.davidofffarchik.R;
import com.davidofffarchik.query.QueryCreateNewProduct;

public class AddNewShop extends Activity implements View.OnClickListener{

    EditText productName;
    EditText productDescription;
    EditText latitude;
    EditText longitude;
    Button pickMap;
    Button createShop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_new_shop);
        pickMap = (Button) findViewById(R.id.pickMap);
        createShop = (Button) findViewById(R.id.createShop);
        pickMap.setOnClickListener(this);
        createShop.setOnClickListener(this);
    }

    public String getLatitude(){
       latitude = (EditText) findViewById(R.id.latitude);
       return latitude.getText().toString();
    }

    public String getLongitude(){
        longitude = (EditText) findViewById(R.id.longitude);
        return longitude.getText().toString();
    }

    public String returnToken(){
        Intent intent = getIntent();
        return intent.getExtras().getString("token");
    }

    public void passDataToQuery(){
        productName = (EditText) findViewById(R.id.putNameShop);
        productDescription = (EditText) findViewById(R.id.putDescription);
        String title = productName.getText().toString();
        String  description = productDescription.getText().toString();
        String latitude = getLatitude();
        String longitude = getLongitude();
        String token = returnToken();
        QueryCreateNewProduct queryCreateNewProduct = new QueryCreateNewProduct();
        queryCreateNewProduct.addNewProductToServer(this, title, description, latitude, longitude, token);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.pickMap :
                Intent intent = new Intent(this, GetMapToAddMarker.class);
                startActivity(intent);
                break;
            case R.id.createShop :
                if(getLatitude() == null || getLongitude() == null)
                    Toast.makeText(this, "Latitude and Longitude are empty", Toast.LENGTH_LONG).show();
                passDataToQuery();
                break;
        }
    }
}
