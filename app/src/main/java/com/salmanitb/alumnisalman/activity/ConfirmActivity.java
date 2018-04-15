package com.salmanitb.alumnisalman.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.salmanitb.alumnisalman.R;
import com.salmanitb.alumnisalman.helper.PreferenceManager;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class ConfirmActivity extends AppCompatActivity {

    @OnClick(R.id.icon_confirmation)
    public void gotoMainPage() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_logout)
    protected void doLogout() {
        PreferenceManager.getInstance().setUserAuth(null);
        Intent i = new Intent(this, LoginActivity.class);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(i);
    }

}
