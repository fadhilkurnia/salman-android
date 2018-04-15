package com.salmanitb.alumnisalman.activity;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.salmanitb.alumnisalman.R;
import com.salmanitb.alumnisalman.fragment.RegistrationActivityFragment;
import com.salmanitb.alumnisalman.fragment.RegistrationAlmamaterFragment;
import com.salmanitb.alumnisalman.fragment.RegistrationConfirmationFragment;
import com.salmanitb.alumnisalman.fragment.RegistrationJobFragment;
import com.salmanitb.alumnisalman.fragment.RegistrationPersonalFragment;
import com.salmanitb.alumnisalman.helper.RegistrationCheckerCallback;
import com.salmanitb.alumnisalman.helper.RegistrationStepFragment;
import com.salmanitb.alumnisalman.model.User;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegistrationActivity extends AppCompatActivity {

    @BindView(R.id.step_progress)
    LinearLayout progressStep;
    @BindView(R.id.img_step_icon)
    ImageView imgStepIcon;
    @BindView(R.id.txt_step_description)
    TextView txtStepDescription;
    @BindView(R.id.btn_back)
    Button btnBack;
    @BindView(R.id.btn_next)
    Button btnNext;

    RegistrationStepFragment firstStep;
    RegistrationStepFragment secondStep;
    RegistrationStepFragment thirdStep;
    RegistrationStepFragment fourthStep;
    RegistrationStepFragment fifthStep;
    ArrayList<RegistrationStepFragment> stepFragments;
    int stepId = 0;

    public static User applicationUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        ButterKnife.bind(this);

        Intent currentIntent = getIntent();
        String email = currentIntent.getStringExtra(RegisterActivity.REGISTRATION_EMAIL);
        String password = currentIntent.getStringExtra(RegisterActivity.REGISTRATION_PASSWORD);

        applicationUser = new User();
        applicationUser.setEmail(email);
        applicationUser.setPassword(password);

        firstStep = new RegistrationPersonalFragment();
        secondStep = new RegistrationAlmamaterFragment();
        thirdStep = new RegistrationActivityFragment();
        fourthStep = new RegistrationJobFragment();
        fifthStep = new RegistrationConfirmationFragment();

        stepFragments = new ArrayList<>();
        stepFragments.add(firstStep);
        stepFragments.add(secondStep);
        stepFragments.add(thirdStep);
        stepFragments.add(fourthStep);
        stepFragments.add(fifthStep);

        loadStepFragment(stepFragments.get(stepId));
    }

    public void loadStepFragment(Fragment fragment) {
        // Change step icon and description
        changeRegistrationIcon();

        // Change current step indicator color to active
        View indicator = progressStep.getChildAt(stepId*2+1);
        indicator.setBackground(getResources().getDrawable(R.drawable.circle_status_active));
        View line = progressStep.getChildAt(stepId*2);
        line.setBackgroundColor(getResources().getColor(R.color.accent));

        // Handle exception for back button
        if (stepId == 0)
            btnBack.setVisibility(View.GONE);
        else
            btnBack.setVisibility(View.VISIBLE);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.registration_frame, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @OnClick(R.id.btn_back)
    protected void gotoPreviousStep() {
        if (stepId == 0)
            return;

        // set current step indicator to false before change to previous step
        View indicator = progressStep.getChildAt(stepId*2+1);
        indicator.setBackground(getResources().getDrawable(R.drawable.circle_status_false));
        View line = progressStep.getChildAt(stepId*2);
        line.setBackgroundColor(getResources().getColor(R.color.primaryLight));

        stepId--;
        loadStepFragment(stepFragments.get(stepId));
    }

    @OnClick(R.id.btn_next)
    protected void gotoNextStep() {

        stepFragments.get(stepId).checkInput(new RegistrationCheckerCallback() {
            @Override
            public void onFinishChecking(boolean isSuccess) {
                if (isSuccess) {
                    if (stepId < stepFragments.size()-1) {
                        // set current step indicator to true (finished) before change to next step
                        View indicator = progressStep.getChildAt(stepId*2+1);
                        indicator.setBackground(getResources().getDrawable(R.drawable.circle_status_true));

                        stepId++;
                        loadStepFragment(stepFragments.get(stepId));

                        return;
                    }

                    Intent i = new Intent(RegistrationActivity.this, ConfirmActivity.class);
                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(i);
                }
            }
        });

    }

    private void changeRegistrationIcon() {
        switch (stepId) {
            case 1:
                imgStepIcon.setImageResource(R.drawable.ic_action_contact);
                txtStepDescription.setText("Masukan data almamater sarjana (S1) Anda");
                break;
            case 2:
                imgStepIcon.setImageResource(R.drawable.ic_mail);
                txtStepDescription.setText("Masukan data kegiatan yang pernah anda ikuti di Masjid Salman");
                break;
            case 3:
                imgStepIcon.setImageResource(R.drawable.ic_phone);
                txtStepDescription.setText("Masukan data pekerjaan Anda saat ini");
                break;
            case 4:
                imgStepIcon.setImageResource(R.drawable.ic_address);
                txtStepDescription.setText("Jawab pertanyaan dari kami, dan pastikan data Anda sudah benar");
                break;
            default:
                imgStepIcon.setImageResource(R.drawable.ic_action_profile);
                txtStepDescription.setText("Masukan data personal dan domisili Anda");
                break;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

}
