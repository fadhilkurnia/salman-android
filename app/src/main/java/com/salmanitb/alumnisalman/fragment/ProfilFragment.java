package com.salmanitb.alumnisalman.fragment;


import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
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
import com.salmanitb.alumnisalman.model.User;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
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

    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout refreshLayout;

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
        prepareRefreshLayout();
        loadData();

        return rootView;
    }

    private void prepareRefreshLayout() {
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                APIConnector.getInstance().getProfil(SalmanApplication.getCurrentUserAuth().getId(), new APIConnector.ApiCallback<User>() {
                    @Override
                    public void onSuccess(User response) {
                        refreshLayout.setRefreshing(false);
                        SalmanApplication.setCurrentUser(response);
                        loadData();
                    }

                    @Override
                    public void onFailure(Throwable t) {
                        Toast.makeText(getActivity(), "Gagal memperbaharui data profil", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }


    @OnClick (R.id.edit_image_button)
    protected void editImage() {

        if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            // Permission is not granted
//            Toast.makeText(getActivity(), "Permission not granted!", Toast.LENGTH_SHORT).show();
//            return;
            // Permission is not granted
            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(),
                    Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
            } else {
                // No explanation needed; request the permission
                ActivityCompat.requestPermissions(getActivity(),
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        PICK_IMAGE_REQUEST);

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        }
        if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        loadImageChooser();

    }

    private void loadImageChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent,"Pilih Foto"),PICK_IMAGE_REQUEST);
    }

    // handling the image chooser activity result
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode,resultCode,data);
        changeImage(requestCode, resultCode, data);
    }

    private void changeImage(int requestCode, int resultCode, Intent data) {

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            try {
                android.net.Uri selectedImage = data.getData();

                filePath = RealPathUtil.getRealPath(this.getContext(), selectedImage);

                bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), data.getData());
                ByteArrayOutputStream out = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 60, out);
                Bitmap image = BitmapFactory.decodeStream(new ByteArrayInputStream(out.toByteArray()));
                imgProfile.setImageBitmap(image);

                uploadImage();
            } catch (IOException e) {
                e.printStackTrace();
                Toast.makeText(getActivity(), "Terjadi kesalahan", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case PICK_IMAGE_REQUEST: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.
                    Toast.makeText(getActivity(), "Diizinkan untuk mengganti foto!", Toast.LENGTH_SHORT).show();
                    loadImageChooser();
                } else {
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                    Toast.makeText(getActivity(), "Tidak diizinkan untuk mengakses media!", Toast.LENGTH_SHORT).show();
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request.
        }
    }

    private void uploadImage() {
        File file = new File(filePath);
        try {
            Bitmap bitmap = BitmapFactory.decodeFile (file.getPath ());
            bitmap.compress (Bitmap.CompressFormat.JPEG, 50, new FileOutputStream(file));
        }
        catch (Throwable t) {
            Log.e("ERROR", "Error compressing file." + t.toString ());
            t.printStackTrace ();
        }
        RequestBody reqFile = RequestBody.create(MediaType.parse("image/*"), file);
        MultipartBody.Part body = MultipartBody.Part.createFormData("foto", file.getName(), reqFile);

        APIConnector.getInstance().uploadImage(body, new APIConnector.ApiCallback<MessageResponse>() {
            @Override
            public void onSuccess(MessageResponse response) {
                Toast.makeText(getActivity(), "Sukses!", Toast.LENGTH_SHORT).show();
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

        linearLayout.removeAllViews();
        for (SalmanActivity s : currentUser.getActivities()) {
            String year_start =  s.getStartYear();
            String year_end = s.getEndYear();
            String activity = s.getTitle();
            TextView textView = new TextView(getContext());
            String detail_activity = activity;
            if (!(year_start.equals("") && year_end.equals(""))) {
                if (!year_start.equals(""))
                    detail_activity = detail_activity + ", " + year_start;
                if (!year_end.equals(""))
                    if (!year_start.equals(""))
                        detail_activity = detail_activity + "-";
                    detail_activity = detail_activity + year_end;
            }
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
