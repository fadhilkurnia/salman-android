package com.salmanitb.alumnisalman.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.salmanitb.alumnisalman.R;
import com.salmanitb.alumnisalman.SalmanApplication;
import com.salmanitb.alumnisalman.helper.APIConnector;
import com.salmanitb.alumnisalman.helper.PreferenceManager;
import com.salmanitb.alumnisalman.model.User;
import com.salmanitb.alumnisalman.model.UserAuth;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.input_email)
    EditText inputEmail;
    @BindView(R.id.input_password)
    EditText inputPassword;
    @BindView(R.id.btn_login)
    Button btnLogin;
    @BindView(R.id.txt_register)
    TextView txtRegister;

    @OnClick(R.id.txt_register)
    public void gotoRegister() {
        Intent i = new Intent(this, RegisterActivity.class);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(i);
    }

    @OnClick(R.id.btn_login)
    public void doLogin() {
        final String email = inputEmail.getText().toString();
        final String password = inputPassword.getText().toString();

        if (!checkInput(email, password))
            return;

        UserAuth userAuth = new UserAuth();
        userAuth.setEmail(email);
        userAuth.setPassword(password);

        final Context context = this;
        disableInput();
        APIConnector.getInstance().doLogin(userAuth.getEmail(), userAuth.getPassword(), new APIConnector.ApiCallback<UserAuth>() {
            @Override
            public void onSuccess(UserAuth response) {
                response.setPassword(password);
                PreferenceManager.getInstance().setUserAuth(response);
                if (response.isVerified())
                    gotoMain();
                else
                    gotoConfirmation();
            }

            @Override
            public void onFailure(Throwable t) {
                Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
                enableInput();
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

    }

    private boolean checkInput(String email, String password) {
        if (email.trim().equals("") || password.equals("")) {
            Toast.makeText(this, "Pastikan email dan password sudah terisi!", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (!validateEmail(email)) {
            Toast.makeText(this, "Pastikan alamat email anda benar!", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

    public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    public static boolean validateEmail(String emailStr) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX .matcher(emailStr);
        return matcher.find();
    }

    private void gotoConfirmation() {
        Intent i = new Intent(this, ConfirmActivity.class);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(i);
    }

    private void gotoMain() {
        UserAuth userAuth = PreferenceManager.getInstance().getUserAuth();
        if (userAuth == null) {
            Toast.makeText(this, "Terjadi kesalahan sistem", Toast.LENGTH_SHORT).show();
            return;
        }


        // Get all default_user data and save to local
        APIConnector.getInstance().getProfil(userAuth.getId(), new APIConnector.ApiCallback<User>() {
            @Override
            public void onSuccess(User response) {
                SalmanApplication.setCurrentUser(response);
                Intent i = new Intent(LoginActivity.this, MainActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(i);
            }

            @Override
            public void onFailure(Throwable t) {
                Toast.makeText(LoginActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void disableInput() {
        inputEmail.setEnabled(false);
        inputPassword.setEnabled(false);
        btnLogin.setEnabled(false);
        btnLogin.setBackgroundColor(getResources().getColor(R.color.colorAccent));
        txtRegister.setEnabled(false);
    }

    private void enableInput() {
        inputEmail.setEnabled(true);
        inputPassword.setEnabled(true);
        btnLogin.setEnabled(true);
        btnLogin.setBackgroundColor(getResources().getColor(R.color.colorAccent));
        txtRegister.setEnabled(true);
    }

}
