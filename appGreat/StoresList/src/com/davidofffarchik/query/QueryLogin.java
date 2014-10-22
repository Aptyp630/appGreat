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

    public static void logInTo(final Context context, final String email, final String password){
        String url = "http://protected-wave-2984.herokuapp.com/api/login.json";
        RequestQueue queue = Volley.newRequestQueue(context);
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("email", email);
            jsonObject.put("password", password);
        }catch (Exception e){
            e.printStackTrace();
        }
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, jsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                Log.v(Constans.LOG_TAG, "Вывод для логина " + jsonObject);
                try {
                    String emailLogin = jsonObject.getString("email");
                    String passwordLogin = jsonObject.getString("password");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Log.v(Constans.LOG_TAG, "Ошибка сети");
            }
        });
        queue.add(jsonObjectRequest);
    }

}
