package com.salmanitb.alumnisalman.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.salmanitb.alumnisalman.R;

import butterknife.ButterKnife;

public class RegistrationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        ButterKnife.bind(this);
    }
}
