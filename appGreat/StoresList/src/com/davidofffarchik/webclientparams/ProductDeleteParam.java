package com.davidofffarchik.webclientparams;

import android.util.Log;
import com.android.volley.Request;
import com.davidofffarchik.models.NewProductResponse;
import com.davidofffarchik.models.RegistrationResponse;
import com.davidofffarchik.webclient.Parameter;
import org.json.JSONException;
import org.json.JSONObject;

public class ProductDeleteParam extends Parameter<RegistrationResponse>{

    private NewProductResponse newProductResponse;

    public ProductDeleteParam(NewProductResponse newProductResponse) {
        this.newProductResponse = newProductResponse;
    }

    @Override
    public int getRequestMethod() {
        return Request.Method.DELETE;
    }

    @Override
    public RegistrationResponse parseResponse(JSONObject jsonObject) {
        JSONObject jsonDeleteProduct = new JSONObject();
        try {
            return new RegistrationResponse(jsonDeleteProduct.getString("message"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public JSONObject getBody() {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("id", newProductResponse.getUser().getEmail());
            jsonObject.put("token", newProductResponse.getUser().getToken());
            Log.v("То, что мы отправили при удалении товара", "" + jsonObject);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }

    @Override
    public String getApiMethod() {
        return "delete_product.json?id="+newProductResponse.getProduct().getProductId();
    }
}
