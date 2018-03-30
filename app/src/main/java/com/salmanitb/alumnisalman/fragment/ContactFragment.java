package com.salmanitb.alumnisalman.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.salmanitb.alumnisalman.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ContactFragment extends Fragment {

    @BindView(R.id.popupBackground)
    RelativeLayout popupBackground;

    @BindView(R.id.popupSendEmail)
    LinearLayout popupSendEmail;

    @OnClick(R.id.btn_show_popup)
    public void showPopup() {
        popupBackground.setVisibility(View.VISIBLE);
        popupSendEmail.setVisibility(View.VISIBLE);
    }

    @OnClick(R.id.popupBackground)
    public void hidePopup() {
        popupSendEmail.setVisibility(View.GONE);
        popupBackground.setVisibility(View.GONE);
    }

    @OnClick(R.id.popupSendEmail)
    public void doNothing() {

    }

    public ContactFragment() {
        // empty constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_contact, container, false);
        ButterKnife.bind(this, rootView);
        return rootView;
    }
}
