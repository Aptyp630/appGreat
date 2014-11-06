package com.davidofffarchik.webclientparams;

import android.util.Log;
import com.android.volley.Request;
import com.davidofffarchik.UserToken;
import com.davidofffarchik.models.NewProductResponse;
import com.davidofffarchik.models.Product;
import com.davidofffarchik.models.User;
import com.davidofffarchik.webclient.Parameter;
import org.json.JSONException;
import org.json.JSONObject;

public class ProductUpdateParam extends Parameter<NewProductResponse>{

    private NewProductResponse newProductResponse;

    public ProductUpdateParam(NewProductResponse newProductResponse) {
        this.newProductResponse = newProductResponse;
    }

    @Override
    public int getRequestMethod() {
        return Request.Method.PUT;
    }

    @Override
    public NewProductResponse parseResponse(JSONObject jsonObject) {
        Log.v("Что нам приходит после апа", " " +jsonObject);
        try {
            JSONObject jsonParse = jsonObject.getJSONObject("product");
            int id = jsonParse.getInt("id");
            String title = jsonParse.getString("title");
            String description = jsonParse.getString("description");
            double latitude = jsonParse.getDouble("lat");
            double longitude = jsonParse.getDouble("long");
            User user = new User(UserToken.getInstance().getSavedToken());
            Product product = new Product(id, title, description, latitude, longitude);
            return new NewProductResponse(user, product);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public JSONObject getBody() {
        JSONObject parentObject = new JSONObject();
        JSONObject jsonProduct = new JSONObject();
        try {
            parentObject.put("id", newProductResponse.getProduct().getProductId());
            jsonProduct.put("title", newProductResponse.getProduct().getTitle());
            jsonProduct.put("description", newProductResponse.getProduct().getDescription());
            jsonProduct.put("lat", newProductResponse.getProduct().getLatitude());
            jsonProduct.put("long", newProductResponse.getProduct().getLongitude());
            parentObject.put("product", jsonProduct);
            parentObject.put("token", newProductResponse.getUser().getToken());
            return parentObject;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String getApiMethod() {
        return "update_product.json";
    }
}
