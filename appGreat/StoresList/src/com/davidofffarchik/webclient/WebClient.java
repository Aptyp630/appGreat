package com.davidofffarchik.webclient;


import android.content.Context;
import android.widget.Toast;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.davidofffarchik.models.Product;
import com.davidofffarchik.models.User;
import com.davidofffarchik.models.UserSuccess;
import org.json.JSONObject;

public class WebClient {

    public static void callCreateNewProduct(Product product, String token, WebClientListener webClientListener) {
    }

    public static void callLogin(User user, WebClientListener webClientListener){

    }

    public static void callGetProducts(WebClientListener webClientListener){

    }

    public static void callRegistration(Context context, UserSuccess userSuccess, WebClientListener<UserSuccess> webClientListener) {

        Parameter<UserSuccess> parameter = new RegistrationParam(userSuccess);
            makeRequest(context, parameter, webClientListener);
    }

    private static void makeRequest(final Context context, final Parameter<UserSuccess> parameter, final WebClientListener<UserSuccess> webClientListener){
        RequestQueue queue = Volley.newRequestQueue(context);
        String url = parameter.getUrl();
        JSONObject jsonObject = parameter.getBody();
        final JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(parameter.getRequestMethod(), url+parameter.getApiMethod(), jsonObject , new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                if(parameter.parseResponse(context, jsonObject).getUser() != null)
                    webClientListener.onResponseSuccess(parameter.parseResponse(context, jsonObject));
                else
                    Toast.makeText(context, "Такой E-mail уже имеется в базе данных", Toast.LENGTH_LONG).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                webClientListener.onResponseError();
            }
        });
        queue.add(jsonObjectRequest);
    }
}
