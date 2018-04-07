package com.salmanitb.alumnisalman.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.salmanitb.alumnisalman.R;
import com.salmanitb.alumnisalman.helper.APIConnector;
import com.salmanitb.alumnisalman.model.About;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ContactFragment extends Fragment {

    @BindView(R.id.popupBackground)
    RelativeLayout popupBackground;

    @BindView(R.id.popupSendEmail)
    LinearLayout popupSendEmail;

    @BindView(R.id.app_description)
    TextView appDescription;

    @BindView(R.id.txt_address)
    TextView txtAddress;

    @BindView(R.id.txt_phone)
    TextView txtPhone;

    @BindView(R.id.txt_email)
    TextView txtEmail;

    @OnClick(R.id.btn_show_popup)
    public void showPopup() {
        popupBackground.setVisibility(View.VISIBLE);
        popupSendEmail.setVisibility(View.VISIBLE);
    }

    @OnClick(R.id.popupBackground)
    public void hidePopup() {
        hideSoftKeyboard();
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
        final View rootView = inflater.inflate(R.layout.fragment_contact, container, false);
        ButterKnife.bind(this, rootView);

        APIConnector.getInstance().getAbout(new APIConnector.ApiCallback<About>() {
            @Override
            public void onSuccess(About response) {
                appDescription.setText(response.getAbout());
                txtAddress.setText(response.getAddress());
                txtPhone.setText(response.getPhone());
                txtEmail.setText(response.getEmail());
            }

            @Override
            public void onFailure(Throwable t) {
                Toast.makeText(rootView.getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        return rootView;
    }

    private void hideSoftKeyboard() {
        View view = getActivity().getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            if (imm != null) {
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
        }
    }
}
