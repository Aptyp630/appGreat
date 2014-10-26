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
import org.json.JSONException;
import org.json.JSONObject;

public class QueryToEnter {

    //####################################Request for Registration - start################################################

    public void queryRegistration(final Context context,
                                  final String userEmail,
                                  final String password,
                                  final String passwordConfirm,
                                  final String userName,
                                  final OnCreateProductFromRegistration listener)
    {
        RequestQueue queue = Volley.newRequestQueue(context);
        String url = "http://protected-wave-2984.herokuapp.com/api/create_user.json";
        JSONObject jsonObject = getJsonObject(userEmail, password, passwordConfirm, userName);
        final JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, jsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject){
                    onResponseRegistration(context, jsonObject, listener);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                listener.errorInternetConnectionRegistration();
            }
        });
        queue.add(jsonObjectRequest);
    }

    private JSONObject getJsonObject(String userEmail, String password, String passwordConfirm, String userName){
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("email", userEmail);
            jsonObject.put("password", password);
            jsonObject.put("password_confirmation", passwordConfirm);
            jsonObject.put("username", userName);
            Log.v("Отправляем при регистрации", " " +jsonObject);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }

    private void onResponseRegistration(Context context, JSONObject jsonObject, OnCreateProductFromRegistration listener) {
        JSONObject jsonForRegister = new JSONObject();
        try {
            jsonForRegister.getString("email");
            jsonForRegister.getBoolean("success");
            Toast.makeText(context, "Ошибочка вышла", Toast.LENGTH_LONG).show();
            Log.v("jsonObject", "is " +jsonObject);
            listener.createNewShopRegistration();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public interface OnCreateProductFromRegistration {
        public void createNewShopRegistration();
        public void errorInternetConnectionRegistration();
    }

    //####################################Request for Registration - end################################################

    //************************************Request for Login - start*****************************************************

public void queryLogin(final Context context,
                       final String email,
                       final String password,
                       final OnCreateProductFromLogin listener){
    RequestQueue queue = Volley.newRequestQueue(context);
    String url = "http://protected-wave-2984.herokuapp.com/api/login.json";
    JSONObject jsonObject = sendLoginData(email, password);
    final JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, jsonObject, new Response.Listener<JSONObject>() {
        @Override
        public void onResponse(JSONObject jsonObject) {
                onResponseLogin(jsonObject, listener);
        }
    }, new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError volleyError) {
            listener.responseError();
        }
    });
    queue.add(jsonObjectRequest);
}

    private JSONObject sendLoginData(String userEmail, String password){
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("email", userEmail);
            jsonObject.put("password", password);
            Log.v("То, что мы отправили", "" +jsonObject);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }

    private void onResponseLogin(JSONObject jsonObject, OnCreateProductFromLogin listener){
        Log.v("То, что мы получаем", "" +jsonObject);
            try {
                    JSONObject jsonLogin = jsonObject.getJSONObject("user");
                Log.v("То, что мы получаем в экземпляре", "" +jsonLogin);
                    String token = jsonLogin.getString("token");
                    jsonLogin.getString("email");
                    jsonLogin.getBoolean("success");
                    listener.createNewShopLogin(token);
                Log.v("То, что мы парсим", "" +jsonLogin);
            } catch (JSONException e) {
                e.printStackTrace();
            }
    }

    public interface OnCreateProductFromLogin {
        public void createNewShopLogin(String token);
        public void responseError();
    }

    //************************************Request for Login - end*****************************************************
}
