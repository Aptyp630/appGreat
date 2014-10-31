package com.davidofffarchik.webclientparams;

import android.util.Log;
import com.android.volley.Request;
import com.davidofffarchik.models.RegistrationResponse;
import com.davidofffarchik.models.User;
import com.davidofffarchik.webclient.Parameter;
import org.json.JSONException;
import org.json.JSONObject;

public class LoginParam extends Parameter<RegistrationResponse> {

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
        try {
                JSONObject jsonLogin = jsonObject.getJSONObject("user");
                String token = jsonLogin.getString("token");
                Log.v("Token", "is " + token);
                jsonLogin.getString("email");
                jsonLogin.getBoolean("success");
                return new RegistrationResponse(new User(token));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public JSONObject getBody() {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("email", user.getEmail());
            jsonObject.put("password", user.getPassword());
            Log.v("То, что мы отправили", "" + jsonObject);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }

    @Override
    public String getApiMethod() {
        return "login.json";
    }
}
