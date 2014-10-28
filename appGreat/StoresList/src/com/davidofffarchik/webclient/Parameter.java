package com.davidofffarchik.webclient;

import android.content.Context;
import org.json.JSONObject;

public abstract class Parameter<T> {

    abstract public int getRequestMethod();

    abstract public T parseResponse (Context context, JSONObject jsonObject);

    abstract public JSONObject getBody();

    abstract public String getApiMethod();

    public String getUrl(){
        return "http://protected-wave-2984.herokuapp.com/api/";
    }
}
