package com.davidofffarchik.query;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.davidofffarchik.models.Product;
import org.json.JSONException;
import org.json.JSONObject;

public class QueryCreateNewProduct {

    public void addNewProductToServer(
            final Context context,
            final Product product,
            final String token
    )
    {
        String url = "http://protected-wave-2984.herokuapp.com/api/create_product.json";
        RequestQueue queue = Volley.newRequestQueue(context);
        JSONObject parentObject = new JSONObject();
        JSONObject jsonProduct = new JSONObject();
        try {
                jsonProduct.put("title", product.getTitle());
                jsonProduct.put("description", product.getDescription());
                jsonProduct.put("lat", product.getLatitude());
                jsonProduct.put("long", product.getLongitude());
            parentObject.put("product", jsonProduct);
            parentObject.put("token", token);
            Log.v("ParentObject", "is " +parentObject);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, parentObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                Toast.makeText(context, "Новый продукт был добавлен", Toast.LENGTH_LONG).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Toast.makeText(context, "Вы заполнили не все поля!", Toast.LENGTH_LONG).show();
            }
        });
        queue.add(jsonObjectRequest);
    }
}
