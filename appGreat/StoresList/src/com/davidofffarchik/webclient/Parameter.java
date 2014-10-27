package com.davidofffarchik.webclient;

import com.android.volley.Request;
import org.json.JSONObject;

public abstract class Parameter<T> {

    abstract public Request.Method getRequestMethod();

    abstract public T parseResponse (JSONObject jsonObject);

    abstract public JSONObject getBody();

    abstract public String getApiMethod();

    public String getUrl(){
        return null;
    }



}
