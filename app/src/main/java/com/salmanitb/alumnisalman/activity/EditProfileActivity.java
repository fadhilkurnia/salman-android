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

    public static String EDIT_ARGUMENT = "EDIT";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        Bundle fragmentBundle = new Bundle();
        fragmentBundle.putBoolean(EDIT_ARGUMENT, true);

        editPersonal = new RegistrationPersonalFragment();
        editPersonal.setArguments(fragmentBundle);
        editAlmamater = new RegistrationAlmamaterFragment();
        editAlmamater.setArguments(fragmentBundle);
        editPekerjaan = new RegistrationJobFragment();
        editPekerjaan.setArguments(fragmentBundle);
        editKegiatan = new RegistrationActivityFragment();
        editKegiatan.setArguments(fragmentBundle);

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

        loadFragment(fragmentID);

    }

    private void loadFragment(String fragmentID) {

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if (fragmentID == null) {
            Log.d("EDIT_PROFILE", "null fragment ID");
        } else if (fragmentID.equals("PERSONAL")) {
            transaction.replace(R.id.edit_profile_frame, editPersonal);
        } else if (fragmentID.equals("ALMAMATER")) {
            transaction.replace(R.id.edit_profile_frame, editAlmamater);
        } else if (fragmentID.equals("PEKERJAAN")) {
            transaction.replace(R.id.edit_profile_frame, editPekerjaan);
        } else if (fragmentID.equals("KEGIATAN")) {
            transaction.replace(R.id.edit_profile_frame, editKegiatan);
        }
        transaction.commit();

    }
}
