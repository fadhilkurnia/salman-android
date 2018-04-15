package com.salmanitb.alumnisalman.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.salmanitb.alumnisalman.R;
import com.salmanitb.alumnisalman.SalmanApplication;
import com.salmanitb.alumnisalman.helper.APIConnector;
import com.salmanitb.alumnisalman.helper.PreferenceManager;
import com.salmanitb.alumnisalman.helper.WebService;
import com.salmanitb.alumnisalman.model.UserAuth;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        final Context context = this;
        UserAuth userAuth = SalmanApplication.getCurrentUserAuth();
        if (userAuth != null) {

            // Handle unverified user
            if (!userAuth.isVerified()) {
                gotoConfirmation();
                return;
            }

            // Handle verified user
            APIConnector.getInstance().doLogin(userAuth.getEmail(), userAuth.getPassword(), new APIConnector.ApiCallback<UserAuth>() {
                @Override
                public void onSuccess(UserAuth response) {
                    if (response != null)
                        gotoMain();
                    else {
                        Toast.makeText(context, "Terjadi kesalahan pada sistem kami", Toast.LENGTH_SHORT).show();
                        gotoLogin();
                    }
                }

                @Override
                public void onFailure(Throwable t) {
                    Toast.makeText(context, "Gagal melakukan autentikasi", Toast.LENGTH_SHORT).show();
                    gotoLogin();
                }
            });


        } else { // no cached user data found
            new Handler().postDelayed(new Runnable() {
                public void run() {
                    gotoLogin();
                }
            }, 500);
        }

    }

    private void gotoLogin() {
        Intent i = new Intent(this, LoginActivity.class);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(i);
    }

    private void gotoConfirmation() {
        Intent i = new Intent(this, ConfirmActivity.class);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(i);
    }

    private void gotoMain() {
        Intent i = new Intent(this, MainActivity.class);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(i);
    }
}
