package com.salmanitb.alumnisalman;

import android.app.Application;

import com.salmanitb.alumnisalman.helper.PreferenceManager;

/**
 * Created by Fadhil Imam Kurnia on 30/03/2018.
 */

public class SalmanApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        PreferenceManager.getInstance().setApplicationCOntext(getApplicationContext());
    }
}
