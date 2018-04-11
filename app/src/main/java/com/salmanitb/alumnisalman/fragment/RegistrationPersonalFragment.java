package com.salmanitb.alumnisalman.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.salmanitb.alumnisalman.R;
import com.salmanitb.alumnisalman.helper.RegistrationStepFragment;


/**
 * A simple {@link Fragment} subclass.
 */
public class RegistrationPersonalFragment extends RegistrationStepFragment {


    public RegistrationPersonalFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_registration_personal, container, false);
    }

    @Override
    public boolean checkInput() {
        return false;
    }
}
