package com.erdemtsynduev.profitcoin.screen.signup;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

@InjectViewState
public class SignupPresenter extends MvpPresenter<SignupView> {

    public void showErrorEnterEmail() {
        getViewState().showErrorEnterEmail();
    }

    public void showErrorPassword() {
        getViewState().showErrorPassword();
    }

    public void showErrorPasswordSmall() {
        getViewState().showErrorPasswordSmall();
    }

    public void openScreenResetPassword() {
        getViewState().openScreenResetPassword();
    }
}