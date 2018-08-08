package com.erdemtsynduev.profitcoin.screen.login;

import com.arellomobile.mvp.MvpView;

public interface LoginView extends MvpView {

    void openScreenSignUp();

    void openScreenResetPassword();

    void showErrorEnterEmail();

    void showErrorPassword();
}