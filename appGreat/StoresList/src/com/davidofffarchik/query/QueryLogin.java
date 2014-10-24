package com.davidofffarchik.query;

import android.content.Context;
import android.util.Log;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.davidofffarchik.constans.Constans;
import org.json.JSONException;
import org.json.JSONObject;

public class QueryLogin{

    public static void logInTo(
            final Context context,
            final String email,
            final String password,
            final OnCreateProductFromLogin listener
    ){
        String url = "http://protected-wave-2984.herokuapp.com/api/login.json";
        RequestQueue queue = Volley.newRequestQueue(context);
        final JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("email", email);
            jsonObject.put("password", password);
            Log.v("Отправленные данные при регистрации", "" +jsonObject);
        }catch (Exception e){
            e.printStackTrace();
        }
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, jsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                Log.v("Что мне приходит", "" +jsonObject);
                try {
                    JSONObject jsonUser = jsonObject.getJSONObject("user");
                    String token = jsonUser.getString("token");
                    Log.v(Constans.LOG_TAG, token);
                    String email = jsonUser.getString("email");
                    Log.v(Constans.LOG_TAG, email);
                    boolean success = jsonUser.getBoolean("success");
                    Log.v(Constans.LOG_TAG, String.valueOf(success));
                    Log.v("Что я парсю", "" +jsonUser);
                    listener.createNewShopLogin(token);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                listener.errorInternetConnectionLogin();
            }
        });
        queue.add(jsonObjectRequest);
    }
    public interface OnCreateProductFromLogin{
        public void createNewShopLogin(String token);
        public void errorInternetConnectionLogin();
    }
}
