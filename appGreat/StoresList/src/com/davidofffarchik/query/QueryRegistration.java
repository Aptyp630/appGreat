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

public class QueryRegistration{

    public static void sendAutorizationData(
            final Context context,
            final String userEmail,
            final String password,
            final String passwordConfirm,
            final String userName,
            final OnCreateProductFromRegistration listener
    ) {
        String url = "http://protected-wave-2984.herokuapp.com/api/create_user.json";
        RequestQueue queue = Volley.newRequestQueue(context);
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("email", userEmail);
            jsonObject.put("password", password);
            jsonObject.put("password_confirmation", passwordConfirm);
            jsonObject.put("username", userName);
            Log.v("Отправленные данные при регистрации", "" +jsonObject);
        }catch (Exception e){
            e.printStackTrace();
        }
        final JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, jsonObject,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        Log.v("что мне приходит", "" +jsonObject);
                        try {
                            JSONObject jsonRegistration = jsonObject.getJSONObject("user");
                            String token = jsonRegistration.getString("token");
                            Log.v("Token", "is " +token);
                            String email = jsonRegistration.getString("email");
                            Log.v("Email", "is " +email);
                            boolean success = jsonRegistration.getBoolean("success");
                            Log.v("Success", "is " +success);
                            Log.v("Что я парсю", "" +jsonRegistration);
                            listener.createNewShopRegistration();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        listener.errorInternetConnectionRegistration();
                    }
                });
        queue.add(jsonObjectRequest);
    }

    public interface OnCreateProductFromRegistration {
        public void createNewShopRegistration();
        public void errorInternetConnectionRegistration();
    }

}
