package com.davidofffarchik.webclient;

import android.util.Log;
import android.widget.Toast;
import com.android.volley.Request;
import com.davidofffarchik.models.RegistrationResponse;
import com.davidofffarchik.models.User;
import org.json.JSONException;
import org.json.JSONObject;

public class RegistrationParam extends Parameter<RegistrationResponse> {

   private User user;

    public RegistrationParam(User user){
        this.user = user;
    }

    @Override
    public int getRequestMethod() {
        return Request.Method.POST;
    }

    @Override
    public RegistrationResponse parseResponse(JSONObject jsonObject) {
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
                return new RegistrationResponse(new User(token));
            }else{
                String message = jsonObject.getString("message");
                Log.v("Message", "is " + message);
                boolean success = jsonObject.getBoolean("success");
                Log.v("Success", "is " + success);
                Toast.makeText(GetOwnContext.getInstance(), "Такой Email уже есть в базе!", Toast.LENGTH_LONG).show();
                return new RegistrationResponse(success);
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
            jsonObject.put("email", user.getEmail());
            jsonObject.put("password", user.getPassword());
            jsonObject.put("password_confirmation", user.getConfirmedPassword());
            jsonObject.put("username", user.getUserName());
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
