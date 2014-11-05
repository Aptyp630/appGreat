package com.davidofffarchik;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

public class UserToken {

    private SharedPreferences sharedPreferences;
    private final static String PREFERENCES = "shared";
    private static UserToken instance;

    private UserToken (){
    }

    public static UserToken getInstance(){
        if (null == instance){
            instance = new UserToken();
        }
        return instance;
    }

    public void saveToken(String token){
        sharedPreferences = StoresListApp.getInstance().getSharedPreferences(PREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Log.v("Show Token in Singleton", " " + token);
        editor.putString("token", token);
        editor.commit();
    }

    public String getSavedToken(){
        sharedPreferences = StoresListApp.getInstance().getSharedPreferences(PREFERENCES, Context.MODE_PRIVATE);
        return sharedPreferences.getString("token", "");
    }

}