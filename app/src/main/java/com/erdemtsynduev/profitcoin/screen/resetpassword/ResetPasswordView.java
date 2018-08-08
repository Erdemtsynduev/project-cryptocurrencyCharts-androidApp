package com.erdemtsynduev.profitcoin.screen.resetpassword;

import com.arellomobile.mvp.MvpView;

public interface ResetPasswordView extends MvpView {

    void showErrorReset();

    void showErrorSendReset();

    void showSuccessResetSend();

}