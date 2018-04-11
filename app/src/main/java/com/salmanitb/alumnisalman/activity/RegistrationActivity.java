package com.salmanitb.alumnisalman.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.salmanitb.alumnisalman.R;
import com.salmanitb.alumnisalman.fragment.RegistrationPersonalFragment;

import butterknife.ButterKnife;

public class RegistrationActivity extends AppCompatActivity {

    Fragment firstStep;
    Fragment secondStep;
    Fragment thirdStep;
    Fragment fourthStep;
    Fragment fifthStep;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        ButterKnife.bind(this);

        firstStep = new RegistrationPersonalFragment();

        loadFragment(firstStep);
    }

    private void loadFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.registration_frame, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

}
