package com.salmanitb.alumnisalman.fragment;


import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.salmanitb.alumnisalman.R;
import com.salmanitb.alumnisalman.SalmanApplication;
import com.salmanitb.alumnisalman.activity.EditProfileActivity;
import com.salmanitb.alumnisalman.activity.LoginActivity;
import com.salmanitb.alumnisalman.helper.APIConnector;
import com.salmanitb.alumnisalman.helper.PreferenceManager;
import com.salmanitb.alumnisalman.helper.RealPathUtil;
import com.salmanitb.alumnisalman.model.MessageResponse;
import com.salmanitb.alumnisalman.model.SalmanActivity;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.ResponseBody;

import static android.app.Activity.RESULT_OK;
import static com.salmanitb.alumnisalman.SalmanApplication.currentUser;
import static com.salmanitb.alumnisalman.helper.WebService.BASE_IMAGE_URL;
import static com.salmanitb.alumnisalman.model.User.MALE;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProfilFragment extends Fragment{

    boolean doubleLogoutToExitPressedOnce = false;

    @BindView(R.id.txt_name)
    TextView txtName;

    @BindView(R.id.activity_list)
    LinearLayout linearLayout;

    @BindView(R.id.user_email)
    TextView txtEmail;

    @BindView(R.id.man_icon)
    ImageView imgMan;

    @BindView(R.id.woman_icon)
    ImageView imgWoman;

    @BindView(R.id.profile_image)
    CircleImageView imgProfile;

    @BindView(R.id.user_address)
    TextView txtAddres;

    @BindView(R.id.user_phonenumber)
    TextView txtPhonenumber;

    @BindView(R.id.user_major)
    TextView txtMajor;

    @BindView(R.id.user_job)
    TextView txtJob;

    @BindView(R.id.user_company)
    TextView txtCompany;

    @BindView(R.id.angkatan_lmd)
    TextView txtLmd;

    private Bitmap bitmap;
    private String filePath;


    //a constant to track the file chooser intent
    private static final int PICK_IMAGE_REQUEST = 100;

    public ProfilFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_profil, container, false);
        ButterKnife.bind(this, rootView);

        loadData();

        return rootView;
    }


    @OnClick (R.id.edit_image_button)
    protected void editImage() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent,"Select Picture"),PICK_IMAGE_REQUEST);
    }

    // handling the image chooser activity result
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode,resultCode,data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
//            filePath = data.getData();
            try {
                android.net.Uri selectedImage = data.getData();
                Log.d("PATH URI", selectedImage.toString());

                filePath = RealPathUtil.getRealPath(this.getContext(), selectedImage);
                Log.d("PATH STR", filePath);
//                File file = new File(filePath);

                bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), data.getData());
                imgProfile.setImageBitmap(bitmap);
                uploadImage();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void uploadImage() {
        // use the FileUtils to get the actual file by uri
        File file = new File(filePath);

//                getFile(this, filePath);

        APIConnector.getInstance().uploadFile(getContext(), SalmanApplication.getCurrentUser(), file,  new APIConnector.ApiCallback<ResponseBody>() {
            @Override
            public void onSuccess(ResponseBody response) {

            }

            @Override
            public void onFailure(Throwable t) {
                Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void loadData() {

        Gson gson = new Gson();
        Log.d("DEBUG_SALMAN", gson.toJson(currentUser));
        txtName.setText(currentUser.getName());
        txtEmail.setText(currentUser.getEmail());
        txtAddres.setText(currentUser.getAddress());
        txtPhonenumber.setText(currentUser.getPhonenumber());
        String almamater = currentUser.getMajor() + " " + currentUser.getUniversity() + " " + currentUser.getYearUniversity();
        txtMajor.setText(almamater);
        txtJob.setText(currentUser.getJob());
        txtCompany.setText(currentUser.getCompany());

        if (currentUser.getImageURL() != null) {
            if (!currentUser.getImageURL().equals("")) {
                Picasso.get().load(BASE_IMAGE_URL + currentUser.getImageURL()).into(imgProfile);
            }  else {
                Picasso.get().load(R.drawable.default_user).into(imgProfile);
            }
        } else {
            Picasso.get().load(R.drawable.default_user).into(imgProfile);
        }

        if (currentUser.getSex().equals(MALE)) {
            imgWoman.setVisibility(View.GONE);
        } else {
            imgMan.setVisibility(View.GONE);
        }

        for (SalmanActivity s : currentUser.getActivities()) {
            String year_start =  s.getStartYear();
            String year_end = s.getEndYear();
            String activity = s.getTitle();
            TextView textView = new TextView(getContext());
            String detail_activity = activity + ", " + year_start + "-" + year_end;
            textView.setText(detail_activity);
            linearLayout.addView(textView);
        }

        txtLmd.setText(currentUser.getLmd());

    }

    @OnClick(R.id.btn_logout)
    protected void doLogout() {

        if (doubleLogoutToExitPressedOnce) {
            APIConnector.getInstance().doLogout(new APIConnector.ApiCallback<MessageResponse>() {
                @Override
                public void onSuccess(MessageResponse response) {
                    PreferenceManager.getInstance().setUserAuth(null);
                    Intent i = new Intent(getActivity(), LoginActivity.class);
                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(i);
                }

                @Override
                public void onFailure(Throwable t) {
                    Toast.makeText(getActivity(), "Gagal logout!", Toast.LENGTH_SHORT).show();
                }
            });
            return;
        }

        this.doubleLogoutToExitPressedOnce = true;
        Toast.makeText(this.getContext(), "Tekan sekali lagi untuk logout", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                doubleLogoutToExitPressedOnce=false;
            }
        }, 2000);
    }

    @OnClick (R.id.btn_edit_personal)
    protected void editPersonal() {
        Intent intent = new Intent(this.getContext(), EditProfileActivity.class);
        intent.putExtra("FRAGMENT_ID","PERSONAL");
        startActivity(intent);
    }

    @OnClick (R.id.btn_edit_almamater)
    protected void editAlmamater() {
        Intent intent = new Intent(this.getContext(), EditProfileActivity.class);
        intent.putExtra("FRAGMENT_ID","ALMAMATER");
        startActivity(intent);
    }

    @OnClick (R.id.btn_edit_pekerjaan)
    protected void editPekerjaan() {
        Intent intent = new Intent(this.getContext(), EditProfileActivity.class);
        intent.putExtra("FRAGMENT_ID","PEKERJAAN");
        startActivity(intent);
    }

    @OnClick (R.id.btn_edit_kegiatan)
    protected void editKegiatan() {
        Intent intent = new Intent(this.getContext(), EditProfileActivity.class);
        intent.putExtra("FRAGMENT_ID","KEGIATAN");
        startActivity(intent);
    }


}
