package com.salmanitb.alumnisalman.activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Toast;

import com.salmanitb.alumnisalman.R;
import com.salmanitb.alumnisalman.fragment.RegistrationActivityFragment;
import com.salmanitb.alumnisalman.fragment.RegistrationAlmamaterFragment;
import com.salmanitb.alumnisalman.fragment.RegistrationJobFragment;
import com.salmanitb.alumnisalman.fragment.RegistrationPersonalFragment;
import com.salmanitb.alumnisalman.helper.RegistrationStepFragment;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

import static com.salmanitb.alumnisalman.SalmanApplication.currentUser;
import static com.salmanitb.alumnisalman.model.User.MALE;

public class EditProfileActivity extends AppCompatActivity {

    @BindView(R.id.edit_profile_frame)
    FrameLayout frame_layout;

    RegistrationStepFragment editPersonal;
    RegistrationStepFragment editAlmamater;
    RegistrationStepFragment editPekerjaan;
    RegistrationStepFragment editKegiatan;

    //Edit Profil
    @BindView(R.id.input_name)
    EditText inputName;
    @BindView(R.id.input_email)
    EditText inputEmail;
    @BindView(R.id.radio_male)
    RadioButton radioMale;
    @BindView(R.id.radio_female)
    RadioButton radioFemale;
    @BindView(R.id.input_phone)
    EditText inputPhone;
    @BindView(R.id.input_country)
    EditText inputCountry;
    @BindView(R.id.input_city)
    EditText inputCity;
    @BindView(R.id.input_address)
    EditText inputAddress;

    //Edit Almamater
    @BindView(R.id.input_campus)
    EditText inputCampus;
    @BindView(R.id.input_major)
    EditText inputMajor;
    @BindView(R.id.input_year)
    EditText inputYear;

    //Edit Pekerjaan
    @BindView(R.id.leftColumn)
    LinearLayout leftColumn;
    @BindView(R.id.rightColumn)
    LinearLayout rightColumn;
    @BindView(R.id.input_company)
    EditText inputCompany;
    @BindView(R.id.checkbox_others)
    CheckBox checkboxOthers;
    @BindView(R.id.input_others)
    EditText inputOthers;

    ArrayList<CheckBox> jobCheckbox;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        ButterKnife.bind(this);

        String fragmentID;
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                fragmentID = null;
            } else {
                fragmentID = extras.getString("FRAGMENT_ID");
            }
        } else {
            fragmentID = (String) savedInstanceState.getSerializable("FRAGMENT_ID");
        }

        laodFragment(fragmentID);
        loadData(fragmentID);


    }

    private void loadData(String fragmentID) {
        if (fragmentID == null) {
            Log.d("EDIT_PROFILE", "null fragment ID");
        } else if (fragmentID.equals("PERSONAL")) {
            inputName.setText(currentUser.getName());
            inputEmail.setText(currentUser.getEmail());
            if (currentUser.getSex().equals(MALE))
                radioMale.setChecked(true);
            else
                radioFemale.setChecked(true);
            inputPhone.setText(currentUser.getPhonenumber());
            inputCountry.setText(currentUser.getCountry());
            inputCity.setText(currentUser.getCity());
            inputAddress.setText(currentUser.getAddress());
        } else if (fragmentID.equals("ALMAMATER")) {
            inputCampus.setText(currentUser.getUniversity());
            inputMajor.setText(currentUser.getMajor());
            inputYear.setText(currentUser.getYearUniversity());
        } else if (fragmentID.equals("PEKERJAAN")) {
            jobCheckbox = new ArrayList<>();
            String jobs[] = getResources().getStringArray(R.array.default_registered_profession);
            prepareJobOption(leftColumn, rightColumn, jobs);

            checkboxOthers.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        inputOthers.setEnabled(true);
                        inputOthers.setBackground(getResources().getDrawable(R.drawable.edit_text_background));
                    } else {
                        inputOthers.setEnabled(false);
                        inputOthers.setText("");
                        inputOthers.setBackground(getResources().getDrawable(R.drawable.edit_text_background_disabled));
                    }
                }
            });
        } else if (fragmentID.equals("KEGIATAN")) {
        }
    }


    private void prepareJobOption(LinearLayout leftColumn, LinearLayout rightColumn, String data[]) {
        for (int i = 0; i < data.length; i++) {
            CheckBox checkBox = new CheckBox(this);
            checkBox.setText(data[i]);

            if (i%2==0)
                leftColumn.addView(checkBox);
            else
                rightColumn.addView(checkBox);

            jobCheckbox.add(checkBox);
        }
    }

    private void laodFragment(String fragmentID) {

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if (fragmentID == null) {
            Log.d("EDIT_PROFILE", "null fragment ID");
        } else if (fragmentID.equals("PERSONAL")) {
            editPersonal = new RegistrationPersonalFragment();
            transaction.replace(R.id.edit_profile_frame, editPersonal);
        } else if (fragmentID.equals("ALMAMATER")) {
            editAlmamater = new RegistrationAlmamaterFragment();
            transaction.replace(R.id.edit_profile_frame, editAlmamater);
        } else if (fragmentID.equals("PEKERJAAN")) {
            editPekerjaan = new RegistrationJobFragment();
            transaction.replace(R.id.edit_profile_frame, editPekerjaan);
        } else if (fragmentID.equals("KEGIATAN")) {
            editKegiatan = new RegistrationActivityFragment();
            transaction.replace(R.id.edit_profile_frame, editKegiatan);
        }
        transaction.commit();

    }
}
