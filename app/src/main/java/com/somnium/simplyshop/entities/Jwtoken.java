package com.somnium.simplyshop.entities;

import android.content.Context;
import android.content.SharedPreferences;

public class Jwtoken {
    private String auth_token;


    public static void saveToken(Context context,Jwtoken token) {
        SharedPreferences preferences = context.getSharedPreferences("TokenInfo", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("auth_token", token.getAuth_token());
        editor.apply();
    }

    public static Jwtoken getToken(Context context) {
        Jwtoken token = new Jwtoken();
        SharedPreferences sharedPreferences = context.getSharedPreferences("TokenInfo", Context.MODE_PRIVATE);
        token.setAuth_token(sharedPreferences.getString("auth_token", ""));
        return token;
    }

    public static void deleteToken(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("TokenInfo", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove("auth_token");
        editor.apply();
    }

    public String getAuth_token() {
        return auth_token;
    }

    public void setAuth_token(String auth_token) {
        this.auth_token = auth_token;
    }
}
