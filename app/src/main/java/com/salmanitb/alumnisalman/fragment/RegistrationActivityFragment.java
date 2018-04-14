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

import com.salmanitb.alumnisalman.R;
import com.salmanitb.alumnisalman.helper.RegistrationCheckerCallback;
import com.salmanitb.alumnisalman.helper.RegistrationStepFragment;
import com.salmanitb.alumnisalman.model.ActivityView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;


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

    public RegistrationActivityFragment() {
        // Required empty public constructor
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

        return rootView;
    }

    @Override
    public void checkInput(RegistrationCheckerCallback callback) {
        // TODO: check input, then insert to user object
        callback.onFinishChecking(true);
    }
}
