package com.salmanitb.alumnisalman.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.salmanitb.alumnisalman.R;
import com.salmanitb.alumnisalman.SalmanApplication;
import com.salmanitb.alumnisalman.activity.RegistrationActivity;
import com.salmanitb.alumnisalman.helper.APIConnector;
import com.salmanitb.alumnisalman.helper.RegistrationCheckerCallback;
import com.salmanitb.alumnisalman.helper.RegistrationStepFragment;
import com.salmanitb.alumnisalman.model.GeocodingAddressComponent;
import com.salmanitb.alumnisalman.model.GeocodingResponse;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.salmanitb.alumnisalman.SalmanApplication.currentUser;
import static com.salmanitb.alumnisalman.activity.EditProfileActivity.EDIT_ARGUMENT;
import static com.salmanitb.alumnisalman.model.User.MALE;


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

    @BindView(R.id.radio_female)
    RadioButton radioFemale;

    private boolean isEdit;

    public RegistrationPersonalFragment() {
        // Required empty public constructor
        isEdit = false;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_registration_personal, container, false);
        ButterKnife.bind(this, rootView);
        inputEmail.setText(SalmanApplication.getCurrentUser().getEmail());

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
        inputName.setText(currentUser.getName());
        inputEmail.setText(currentUser.getEmail());
        if (currentUser.getSex().equals(MALE))
            radiomMale.setChecked(true);
        else
            radioFemale.setChecked(true);
        inputPhone.setText(currentUser.getPhonenumber());
        inputCountry.setText(currentUser.getCountry());
        inputCity.setText(currentUser.getCity());
        inputAddress.setText(currentUser.getAddress());
    }

    @Override
    public void checkInput(final RegistrationCheckerCallback callback) {
        final StringBuilder stringBuilder = new StringBuilder();
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
            callback.onFinishChecking(false);
            return;
        }

        final boolean[] isSucces = {false};
        APIConnector.getInstance().checkAddress(city + " " + country, new APIConnector.ApiCallback<GeocodingResponse>() {
            @Override
            public void onSuccess(GeocodingResponse response) {
                formatUserAddress(response);
                inputCountry.setText(SalmanApplication.getCurrentUser().getCountry());
                inputCity.setText(SalmanApplication.getCurrentUser().getCity());

                SalmanApplication.getCurrentUser().setName(name);
                SalmanApplication.getCurrentUser().setPhonenumber(phone);
                SalmanApplication.getCurrentUser().setCountry(country);
                SalmanApplication.getCurrentUser().setCity(city);
                SalmanApplication.getCurrentUser().setAddress(address);
                SalmanApplication.getCurrentUser().setSex(radiomMale.isChecked()? "Pria" : "Wanita");
                callback.onFinishChecking(true);
            }

            @Override
            public void onFailure(Throwable t) {
                stringBuilder.append("  - Periksa negara atau kota yang diisi");
                txtError.setText(stringBuilder.toString());
                txtError.setVisibility(View.VISIBLE);
                showToast("Negara atau kota yang dimasukan salah!");
                callback.onFinishChecking(false);
            }
        });
    }

    private void showToast(String text) {
        Toast.makeText(getActivity(), text, Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.txt_error)
    protected void hideError() {
        txtError.setVisibility(View.GONE);
    }

    private void formatUserAddress(GeocodingResponse geocodingResponse) {
        for (GeocodingAddressComponent address: geocodingResponse.getResults()[0].getAddressComponent()) {
            if (isMember(address.getTypes(), "country"))
                SalmanApplication.getCurrentUser().setCountry(address.getLongName());
            if (isMember(address.getTypes(), "administrative_area_level_2")) {
                String cityName = address.getShortName();
                String cityNameWord[] = cityName.split(" ", 2);
                if (cityNameWord.length > 1)
                    cityName = cityNameWord[1];

                SalmanApplication.getCurrentUser().setCity(cityName);
            }
        }
        SalmanApplication.getCurrentUser()
                .setLatitude(
                        geocodingResponse.getResults()[0]
                                .getGeometry()
                                .getLocation()
                                .getLatitude());
        SalmanApplication.getCurrentUser()
                .setLongitude(
                        geocodingResponse.getResults()[0]
                                .getGeometry()
                                .getLocation()
                                .getLongitude());
    }

    private boolean isMember(String data[], String element) {
        for (String aData : data) if (aData.equals(element)) return true;
        return false;
    }

}
