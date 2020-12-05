package com.example.uts.jagadita.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.uts.jagadita.models.Pengguna;
import com.google.gson.Gson;

public class PreferencesHelper {
    private SharedPreferences sharedPreferences;
    private final String PREFERENCES_NAME="shared_preferences";
    private final String LOGIN="login";
    private final String USER_ID="user_id";
    private final String USER_DATA = "user_data";

    public PreferencesHelper(Context context) {
        sharedPreferences=context.getSharedPreferences(PREFERENCES_NAME,Context.MODE_PRIVATE);
    }

    public void setLogin(boolean login){
        sharedPreferences.edit().putBoolean(LOGIN,login).apply();
    }

    public boolean getLogin(){
        return sharedPreferences.getBoolean(LOGIN,false);
    }


    public void setUserId(int user_id){
        sharedPreferences.edit().putInt(USER_ID,user_id).apply();
    }

    public int getUserId(){
        return sharedPreferences.getInt(USER_ID,0);
    }

    public void setUser(Pengguna user){
        Gson gson = new Gson();
        String jsonUser = gson.toJson(user);
        sharedPreferences.edit().putString(USER_DATA,jsonUser).apply();
    }

    public Pengguna getUser(){
        Gson gson = new Gson();
        String json = sharedPreferences.getString(USER_DATA, "");
        return gson.fromJson(json, Pengguna.class);
    }

    public void setUserLogin(Pengguna user){
        setLogin(true);
        setUserId(user.getId());
        setUser(user);
    }

    public void logout(){
        sharedPreferences.edit().clear().apply();
    }
}
