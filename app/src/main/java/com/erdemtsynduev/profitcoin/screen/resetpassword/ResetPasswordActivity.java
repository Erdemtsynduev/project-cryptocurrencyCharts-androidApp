package com.erdemtsynduev.profitcoin.screen.resetpassword;

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
import com.google.firebase.auth.FirebaseAuth;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ResetPasswordActivity extends MvpAppCompatActivity implements ResetPasswordView {

    @InjectPresenter
    ResetPasswordPresenter mResetPasswordPresenter;

    @BindView(R.id.email)
    EditText inputEmail;
    @BindView(R.id.btn_reset_password)
    Button btnReset;
    @BindView(R.id.btn_back)
    Button btnBack;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);
        ButterKnife.bind(this);

        auth = FirebaseAuth.getInstance();

        btnBack.setOnClickListener(v -> finish());

        btnReset.setOnClickListener(v -> {

            String email = inputEmail.getText().toString().trim();

            if (TextUtils.isEmpty(email)) {
                mResetPasswordPresenter.showErrorReset();
                return;
            }

            progressBar.setVisibility(View.VISIBLE);
            auth.sendPasswordResetEmail(email)
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            mResetPasswordPresenter.showErrorSendReset();
                        } else {
                            mResetPasswordPresenter.showSuccessResetSend();
                        }
                        progressBar.setVisibility(View.GONE);
                    });
        });
    }

    @Override
    public void showErrorReset() {
        Toast.makeText(getApplication(), getString(R.string.activity_reset_password_error_reset), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showErrorSendReset() {
        Toast.makeText(ResetPasswordActivity.this, getString(R.string.activity_reset_password_error_reset_send), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showSuccessResetSend() {
        Toast.makeText(ResetPasswordActivity.this, getString(R.string.activity_reset_password_success), Toast.LENGTH_SHORT).show();
    }
}