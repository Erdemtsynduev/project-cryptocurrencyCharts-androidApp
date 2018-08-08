package com.erdemtsynduev.profitcoin.screen.signup;

import com.arellomobile.mvp.MvpView;

public interface SignupView extends MvpView {

    void openScreenResetPassword();

    void showErrorEnterEmail();

    void showErrorPassword();

    void showErrorPasswordSmall();
}
