package com.erdemtsynduev.profitcoin.screen.login;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

@InjectViewState
public class LoginPresenter extends MvpPresenter<LoginView> {

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
    }

    public void openScreenSignUp() {
        getViewState().openScreenSignUp();
    }

    public void openScreenResetPassword() {
        getViewState().openScreenResetPassword();
    }

    public void showErrorEnterEmail() {
        getViewState().showErrorEnterEmail();
    }

    public void showErrorPassword() {
        getViewState().showErrorPassword();
    }

}