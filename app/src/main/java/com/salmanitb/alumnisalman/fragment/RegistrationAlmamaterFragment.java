package com.salmanitb.alumnisalman.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.salmanitb.alumnisalman.R;
import com.salmanitb.alumnisalman.SalmanApplication;
import com.salmanitb.alumnisalman.activity.RegistrationActivity;
import com.salmanitb.alumnisalman.helper.RegistrationCheckerCallback;
import com.salmanitb.alumnisalman.helper.RegistrationStepFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.salmanitb.alumnisalman.SalmanApplication.currentUser;
import static com.salmanitb.alumnisalman.activity.EditProfileActivity.EDIT_ARGUMENT;


/**
 * A simple {@link Fragment} subclass.
 */
public class RegistrationAlmamaterFragment extends RegistrationStepFragment {

    @BindView(R.id.txt_error)
    TextView txtError;
    @BindView(R.id.input_campus)
    EditText inputCampus;
    @BindView(R.id.input_major)
    EditText inputMajor;
    @BindView(R.id.input_year)
    EditText inputYear;


    private boolean isEdit;

    public RegistrationAlmamaterFragment() {
        // Required empty public constructor
        isEdit = false;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_registration_almamater, container, false);
        ButterKnife.bind(this, rootView);

        Bundle bundle = this.getArguments();
        if (bundle != null) {
            isEdit = bundle.getBoolean(EDIT_ARGUMENT);
//            Log.d("FRAGMENT_DEBUG","isEdit true");
        }

        if (isEdit){
            setData();
        }

        return rootView;
    }

    private void setData() {
        inputCampus.setText(currentUser.getUniversity());
        inputMajor.setText(currentUser.getMajor());
        inputYear.setText(currentUser.getYearUniversity());
    }

    @Override
    public void checkInput(RegistrationCheckerCallback callback) {
        final StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Harap perhatikan data yang anda input, terjadi kesalahan:\n");

        final String campus = inputCampus.getText().toString().trim();
        final String major = inputMajor.getText().toString().trim();
        final String year = inputYear.getText().toString().trim();

        if (campus.equals("") || major.equals("") || year.equals("")) {
            if (campus.equals(""))
                stringBuilder.append("  - Kolom nama kampus masih kosong\n");
            if (major.equals(""))
                stringBuilder.append("  - Kolom jurusan masih kosong\n");
            if (year.equals(""))
                stringBuilder.append("  - Kolom angkatan masih kososng\n");
            txtError.setText(stringBuilder.toString());
            txtError.setVisibility(View.VISIBLE);
            showToast("Data belum terisi semuanya!");
            callback.onFinishChecking(false);
            return;
        }

        SalmanApplication.getCurrentUser().setUniversity(campus);
        SalmanApplication.getCurrentUser().setMajor(major);
        SalmanApplication.getCurrentUser().setYearUniversity(year);

        callback.onFinishChecking(true);
    }

    public void showToast(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.txt_error)
    protected void hide() {
        txtError.setVisibility(View.GONE);
    }

}
