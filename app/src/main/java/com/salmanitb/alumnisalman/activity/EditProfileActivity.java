package com.salmanitb.alumnisalman.activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.Toast;

import com.salmanitb.alumnisalman.R;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

public class EditProfileActivity extends AppCompatActivity {

    @BindView(R.id.user_name) protected EditText username;
    @BindView(R.id.user_email) protected EditText email;
    @BindView(R.id.user_sex) protected EditText sex;
    @BindView(R.id.user_job) protected EditText job;
    @BindView(R.id.user_company) protected EditText company;
    @BindView(R.id.user_address) protected EditText address;
    @BindView(R.id.user_city) protected EditText city;
    @BindView(R.id.user_country) protected EditText country;
    @BindView(R.id.user_phonenumber) protected EditText phonenumber;
    @BindView(R.id.user_university) protected EditText university;
    @BindView(R.id.user_year_university) protected EditText year_university;
    @BindView(R.id.user_major) protected EditText major;
    @BindView(R.id.user_lmd) protected EditText lmd;
    @BindView(R.id.user_activities) protected EditText activities;
    @BindView(R.id.user_year_activities) protected EditText year_activities;

    @BindView(R.id.edit_profile_image) protected CircleImageView image;


    //a constant to track the file chooser intent
    private static final int PICK_IMAGE_REQUEST = 234;

    private Bitmap bitmap;
    private Uri filePath;

    @OnClick(R.id.save_button) protected void saveProfile() {
        saveUserAction();
    }

    @OnClick(R.id.edit_image_icon) protected void editImage() {
        editImageAction();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        ButterKnife.bind(this);
    }


    private void editImageAction() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent,"Select Picture"),PICK_IMAGE_REQUEST);

    }

    // handling the image chooser activity result
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode,resultCode,data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            filePath = data.getData();
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),filePath);
                image.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void saveUserAction() {

        final String txtUsername = username.getText().toString();
        final String txtEmail = email.getText().toString();
        final String txtSex = sex.getText().toString();
        final String txtJob = job.getText().toString();
        final String txtCompany = company.getText().toString();
        final String txtAddress = address.getText().toString();
        final String txtCity= city.getText().toString();
        final String txtCountry= country.getText().toString();
        final String txtPhonenumber= phonenumber.getText().toString();
        final String txtUniversity = university.getText().toString();
        final String txtYearUniversity = year_university.getText().toString();
        final String txtMajor= major.getText().toString();
        final String txtLmd= lmd.getText().toString();
        final String txtActivities= activities.getText().toString();
        final String txtYearActivities= year_activities.getText().toString();

        if (TextUtils.isEmpty(txtUsername)) {
            showShortToast("Masukkan nama lengkap!");
            return;
        }
        if (TextUtils.isEmpty(txtEmail)) {
            showShortToast("Masukkan email!");
            return;
        }
        if (TextUtils.isEmpty(txtSex)) {
            showShortToast("Masukkan jenis kelamin!");
            return;
        }
        if (TextUtils.isEmpty(txtJob)) {
            showShortToast("Masukkan pekerjaan!");
            return;
        }
        if (TextUtils.isEmpty(txtCompany)) {
            showShortToast("Masukkan tempat kerja!");
            return;
        }
        if (TextUtils.isEmpty(txtAddress)) {
            showShortToast("Masukkan alamat!");
            return;
        }
        if (TextUtils.isEmpty(txtCity)) {
            showShortToast("Masukkan kota!");
            return;
        }
        if (TextUtils.isEmpty(txtCountry)) {
            showShortToast("Masukkan negara!");
            return;
        }
        if (TextUtils.isEmpty(txtPhonenumber)) {
            showShortToast("Masukkan nomer HP!");
            return;
        }
        if (TextUtils.isEmpty(txtUniversity)) {
            showShortToast("Masukkan universitas!");
            return;
        }
        if (TextUtils.isEmpty(txtYearUniversity)) {
            showShortToast("Masukkan tahun anda di universitas!");
            return;
        }
        if (TextUtils.isEmpty(txtMajor)) {
            showShortToast("Masukkan jurusan!");
            return;
        }
        if (TextUtils.isEmpty(txtLmd)) {
            showShortToast("Masukkan angkatan LMD!");
            return;
        }
        if (TextUtils.isEmpty(txtActivities)) {
            showShortToast("Masukkan aktivitas di Salman!");
            return;
        }
        if (TextUtils.isEmpty(txtYearActivities)) {
            showShortToast("Masukkan tahun beraktivitas!");
            return;
        }

    }

    private void showShortToast(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }



}
