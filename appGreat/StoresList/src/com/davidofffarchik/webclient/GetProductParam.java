package com.davidofffarchik.webclient;


import android.content.Context;
import com.davidofffarchik.models.Success;
import org.json.JSONObject;

public class GetProductParam extends Parameter<Success>{
    @Override
    public int getRequestMethod() {
        return 0;
    }

    @Override
    public Success parseResponse(Context context, JSONObject jsonObject) {
        return null;
    }

    @Override
    public JSONObject getBody() {
        return null;
    }

    @Override
    public String getApiMethod() {
        return null;
    }
}
