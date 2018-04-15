package com.salmanitb.alumnisalman.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.salmanitb.alumnisalman.R;
import com.salmanitb.alumnisalman.activity.EditProfileActivity;
import com.salmanitb.alumnisalman.activity.LoginActivity;
import com.salmanitb.alumnisalman.activity.MainActivity;
import com.salmanitb.alumnisalman.helper.PreferenceManager;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProfilFragment extends Fragment{

    boolean doubleLogoutToExitPressedOnce = false;

    public ProfilFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_profil, container, false);
        ButterKnife.bind(this, rootView);

//        Button edit_profile = (Button) rootView.findViewById(R.id.btn_logout);
//        edit_profile.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(v.getContext(), EditProfileActivity.class);
//                startActivity(intent);
//            }
//        });

        return rootView;
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
        startActivity(intent);
    }

    @OnClick (R.id.btn_edit_almamater)
    protected void editAlmamater() {
        Intent intent = new Intent(this.getContext(), EditProfileActivity.class);
        startActivity(intent);
    }

    @OnClick (R.id.btn_edit_pekerjaan)
    protected void editPekerjaan() {
        Intent intent = new Intent(this.getContext(), EditProfileActivity.class);
        startActivity(intent);
    }

    @OnClick (R.id.btn_edit_kegiatan)
    protected void editKegiatan() {
        Intent intent = new Intent(this.getContext(), EditProfileActivity.class);
        startActivity(intent);
    }


}
