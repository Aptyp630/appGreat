package com.davidofffarchik.webclient;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.davidofffarchik.models.Product;
import com.davidofffarchik.models.UserSuccess;
import org.json.JSONObject;

import java.util.Map;

public class VolleyAdaptor<T> extends Request<T>{

    private JSONObject jsonObject;
    private Class<T> tClass;
    private Map<UserSuccess, Product> userSuccessProductMap;
    private WebClientListener<T> webClientListener;

    public VolleyAdaptor(String url, Response.ErrorListener listener, JSONObject jsonObject, Class<T> tClass, Map<UserSuccess, Product> userSuccessProductMap, WebClientListener<T> webClientListener) {
        super(url, listener);
        this.jsonObject = jsonObject;
        this.tClass = tClass;
        this.userSuccessProductMap = userSuccessProductMap;
        this.webClientListener = webClientListener;
    }

    public VolleyAdaptor(int method, String url, Response.ErrorListener listener) {
        super(method, url, listener);
    }

    @Override
    protected Response<T> parseNetworkResponse(NetworkResponse networkResponse) {

        return null;
    }

    @Override
    protected void deliverResponse(T t) {

    }
}
