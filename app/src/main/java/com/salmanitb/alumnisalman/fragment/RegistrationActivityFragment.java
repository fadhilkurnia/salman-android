package com.salmanitb.alumnisalman.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.salmanitb.alumnisalman.R;
import com.salmanitb.alumnisalman.helper.RegistrationCheckerCallback;
import com.salmanitb.alumnisalman.helper.RegistrationStepFragment;

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

    public RegistrationActivityFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_registration_activity, container, false);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void checkInput(RegistrationCheckerCallback callback) {
        callback.onFinishChecking(true);
    }
}
