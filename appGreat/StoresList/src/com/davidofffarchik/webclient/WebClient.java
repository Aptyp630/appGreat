package com.davidofffarchik.webclient;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.davidofffarchik.models.RegistrationResponse;
import com.davidofffarchik.models.User;
import org.json.JSONObject;

public class WebClient {

private RequestQueue queue;

    public WebClient(RequestQueue queue) {
        this.queue = queue;
    }

    /*
        public static void callCreateNewProduct(Parameter<ProductResult> productResultParameter, WebClientListener webClientListener) {
        }

        public static void callLogin(, WebClientListener webClientListener){

        }

        public static void callGetProducts(, WebClientListener webClientListener){

        }
    */
    public static void callRegistration(User user, WebClientListener<RegistrationResponse> webClientListener) {
        Parameter<RegistrationResponse> parameter = new RegistrationParam(user);
            makeRequest(parameter, webClientListener);
    }

    private static void makeRequest (final Parameter parameter, final WebClientListener webClientListener){
        
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
            public void onErrorResponse(VolleyError volleyError) {
                webClientListener.onResponseError();
            }
        });
        queue.add(jsonObjectRequest);
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
