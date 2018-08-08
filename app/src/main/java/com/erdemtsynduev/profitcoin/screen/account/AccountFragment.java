package com.erdemtsynduev.profitcoin.screen.account;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.erdemtsynduev.profitcoin.R;
import com.erdemtsynduev.profitcoin.screen.about.AboutActivity;
import com.erdemtsynduev.profitcoin.screen.help.HelpActivity;
import com.erdemtsynduev.profitcoin.screen.login.LoginActivity;
import com.erdemtsynduev.profitcoin.screen.signup.SignupActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AccountFragment extends MvpAppCompatFragment implements AccountView {

    @InjectPresenter
    AccountPresenter mAccountPresenter;

    @BindView(R.id.frame_about_program)
    LinearLayout mLinearLayoutAbout;

    @BindView(R.id.frame_help)
    LinearLayout mLinearLayoutHelp;

    @BindView(R.id.frame_login)
    LinearLayout mLinearLayoutLogin;

    @BindView(R.id.frame_signup)
    LinearLayout mLinearLayoutSignUp;

    public static AccountFragment getInstance() {
        return new AccountFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_account, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ButterKnife.bind(this, view);

        mLinearLayoutAbout.setOnClickListener(v -> {
            mAccountPresenter.openScreenAboutApp();
        });

        mLinearLayoutHelp.setOnClickListener(v -> {
            mAccountPresenter.openScreenHelp();
        });

        mLinearLayoutLogin.setOnClickListener(v -> {
            mAccountPresenter.openScreenLogin();
        });

        mLinearLayoutSignUp.setOnClickListener(v -> {
            mAccountPresenter.openScreenSignin();
        });
    }

    @Override
    public void openScreenLogin() {
        Intent intent = new Intent(getContext(), LoginActivity.class);
        startActivity(intent);
    }

    @Override
    public void openScreenSignup() {
        Intent intent = new Intent(getContext(), SignupActivity.class);
        startActivity(intent);
    }

    @Override
    public void openScreenHelp() {
        Intent intent = new Intent(getContext(), HelpActivity.class);
        startActivity(intent);
    }

    @Override
    public void openScreenAboutApp() {
        Intent intent = new Intent(getContext(), AboutActivity.class);
        startActivity(intent);
    }

    @Override
    public void showEmptyLogIn() {

    }

    @Override
    public void showLogIn() {

    }
}
