package com.davidofffarchik.webclient;

import android.content.Context;
import android.util.Log;
import com.android.volley.Request;
import com.davidofffarchik.models.User;
import com.davidofffarchik.models.UserSuccess;
import org.json.JSONException;
import org.json.JSONObject;

public class RegistrationParam  extends Parameter<UserSuccess> {

   private UserSuccess userSuccess;

    public RegistrationParam(UserSuccess userSuccess){
        this.userSuccess = userSuccess;
    }

    @Override
    public int getRequestMethod() {
        return Request.Method.POST;
    }

    @Override
    public UserSuccess parseResponse(Context context, JSONObject jsonObject) {
        try {
            if(jsonObject.has("user")) {
                JSONObject jsonRegistration = jsonObject.getJSONObject("user");
                String token = jsonRegistration.getString("token");
                Log.v("Token", "is " + token);
                String email = jsonRegistration.getString("email");
                Log.v("Email", "is " + email);
                boolean success = jsonRegistration.getBoolean("success");
                Log.v("Success", "is " + success);
                Log.v("Что я парсю", "" + jsonRegistration);
                return new UserSuccess(new User(token));
            }else{
                String message = jsonObject.getString("message");
                Log.v("Message", "is " + message);
                boolean success = jsonObject.getBoolean("success");
                Log.v("Success", "is " + success);
                return new UserSuccess(success);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public JSONObject getBody() {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("email", userSuccess.getUser().getEmail());
            jsonObject.put("password", userSuccess.getUser().getPassword());
            jsonObject.put("password_confirmation", userSuccess.getUser().getConfirmedPassword());
            jsonObject.put("username", userSuccess.getUser().getUserName());
            Log.v("Отправленные данные при регистрации", "" +jsonObject);
            return jsonObject;
        }catch (Exception e){
            e.printStackTrace();
        }
        return jsonObject;
    }

    @Override
    public String getApiMethod() {
        return "create_user.json";
    }
}
