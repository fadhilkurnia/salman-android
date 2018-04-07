package com.salmanitb.alumnisalman.helper;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.salmanitb.alumnisalman.model.About;

/**
 * Created by Fadhil Imam Kurnia on 30/03/2018.
 */

public class PreferenceManager {
    private static final String NAME = "com.salmanitb.alumnisalman";
    private static PreferenceManager preferenceManager;
    private static Gson gson;
    private Context mApplicationContext;

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
