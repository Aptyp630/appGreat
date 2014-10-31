package com.davidofffarchik.webclientparams;


import android.util.Log;
import com.android.volley.Request;
import com.davidofffarchik.models.NewProductResponse;
import com.davidofffarchik.models.Product;
import com.davidofffarchik.webclient.Parameter;
import org.json.JSONException;
import org.json.JSONObject;

public class NewProductParam extends Parameter<Product>{

    private NewProductResponse newProductResponse;

    public NewProductParam(NewProductResponse productResponse) {
        this.newProductResponse = productResponse;
    }

    @Override
    public int getRequestMethod() {
        return Request.Method.POST;
    }

    @Override
    public Product parseResponse(JSONObject jsonObject) {
        return null;
    }

    @Override
    public JSONObject getBody() {
        JSONObject parentObject = new JSONObject();
        JSONObject jsonProduct = new JSONObject();
        try {
            jsonProduct.put("title", newProductResponse.getProduct().getTitle());
            jsonProduct.put("description", newProductResponse.getProduct().getDescription());
            jsonProduct.put("lat", newProductResponse.getProduct().getLatitude());
            jsonProduct.put("long", newProductResponse.getProduct().getLongitude());
            parentObject.put("product", jsonProduct);
            parentObject.put("token", newProductResponse.getUser().getToken());
            Log.v("ParentObject", "is " +parentObject);
            return parentObject;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String getApiMethod() {
        return "create_product.json";
    }
}
