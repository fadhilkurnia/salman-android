package com.salmanitb.alumnisalman.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.salmanitb.alumnisalman.R;
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
        Intent intent = new Intent(this, ConfirmActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        UserAuth userAuth = PreferenceManager.getInstance().getUserAuth();
        if (userAuth != null) {
            Intent i = new Intent(this, ConfirmActivity.class);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(i);
        }

    }

}
