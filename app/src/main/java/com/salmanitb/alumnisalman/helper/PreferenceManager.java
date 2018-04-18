package com.salmanitb.alumnisalman.helper;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.google.gson.Gson;
import com.salmanitb.alumnisalman.model.About;
import com.salmanitb.alumnisalman.model.User;
import com.salmanitb.alumnisalman.model.UserAuth;

/**
 * Created by Fadhil Imam Kurnia on 30/03/2018.
 */

public class PreferenceManager {
    private static final String NAME = "com.salmanitb.alumnisalman";
    private static PreferenceManager preferenceManager;
    private static Gson gson;
    private Context mApplicationContext;

    private final String KEY_USER_AUTH= "KEY_USER_AUTH";
    private final String KEY_USER_DATA= "KEY_USER_AUTH";
    private final String KEY_USER_TOKEN = "KEY_USER_TOKEN";
    private final String KEY_ABOUT_DATA = "KEY_ABOUT_DATA";

    public static PreferenceManager getInstance() {
        if (preferenceManager == null) {
            preferenceManager = new PreferenceManager();
            gson = new Gson();
        }
        return preferenceManager;
    }

    public void setApplicationCOntext(Context context) {
        mApplicationContext = context;
    }

    public UserAuth getUserAuth() {
        SharedPreferences preferences = mApplicationContext.getSharedPreferences(NAME, Context.MODE_PRIVATE);
        String responseJSON = preferences.getString(KEY_USER_AUTH, null);
        Log.d("SALMAN_APP getUserAuth", responseJSON);
        return gson.fromJson(responseJSON, UserAuth.class);
    }

    public void setUserAuth(UserAuth userAuth) {
        Gson gson = new Gson();
        Log.d("SALMAN_APP setUserAuth", gson.toJson(userAuth));
        SharedPreferences preferences = mApplicationContext.getSharedPreferences(NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        String json = gson.toJson(userAuth);
        editor.putString(KEY_USER_AUTH, json);
        editor.apply();
    }

    public User getUserData() {
        SharedPreferences preferences = mApplicationContext.getSharedPreferences(NAME, Context.MODE_PRIVATE);
        String responseJSON = preferences.getString(KEY_USER_DATA, null);
        return gson.fromJson(responseJSON, User.class);
    }

    public void setUserData(User user) {
        SharedPreferences preferences = mApplicationContext.getSharedPreferences(NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        String json = gson.toJson(user);
        editor.putString(KEY_USER_DATA, json);
        editor.apply();
    }

    public String getUserToken() {
        SharedPreferences preferences = mApplicationContext.getSharedPreferences(NAME, Context.MODE_PRIVATE);
        return preferences.getString(KEY_USER_TOKEN,"");
    }

    public void setUserToken(String userToken) {
        SharedPreferences preferences = mApplicationContext.getSharedPreferences(NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(KEY_USER_TOKEN, userToken);
        editor.apply();
    }

    public About getAboutData() {
        SharedPreferences preferences = mApplicationContext.getSharedPreferences(NAME, Context.MODE_PRIVATE);
        String aboutText = preferences.getString(KEY_ABOUT_DATA, null);
        if (aboutText == null)
            return null;

        return gson.fromJson(aboutText, About.class);
    }

    public void setAboutData(About about) {
        SharedPreferences preferences = mApplicationContext.getSharedPreferences(NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();

        String aboutText = gson.toJson(about);
        editor.putString(KEY_ABOUT_DATA, aboutText);
    }

}
