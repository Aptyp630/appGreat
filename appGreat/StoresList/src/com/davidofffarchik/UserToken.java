package com.davidofffarchik;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;

public class UserToken extends ListFragment{

    private SharedPreferences sharedPreferences;
    private final static String PRIVATE_TOKEN = "userToken";
/*
    private static UserToken instance;

    public UserToken (){
    }

    public static UserToken getInstance(){
        if (null == instance){
            instance = new UserToken();
        }
        return instance;
    }*/

    private String getTokenStoreFragment(){
        Bundle bundle = getArguments();
        Log.v("Log id UserToken", " " +bundle.getString("token"));
        return bundle.getString("token");
    }

    public void saveToken(){
        sharedPreferences = getActivity().getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putString(PRIVATE_TOKEN, getTokenStoreFragment());
        edit.commit();
    }

    public String getSavedToken(){
        sharedPreferences = getActivity().getPreferences(Context.MODE_PRIVATE);
        String getSavedToken = sharedPreferences.getString(PRIVATE_TOKEN, getTokenStoreFragment());
        Log.v(PRIVATE_TOKEN, " " +getTokenStoreFragment());
        return getSavedToken;
    }
}
