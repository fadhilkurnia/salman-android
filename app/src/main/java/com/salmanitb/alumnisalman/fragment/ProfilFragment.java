package com.salmanitb.alumnisalman.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.NotificationCompat;
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
import com.salmanitb.alumnisalman.activity.EditProfileActivity;
import com.salmanitb.alumnisalman.activity.LoginActivity;
import com.salmanitb.alumnisalman.helper.PreferenceManager;
import com.salmanitb.alumnisalman.model.SalmanActivity;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import butterknife.BindDimen;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

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
                Picasso.get().load(R.drawable.user).into(imgProfile);
            }
        } else {
            Picasso.get().load(R.drawable.user).into(imgProfile);
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
            PreferenceManager.getInstance().setUserAuth(null);
            Intent i = new Intent(getActivity(), LoginActivity.class);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(i);
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
