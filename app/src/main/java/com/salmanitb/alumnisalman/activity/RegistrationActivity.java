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

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegistrationActivity extends AppCompatActivity {

    Fragment firstStep;
    Fragment secondStep;
    Fragment thirdStep;
    Fragment fourthStep;
    Fragment fifthStep;
    ArrayList<Fragment> stepFragments;
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

        stepFragments = new ArrayList<>();
        stepFragments.add(firstStep);
        stepFragments.add(secondStep);
        stepFragments.add(thirdStep);
        stepFragments.add(fourthStep);
        stepFragments.add(fifthStep);

        loadStepFragment(stepFragments.get(stepId));
    }

    public void loadStepFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.registration_frame, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @OnClick(R.id.btn_next)
    protected void gotoNextStep() {
        if (stepId < stepFragments.size() -1) {
            stepId++;
            loadStepFragment(stepFragments.get(stepId));
            return;
        }

        Intent i = new Intent(this, ConfirmActivity.class);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        // finish(); finish hanya activity ini, tapi setFlag ngeclear semuanya
        startActivity(i);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

}
