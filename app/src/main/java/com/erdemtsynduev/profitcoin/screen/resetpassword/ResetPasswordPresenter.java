package com.erdemtsynduev.profitcoin.screen.resetpassword;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

@InjectViewState
public class ResetPasswordPresenter extends MvpPresenter<ResetPasswordView> {

    public void showErrorReset() {
        getViewState().showErrorReset();
    }

    public void showErrorSendReset() {
        getViewState().showErrorSendReset();
    }

    public void showSuccessResetSend() {
        getViewState().showSuccessResetSend();
    }
}