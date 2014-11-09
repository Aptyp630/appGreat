package com.davidofffarchik.webclientparams;

import android.util.Log;
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
            Log.v("Ответ от сервера", " " +jsonObject.getString("message"));
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
            Log.v("ID для удаления товара", " " +newProductResponse.getProduct().getProductId());
            jsonObject.put("token", newProductResponse.getUser().getToken());
            Log.v("Token для удаления товара", " " +newProductResponse.getUser().getToken());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }

    @Override
    public String getApiMethod() {
        return "delete_product.json?id="+newProductResponse.getProduct().getProductId()+"&token="+newProductResponse.getUser().getToken();
    }
}
