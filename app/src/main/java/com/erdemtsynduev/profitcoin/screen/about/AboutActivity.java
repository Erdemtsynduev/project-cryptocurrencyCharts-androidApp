package com.erdemtsynduev.profitcoin.screen.about;

import android.os.Bundle;
import android.widget.TextView;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.erdemtsynduev.profitcoin.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AboutActivity extends MvpAppCompatActivity implements AboutView {

    @InjectPresenter
    AboutPresenter mAboutPresenter;

    @BindView(R.id.toolbarAction1)
    TextView toolbarAction1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        ButterKnife.bind(this);

        toolbarAction1.setText(getText(R.string.back));
        toolbarAction1.setOnClickListener(v -> {
            onBackPressed();
        });
    }
}
