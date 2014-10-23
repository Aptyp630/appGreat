package com.davidofffarchik.query;

import android.content.Context;
import android.util.Log;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONException;
import org.json.JSONObject;

public class QueryCreateNewProduct {

    public void addNewProductToServer(
            final Context context,
            final String title,
            final String description,
            final String latitude,
            final String longitude,
            final String token
    )
    {
        String url = "http://protected-wave-2984.herokuapp.com/api/create_product.json";
        RequestQueue queue = Volley.newRequestQueue(context);
        JSONObject parentObject = new JSONObject();
        Log.v("Response", "is " +parentObject);
        JSONObject jsonProduct = new JSONObject();
        try {
                jsonProduct.put("title", title);
                jsonProduct.put("description", description);
                jsonProduct.put("latitude", latitude);
                jsonProduct.put("longitude", longitude);
            parentObject.put("product", jsonProduct);
            parentObject.put("token", token);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, parentObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                Log.v("Response from Server ", "is " + jsonObject);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
            }
        });
        queue.add(jsonObjectRequest);
    }
}
