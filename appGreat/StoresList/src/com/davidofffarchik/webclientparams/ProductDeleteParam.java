package com.davidofffarchik.webclientparams;

import com.android.volley.Request;
import com.davidofffarchik.models.NewProductResponse;
import com.davidofffarchik.webclient.Parameter;
import org.json.JSONException;
import org.json.JSONObject;

public class ProductDeleteParam extends Parameter<NewProductResponse>{

    private NewProductResponse newProductResponse;

    public ProductDeleteParam(NewProductResponse newProductResponse) {
        this.newProductResponse = newProductResponse;
    }

    @Override
    public int getRequestMethod() {
        return Request.Method.DELETE;
    }

    @Override
    public NewProductResponse parseResponse(JSONObject jsonObject) {
        try {
            String message = jsonObject.getString("message");
            return new NewProductResponse(message);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public JSONObject getBody() {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("id", newProductResponse.getProduct().getProductId());
            jsonObject.put("token", newProductResponse.getUser().getToken());
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
