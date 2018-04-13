package com.salmanitb.alumnisalman.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.salmanitb.alumnisalman.R;
import com.salmanitb.alumnisalman.activity.RegisterActivity;
import com.salmanitb.alumnisalman.activity.RegistrationActivity;
import com.salmanitb.alumnisalman.helper.APIConnector;
import com.salmanitb.alumnisalman.helper.RegistrationStepFragment;
import com.salmanitb.alumnisalman.model.GeocodingResponse;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * A simple {@link Fragment} subclass.
 */
public class RegistrationPersonalFragment extends RegistrationStepFragment {


    @BindView(R.id.txt_error)
    TextView txtError;
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
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Harap perhatikan data yang anda input, terjadi kesalahan:\n");

        final String name = inputName.getText().toString().trim();
        final String phone = inputPhone.getText().toString().trim();
        final String country = inputCountry.getText().toString().trim();
        final String city = inputCity.getText().toString().trim();
        final String address = inputAddress.getText().toString().trim();

        if (name.equals("") || phone.equals("") || country.equals("") || city.equals("") || address.equals("")) {
            if (name.equals(""))
                stringBuilder.append("  - Kolom nama masih kosong\n");
            if (phone.equals(""))
                stringBuilder.append("  - Kolom nomor telepon masih kosong\n");
            if (country.equals(""))
                stringBuilder.append("  - Kolom negara masih kososng\n");
            if (address.equals(""))
                stringBuilder.append("  - Kolom alamat masih kosong\n");
            txtError.setText(stringBuilder.toString());
            txtError.setVisibility(View.VISIBLE);
            showToast("Data belum terisi semuanya!");
            return false;
        }

        final boolean[] isSucces = {false};
        APIConnector.getInstance().checkAddress(city, new APIConnector.ApiCallback<GeocodingResponse>() {
            @Override
            public void onSuccess(GeocodingResponse response) {

                RegistrationActivity.applicationUser.setName(name);
                RegistrationActivity.applicationUser.setPhonenumber(phone);
                RegistrationActivity.applicationUser.setCountry(country);
                RegistrationActivity.applicationUser.setCity(city);
                RegistrationActivity.applicationUser.setAddress(address);
                RegistrationActivity.applicationUser.setSex(radiomMale.isChecked()? "Pria" : "Wanita");
                isSucces[0] = true;
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });



        return isSucces[0];
    }

    private void showToast(String text) {
        Toast.makeText(getActivity(), text, Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.txt_error)
    protected void hideError() {
        txtError.setVisibility(View.GONE);
    }

}
