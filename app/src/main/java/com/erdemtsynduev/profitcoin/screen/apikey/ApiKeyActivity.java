package com.erdemtsynduev.profitcoin.screen.apikey;

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
import com.erdemtsynduev.profitcoin.ExtendApplication;
import com.erdemtsynduev.profitcoin.R;
import com.erdemtsynduev.profitcoin.screen.home.HomeActivity;
import com.erdemtsynduev.profitcoin.utils.ApiKeyUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ApiKeyActivity extends MvpAppCompatActivity implements ApiKeyView {

    @InjectPresenter
    ApiKeyPresenter mApiKeyPresenter;

    @BindView(R.id.activity_api_key_edit_text_api)
    EditText inputApiKey;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    @BindView(R.id.activity_api_key_btn_save_api)
    Button btnSaveApiKey;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_api_key);
        ButterKnife.bind(this);

        btnSaveApiKey.setOnClickListener(v -> {
            String apiKey = inputApiKey.getText().toString();

            if (TextUtils.isEmpty(apiKey)) {
                mApiKeyPresenter.showErrorEnterApiLey();
                return;
            }

            progressBar.setVisibility(View.VISIBLE);

            ExtendApplication.setApiKey(apiKey);
            ApiKeyUtils.setApiKey(apiKey);

            progressBar.setVisibility(View.GONE);

            mApiKeyPresenter.openScreenHome();
        });
    }

    @Override
    public void showErrorApiKey() {
        Toast.makeText(getApplicationContext(), getString(R.string.activity_api_key_error_text_input_api), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void openScreenHome() {
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
        finish();
    }
}