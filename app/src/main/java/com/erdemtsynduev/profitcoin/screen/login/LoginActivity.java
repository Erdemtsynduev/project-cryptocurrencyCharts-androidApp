package com.erdemtsynduev.profitcoin.screen.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.erdemtsynduev.profitcoin.R;
import com.erdemtsynduev.profitcoin.screen.resetpassword.ResetPasswordActivity;
import com.erdemtsynduev.profitcoin.screen.signup.SignupActivity;
import com.google.firebase.auth.FirebaseAuth;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends MvpAppCompatActivity implements LoginView {

    @InjectPresenter
    LoginPresenter mLoginPresenter;

    @BindView(R.id.email)
    EditText inputEmail;

    @BindView(R.id.password)
    EditText inputPassword;

    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    @BindView(R.id.btn_signup)
    Button btnSignup;

    @BindView(R.id.btn_login)
    Button btnLogin;

    @BindView(R.id.btn_reset_password)
    Button btnReset;

    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Get Firebase auth instance
        auth = FirebaseAuth.getInstance();

        if (auth.getCurrentUser() != null) {
            finish();
        }

        // set the view now
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Get Firebase auth instance
        auth = FirebaseAuth.getInstance();

        btnSignup.setOnClickListener(v -> {
            mLoginPresenter.openScreenSignUp();
        });

        btnReset.setOnClickListener(v -> {
            openScreenResetPassword();
        });

        btnLogin.setOnClickListener(v -> {
            String email = inputEmail.getText().toString();
            final String password = inputPassword.getText().toString();

            if (TextUtils.isEmpty(email)) {
                mLoginPresenter.showErrorEnterEmail();
                return;
            }

            if (TextUtils.isEmpty(password)) {
                mLoginPresenter.showErrorPassword();
                return;
            }

            progressBar.setVisibility(View.VISIBLE);

            //authenticate user
            auth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(LoginActivity.this, task -> {
                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        progressBar.setVisibility(View.GONE);
                        if (!task.isSuccessful()) {
                            // there was an error
                            if (password.length() < 6) {
                                inputPassword.setError(getString(R.string.minimum_password));
                            } else {
                                Toast.makeText(LoginActivity.this, getString(R.string.auth_failed), Toast.LENGTH_LONG).show();
                            }
                        } else {
                            finish();
                        }
                    });
        });
    }

    @Override
    public void openScreenSignUp() {
        startActivity(new Intent(LoginActivity.this, SignupActivity.class));
        finish();
    }

    @Override
    public void openScreenResetPassword() {
        startActivity(new Intent(LoginActivity.this, ResetPasswordActivity.class));
        finish();
    }

    @Override
    public void showErrorEnterEmail() {
        Toast.makeText(getApplicationContext(), getString(R.string.activity_Login_error_email), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showErrorPassword() {
        Toast.makeText(getApplicationContext(), getString(R.string.activity_Login_error_password), Toast.LENGTH_SHORT).show();
    }
}