package com.davidofffarchik.webclient;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.davidofffarchik.StoresListApp;
import com.davidofffarchik.models.*;
import com.davidofffarchik.webclientparams.*;
import org.json.JSONObject;

public class WebClient {

    private static WebClient instance;
    private RequestQueue queue;

    private WebClient(){
    }

    public static synchronized WebClient getInstance() {
        if (instance == null) {
            instance = new WebClient();
        }
        return instance;
    }

    private RequestQueue getRequestQueue() {
        if (queue == null) {
            queue = Volley.newRequestQueue(StoresListApp.getInstance().getApplicationContext());
        }
        return queue;
    }

    public void callDeleteProduct(NewProductResponse newProductResponse, WebClientListener<RegistrationResponse> webClientListener){
        Parameter<RegistrationResponse> parameter = new ProductDeleteParam(newProductResponse);
        makeRequest(parameter, webClientListener);
    }

    public void callCreateNewProduct(NewProductResponse newProductResponse, WebClientListener<Product> webClientListener) {
        Parameter<Product> parameter = new NewProductParam(newProductResponse);
        makeRequest(parameter, webClientListener);
    }

    public void callLogin(User user, WebClientListener<RegistrationResponse> webClientListener){
        Parameter<RegistrationResponse> parameter = new LoginParam(user);
        makeRequest(parameter, webClientListener);
    }

    public void callGetProducts(WebClientListener<ProductResult> webClientListener, int page){
        Parameter<ProductResult> parameter = new ProductQueryParam(page);
        makeRequest(parameter, webClientListener);
    }

    public void callRegistration(User user, WebClientListener<RegistrationResponse> webClientListener) {
        Parameter<RegistrationResponse> parameter = new RegistrationParam(user);
        makeRequest(parameter, webClientListener);
    }

    private void makeRequest(final Parameter parameter, final WebClientListener webClientListener){
        int requestMethod = parameter.getRequestMethod();
        String url = parameter.getUrl();
        String apiMethod = parameter.getApiMethod();
        JSONObject jsonObject = parameter.getBody();
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(requestMethod, url+apiMethod, jsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                webClientListener.onResponseSuccess(parameter.parseResponse(jsonObject));
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError){
                webClientListener.onResponseError();
            }
        });
        getRequestQueue().add(jsonObjectRequest);
    }
    /*
    private static void makeRequest(final Context context, final Parameter<RegistrationResponse> parameter, final WebClientListener<RegistrationResponse> webClientListener){
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
    */
}