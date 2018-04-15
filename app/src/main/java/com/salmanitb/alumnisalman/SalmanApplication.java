package com.salmanitb.alumnisalman;

import android.app.Application;

import com.salmanitb.alumnisalman.helper.PreferenceManager;
import com.salmanitb.alumnisalman.model.User;
import com.salmanitb.alumnisalman.model.UserAuth;

/**
 * Created by Fadhil Imam Kurnia on 30/03/2018.
 */

public class SalmanApplication extends Application {

    public static User currentUser;
    public static UserAuth currentUserAuth;

    @Override
    public void onCreate() {
        super.onCreate();

        PreferenceManager.getInstance().setApplicationCOntext(getApplicationContext());

        // TODO: load current user from shared preferences
        currentUser = new User();
        currentUserAuth = PreferenceManager.getInstance().getUserAuth();
        if (currentUserAuth != null) {
            currentUser.setEmail(currentUserAuth.getEmail());
        }
    }

    public static User getCurrentUser() {
        return currentUser;
    }

    public static void setCurrentUser(User currentUser) {
        SalmanApplication.currentUser = currentUser;
    }

    public static UserAuth getCurrentUserAuth() {
        return currentUserAuth;
    }

}
