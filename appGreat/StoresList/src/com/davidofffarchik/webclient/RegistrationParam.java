package com.davidofffarchik.webclient;

import com.android.volley.Request;
import com.davidofffarchik.models.Success;
import org.json.JSONObject;

public class RegistrationParam  extends Parameter<Success>{
    @Override
    public Request.Method getRequestMethod() {
        return null;
    }

    @Override
    public Success parseResponse(JSONObject jsonObject) {
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
