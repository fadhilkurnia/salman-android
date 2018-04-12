package com.salmanitb.alumnisalman.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.salmanitb.alumnisalman.R;
import com.salmanitb.alumnisalman.helper.APIConnector;
import com.salmanitb.alumnisalman.helper.PreferenceManager;
import com.salmanitb.alumnisalman.model.UserAuth;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity {

    @OnClick(R.id.txt_register)
    public void gotoRegister() {
        Intent i = new Intent(this, RegisterActivity.class);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(i);
    }

    @OnClick(R.id.btn_login)
    public void doLogin() {
        UserAuth userAuth = new UserAuth();

        // TODO: Get data from user input, do validation first!
        userAuth.setEmail("verified@gmail.com");
        userAuth.setPassword("ganteng");

        final Context context = this;
        APIConnector.getInstance().doLogin(userAuth.getEmail(), userAuth.getPassword(), new APIConnector.ApiCallback<UserAuth>() {
            @Override
            public void onSuccess(UserAuth response) {
                PreferenceManager.getInstance().setUserAuth(response);
                Intent intent = new Intent(context, ConfirmActivity.class);
                startActivity(intent);
            }

            @Override
            public void onFailure(Throwable t) {
                Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

    }

}
