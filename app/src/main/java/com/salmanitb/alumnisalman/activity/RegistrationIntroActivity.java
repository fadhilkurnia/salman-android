package com.salmanitb.alumnisalman.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.salmanitb.alumnisalman.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.salmanitb.alumnisalman.activity.RegisterActivity.REGISTRATION_EMAIL;
import static com.salmanitb.alumnisalman.activity.RegisterActivity.REGISTRATION_PASSWORD;

public class RegistrationIntroActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_intro);
        ButterKnife.bind(this);
    }


    @OnClick(R.id.btn_next)
    protected void gotoRegistration() {
        Intent currentIntent = getIntent();
        String email = currentIntent.getStringExtra(REGISTRATION_EMAIL);
        String password = currentIntent.getStringExtra(REGISTRATION_PASSWORD);

        Intent i = new Intent(this, RegistrationActivity.class);
        i.putExtra(REGISTRATION_EMAIL, email);
        i.putExtra(REGISTRATION_PASSWORD, password);
        startActivity(i);
        finish();
    }

}
