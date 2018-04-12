package com.salmanitb.alumnisalman.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.salmanitb.alumnisalman.R;
import com.salmanitb.alumnisalman.activity.RegisterActivity;
import com.salmanitb.alumnisalman.activity.RegistrationActivity;
import com.salmanitb.alumnisalman.helper.RegistrationStepFragment;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * A simple {@link Fragment} subclass.
 */
public class RegistrationPersonalFragment extends RegistrationStepFragment {


    @BindView(R.id.input_name)
    EditText inputName;
    @BindView(R.id.input_email)
    EditText inputEmail;
    @BindView(R.id.radio_male)
    RadioButton radiomMale;
    @BindView(R.id.input_phone)
    EditText inputPhone;
    @BindView(R.id.input_country)
    EditText inputCountry;
    @BindView(R.id.input_city)
    EditText inputCity;
    @BindView(R.id.input_address)
    EditText inputAddress;

    public RegistrationPersonalFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_registration_personal, container, false);
        ButterKnife.bind(this, rootView);
        inputEmail.setText(RegistrationActivity.applicationUser.getEmail());

        return rootView;
    }

    @Override
    public boolean checkInput() {
        String name = inputName.getText().toString().trim();
        String phone = inputPhone.getText().toString().trim();
        String country = inputCountry.getText().toString().trim();
        String city = inputCity.getText().toString().trim();
        String address = inputAddress.getText().toString().trim();

        if (name.equals("") || phone.equals("") || country.equals("") || city.equals("") || address.equals("")) {
            showToast("Data belum terisi semuanya");
            return false;
        }

        RegistrationActivity.applicationUser.setName(name);
        RegistrationActivity.applicationUser.setPhonenumber(phone);
        RegistrationActivity.applicationUser.setCountry(country);
        RegistrationActivity.applicationUser.setCity(city);
        RegistrationActivity.applicationUser.setAddress(address);
        RegistrationActivity.applicationUser.setSex(radiomMale.isChecked()? "Pria" : "Wanita");

        return true;
    }

    private void showToast(String text) {
        Toast.makeText(getActivity(), text, Toast.LENGTH_SHORT).show();
    }
}
