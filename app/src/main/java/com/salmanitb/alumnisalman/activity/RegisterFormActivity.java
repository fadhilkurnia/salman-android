package com.salmanitb.alumnisalman.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;

import com.salmanitb.alumnisalman.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegisterFormActivity extends AppCompatActivity {

    @BindView(R.id.parent_layout)
    LinearLayout parentLayout;
    @BindView(R.id.layout_activity_left)
    LinearLayout leftActivityLayout;
    @BindView(R.id.layout_activity_right)
    LinearLayout rightActivityLayout;

    @OnClick(R.id.btn_next)
    void doRegister() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_form);
        ButterKnife.bind(this);

        ArrayList<String> activityData = new ArrayList<>();
        activityData.add("Aksara");
        activityData.add("Biologi Terapan (Bioster)");
        activityData.add("Keluarga Mahasiswa Pasca Sarjana ITB (Kamil)");
        activityData.add("Korps Relawan Salman (Korsa)");
        activityData.add("Pembinaan Anak-anak Salman (PAS)");
        activityData.add("Reklame Kreasi Masa (Reklamasa)");
        activityData.add("Rumah Visi");
        activityData.add("Unit Pengembangan Tilawati Qur'an Al-Muhandis (UPTQ)");
        activityData.add("Lembaga Kemahasiswaan (BASIS)");
        prepareCheckBoxes(activityData, parentLayout, leftActivityLayout, rightActivityLayout);
    }

    private void prepareCheckBoxes(ArrayList<String> data, LinearLayout parent, LinearLayout leftColumn, LinearLayout rightLayout) {
        for(int i = 0; i < data.size(); i++) {
            CheckBox checkBox = new CheckBox(this);
            checkBox.setText(data.get(i));
            checkBox.setId(i);
            if (i%2 == 0)
                leftColumn.addView(checkBox);
            else
                rightLayout.addView(checkBox);
        }
        CheckBox checkBox = new CheckBox(this);
        checkBox.setText("Lainnya");
        checkBox.setId(data.size());
        parent.addView(checkBox);
    }

    @BindView(R.id.input_name)
    EditText inputName;
    @BindView(R.id.radio_male)
    RadioButton radioMale;
    @BindView(R.id.radio_female)
    RadioButton radioFemale;
    @BindView(R.id.input_country)
    EditText inputCountry;
    @BindView(R.id.input_city)
    EditText inputCity;
    @BindView(R.id.input_address)
    EditText inputAddress;
    @BindView(R.id.input_campus)
    EditText inputCampus;
    @BindView(R.id.input_year)
    EditText inputYear;
    @BindView(R.id.input_lmd)
    EditText inputLMD;

    private boolean checkForm() {

        return true;
    }
}
