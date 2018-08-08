package com.erdemtsynduev.profitcoin.screen.signup;

import android.content.Intent;
import android.os.Bundle;
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
import com.google.firebase.auth.FirebaseAuth;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SignupActivity extends MvpAppCompatActivity implements SignupView {

    @InjectPresenter
    SignupPresenter mSignupPresenter;

    @BindView(R.id.email)
    EditText inputEmail;

    @BindView(R.id.password)
    EditText inputPassword;

    @BindView(R.id.sign_in_button)
    Button btnSignIn;

    @BindView(R.id.sign_up_button)
    Button btnSignUp;

    @BindView(R.id.btn_reset_password)
    Button btnResetPassword;

    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        ButterKnife.bind(this);

        //Get Firebase auth instance
        auth = FirebaseAuth.getInstance();

        btnResetPassword.setOnClickListener(v -> mSignupPresenter.openScreenResetPassword());

        btnSignIn.setOnClickListener(v -> finish());

        btnSignUp.setOnClickListener(v -> {

            String email = inputEmail.getText().toString().trim();
            String password = inputPassword.getText().toString().trim();

            if (TextUtils.isEmpty(email)) {
                mSignupPresenter.showErrorEnterEmail();
                return;
            }

            if (TextUtils.isEmpty(password)) {
                mSignupPresenter.showErrorPassword();
                return;
            }

            if (password.length() < 6) {
                mSignupPresenter.showErrorPasswordSmall();
                return;
            }

            progressBar.setVisibility(View.VISIBLE);
            //create user
            auth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(SignupActivity.this, task -> {
                        Toast.makeText(SignupActivity.this, "createUserWithEmail:onComplete:" + task.isSuccessful(), Toast.LENGTH_SHORT).show();
                        progressBar.setVisibility(View.GONE);
                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        if (!task.isSuccessful()) {
                            Toast.makeText(SignupActivity.this, "Authentication failed." + task.getException(),
                                    Toast.LENGTH_SHORT).show();
                        } else {
                            finish();
                        }
                    });

        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void openScreenResetPassword() {
        startActivity(new Intent(SignupActivity.this, ResetPasswordActivity.class));
    }

    @Override
    public void showErrorEnterEmail() {
        Toast.makeText(getApplicationContext(), "Enter email address!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showErrorPassword() {
        Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showErrorPasswordSmall() {
        Toast.makeText(getApplicationContext(), "Password too short, enter minimum 6 characters!", Toast.LENGTH_SHORT).show();
    }
}