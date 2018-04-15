package com.salmanitb.alumnisalman.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.salmanitb.alumnisalman.R;
import com.salmanitb.alumnisalman.SalmanApplication;
import com.salmanitb.alumnisalman.activity.RegistrationActivity;
import com.salmanitb.alumnisalman.helper.APIConnector;
import com.salmanitb.alumnisalman.helper.RegistrationCheckerCallback;
import com.salmanitb.alumnisalman.helper.RegistrationStepFragment;
import com.salmanitb.alumnisalman.model.SalmanActivity;
import com.salmanitb.alumnisalman.model.User;
import com.salmanitb.alumnisalman.model.UserAuth;

import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * A simple {@link Fragment} subclass.
 */
public class RegistrationConfirmationFragment extends RegistrationStepFragment {

    @BindView(R.id.txt_error)
    TextView txtError;
    @BindView(R.id.txt_confirmation)
    TextView txtConfirmation;
    @BindView(R.id.txt_question_1)
    TextView txtQuestion1;
    @BindView(R.id.txt_question_2)
    TextView txtQuestion2;
    @BindView(R.id.input_answer_1)
    EditText inputAnswer1;
    @BindView(R.id.input_answer_2)
    EditText inputAnswer2;
    @BindView(R.id.is_agree)
    CheckBox isAgree;

    public RegistrationConfirmationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_registration_confirmation, container, false);
        ButterKnife.bind(this, rootView);

        // Generate random question from default data
        String questions[] = getResources().getStringArray(R.array.default_registered_question);
        Random random = new Random(System.currentTimeMillis());
        int idx = random.nextInt(questions.length);
        int nextIdx = random.nextInt(questions.length);
        while (idx == nextIdx)
            nextIdx = random.nextInt(questions.length);
        Log.d("SALMAN_APP", "idx: " + idx + " nextIdx: " + nextIdx + " length: " + questions.length);
        txtQuestion1.setText(questions[idx]);
        txtQuestion2.setText(questions[nextIdx]);

        // Show user data
        showUserData();

        return rootView;
    }

    private void showUserData() {
        StringBuilder stringBuilder = new StringBuilder();
        User user = SalmanApplication.getCurrentUser();
        stringBuilder.append("Nama   : " + user.getName() + "\n");
        stringBuilder.append("Email  : " + user.getEmail() + "\n");
        stringBuilder.append("Password  : ");
        for(char c : user.getPassword().toCharArray())
            stringBuilder.append("*");
        stringBuilder.append("\n\n");

        stringBuilder.append("Negara : " + user.getCountry() + "\n");
        stringBuilder.append("Kota   : " + user.getCity() + "\n");
        stringBuilder.append("Alamat : " + user.getAddress() + "\n\n");

        stringBuilder.append("Angkatan LMD/LSI   : " + user.getLmd() + "\n");
        stringBuilder.append("Aktivitas   : \n");
        for(SalmanActivity activity: SalmanApplication.getCurrentUser().getActivities()) {
            stringBuilder.append("     - " + activity.toString() + "\n");
        }

        stringBuilder.append("Kampus   : " + user.getUniversity() + "\n");
        stringBuilder.append("Juruan   : " + user.getMajor() + "\n");
        stringBuilder.append("Angkatan   : " + user.getYearUniversity() + "\n\n");

        stringBuilder.append("Pekerjaan   : " + user.getJob() + "\n");
        stringBuilder.append("Institusi   : " + user.getCompany() + "\n");
        txtConfirmation.setText(stringBuilder.toString());
    }

    @Override
    public void checkInput(final RegistrationCheckerCallback callback) {
        final StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Harap perhatikan data yang anda input, terjadi kesalahan:\n");

        String answer1 = inputAnswer1.getText().toString().trim();
        String answer2 = inputAnswer2.getText().toString().trim();
        if (answer1.equals("") || answer2.equals("")) {
            stringBuilder.append("  - Ada pertanyaan yang belum dijawab\n");
            txtError.setText(stringBuilder.toString());
            txtError.setVisibility(View.VISIBLE);
            Toast.makeText(getActivity(), "Terjadi kesalahan input", Toast.LENGTH_SHORT).show();
            callback.onFinishChecking(false);
            return;
        }

        if (!isAgree.isChecked()) {
            stringBuilder.append("  - Pastikan Anda sudah menyetujui pernyataan yang ada\n");
            txtError.setText(stringBuilder.toString());
            txtError.setVisibility(View.VISIBLE);
            Toast.makeText(getActivity(), "Terjadi kesalahan input", Toast.LENGTH_SHORT).show();
            callback.onFinishChecking(false);
            return;
        }

        SalmanApplication.getCurrentUser().setQuestion1(txtQuestion1.getText().toString());
        SalmanApplication.getCurrentUser().setQuestion2(txtQuestion2.getText().toString());
        SalmanApplication.getCurrentUser().setAnswer1(inputAnswer1.getText().toString());
        SalmanApplication.getCurrentUser().setAnswer2(inputAnswer2.getText().toString());

        APIConnector.getInstance().doRegister(SalmanApplication.getCurrentUser(), new APIConnector.ApiCallback<UserAuth>() {
            @Override
            public void onSuccess(UserAuth response) {
                callback.onFinishChecking(true);
            }

            @Override
            public void onFailure(Throwable t) {
                Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();
                callback.onFinishChecking(false);
            }
        });
    }

    @OnClick(R.id.txt_error)
    public void hide() {
        txtError.setVisibility(View.GONE);
    }

}
