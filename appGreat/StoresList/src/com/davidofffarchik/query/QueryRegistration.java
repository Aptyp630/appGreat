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
import com.davidofffarchik.constans.Constans;
import org.json.JSONException;
import org.json.JSONObject;

public class QueryRegistration{

    public static void sendAutorizationData(
            final Context context,
            final String userEmail,
            final String password,
            final String passwordConfirm,
            final String userName
    ) {
        String url = "http://protected-wave-2984.herokuapp.com/api/create_user.json";
        RequestQueue queue = Volley.newRequestQueue(context);
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("email", userEmail);
            jsonObject.put("password", password);
            jsonObject.put("password_confirmation", passwordConfirm);
            jsonObject.put("username", userName);
        }catch (Exception e){
            e.printStackTrace();
        }
        final JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, jsonObject,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        try {
                            boolean success = jsonObject.getBoolean("success");
                            String login = jsonObject.getString("login");
                            String message = jsonObject.getString("message");
                            Log.v(Constans.LOG_TAG, "Успешно" +login);
                            if(success){
                                Log.v(Constans.LOG_TAG, "Успешно" +login);
                                Toast.makeText(context, "Успешно" +login, Toast.LENGTH_SHORT).show();
                            }else{
                                Log.v(Constans.LOG_TAG, "Проверьте правильность ввода данных!" +message);
                                Toast.makeText(context, "Проверьте правильность ввода данных!" +message, Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                });
        queue.add(jsonObjectRequest);
    }
}
