package com.salmanitb.alumnisalman.helper;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Fadhil Imam Kurnia on 30/03/2018.
 */

public class PreferenceManager {
    private static final String NAME = "com.salmanitb.alumnisalman";
    private static PreferenceManager preferenceManager;
    private Context mApplicationContext;

    private final String KEY_USER_TOKEN = "KEY_USER_TOKEN";

    public static PreferenceManager getInstance() {
        if (preferenceManager == null)
            preferenceManager = new PreferenceManager();
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

}
