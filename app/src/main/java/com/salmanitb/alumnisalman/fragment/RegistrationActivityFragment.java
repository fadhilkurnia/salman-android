package com.salmanitb.alumnisalman.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.salmanitb.alumnisalman.R;
import com.salmanitb.alumnisalman.SalmanApplication;
import com.salmanitb.alumnisalman.activity.RegisterActivity;
import com.salmanitb.alumnisalman.activity.RegistrationActivity;
import com.salmanitb.alumnisalman.helper.RegistrationChecker;
import com.salmanitb.alumnisalman.helper.RegistrationCheckerCallback;
import com.salmanitb.alumnisalman.helper.RegistrationStepFragment;
import com.salmanitb.alumnisalman.model.ActivityView;
import com.salmanitb.alumnisalman.model.SalmanActivity;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.salmanitb.alumnisalman.activity.EditProfileActivity.EDIT_ARGUMENT;


/**
 * A simple {@link Fragment} subclass.
 */
public class RegistrationActivityFragment extends RegistrationStepFragment {


    @BindView(R.id.txt_error)
    TextView txtError;
    @BindView(R.id.input_lmd)
    EditText inputLMD;
    @BindView(R.id.activity_row_layout)
    LinearLayout activityLayoutRow;
    @BindView(R.id.checkbox_others)
    CheckBox checkBoxOthers;
    @BindView(R.id.input_others)
    EditText inputOthers;

    ArrayList<ActivityView> inputActivity;

    private boolean isEdit;

    public RegistrationActivityFragment() {
        // Required empty public constructor
        isEdit = false;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_registration_activity, container, false);
        ButterKnife.bind(this, rootView);

        inputActivity = new ArrayList<>();
        for (String activity: getResources().getStringArray(R.array.default_registered_activity)) {
            ActivityView view = new ActivityView(getActivity(), activity);
            inputActivity.add(view);
            activityLayoutRow.addView(view);
        }

        checkBoxOthers.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
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
    }

    @Override
    public void checkInput(RegistrationCheckerCallback callback) {
        final StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Harap perhatikan data yang anda input, terjadi kesalahan:\n");

        StringBuilder sbActivity = new StringBuilder();
        StringBuilder sbTime = new StringBuilder();
        int counter = 0;
        for (ActivityView activity: inputActivity) {
            if (activity.isChecked()) {
                counter++;
                sbActivity.append(activity.getTitle());
                sbActivity.append(", ");

                String startYear = activity.getStartYear();
                String endYear = activity.getEndYear();
                if (startYear.length() != 4 || endYear.length() != 4) {
                    stringBuilder.append("  - Format tahun untuk ")
                            .append(activity.getTitle())
                            .append(" salah, contoh yang benar : 2004\n");
                    txtError.setText(stringBuilder.toString());
                    txtError.setVisibility(View.VISIBLE);
                    Toast.makeText(getActivity(), "Terjadi kesalahan input", Toast.LENGTH_SHORT).show();
                    callback.onFinishChecking(false);
                    return;
                }
                sbTime.append(activity.getStartYear());
                sbTime.append("-");
                sbTime.append(activity.getEndYear());
                sbTime.append(", ");
            }
        }

        if (sbActivity.length() > 2) {
            sbActivity.delete(sbActivity.length()-2, sbActivity.length());
            sbTime.delete(sbTime.length()-2, sbTime.length());
        }

        if (counter == 0 && !checkBoxOthers.isChecked()) {
            stringBuilder.append("  - Minimal ada kegiatan lainnya yang dimasukan\n");
            txtError.setText(stringBuilder.toString());
            txtError.setVisibility(View.VISIBLE);
            Toast.makeText(getActivity(), "Terjadi kesalahan input", Toast.LENGTH_SHORT).show();
            callback.onFinishChecking(false);
            return;
        }

        if (checkBoxOthers.isChecked()) {
            sbActivity.append(", ").append(inputOthers.getText().toString());
        }

        SalmanApplication.getCurrentUser().setLmd(inputLMD.getText().toString());
        SalmanApplication.getCurrentUser().setActivities(new ArrayList<SalmanActivity>());
        for (ActivityView activityView: inputActivity) {
            if (activityView.isChecked()) {
                SalmanActivity salmanActivity = new SalmanActivity(activityView.getTitle(), activityView.getStartYear(), activityView.getEndYear());
                SalmanApplication.getCurrentUser().getActivities().add(salmanActivity);
            }
        }
        if (checkBoxOthers.isChecked()) {
            if (SalmanApplication.getCurrentUser().getActivities() == null)
                SalmanApplication.getCurrentUser().setActivities(new ArrayList<SalmanActivity>());
            SalmanActivity salmanActivity = new SalmanActivity(inputOthers.getText().toString(), "", "");
            SalmanApplication.getCurrentUser().getActivities().add(salmanActivity);
        }
        callback.onFinishChecking(true);
    }

    @OnClick(R.id.txt_error)
    protected void hide() {
        txtError.setVisibility(View.GONE);
    }

}
