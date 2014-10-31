package com.davidofffarchik.webclient;

import com.android.volley.Request;
import com.davidofffarchik.models.RegistrationResponse;
import com.davidofffarchik.models.User;
import org.json.JSONObject;

public class LoginParam extends Parameter<RegistrationResponse>{

    private User user;

    public LoginParam(User user) {
        this.user = user;
    }


    @Override
    public int getRequestMethod() {
        return Request.Method.POST;
    }

    @Override
    public RegistrationResponse parseResponse(JSONObject jsonObject) {
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
