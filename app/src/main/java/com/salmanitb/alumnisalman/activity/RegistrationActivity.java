package com.salmanitb.alumnisalman.activity;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.salmanitb.alumnisalman.R;
import com.salmanitb.alumnisalman.fragment.RegistrationActivityFragment;
import com.salmanitb.alumnisalman.fragment.RegistrationAlmamaterFragment;
import com.salmanitb.alumnisalman.fragment.RegistrationPersonalFragment;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegistrationActivity extends AppCompatActivity {

    static Fragment firstStep;
    static Fragment secondStep;
    static Fragment thirdStep;
    static Fragment fourthStep;
    static Fragment fifthStep;
    Fragment[] stepFragments = {firstStep, secondStep, thirdStep, fourthStep, fifthStep};
    int stepId = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        ButterKnife.bind(this);

        firstStep = new RegistrationPersonalFragment();
        secondStep = new RegistrationAlmamaterFragment();
        thirdStep = new RegistrationActivityFragment();
        fourthStep = new RegistrationActivityFragment();
        fifthStep = new RegistrationActivityFragment();

        loadStepFragment(stepFragments[stepId]);
    }

    public void loadStepFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.registration_frame, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @OnClick(R.id.btn_next)
    protected void gotoNextStep() {
        if (stepId < stepFragments.length -1) {
            stepId++;
            loadStepFragment(stepFragments[stepId]);
            return;
        }

        Intent i = new Intent(this, ConfirmActivity.class);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(i);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

}
