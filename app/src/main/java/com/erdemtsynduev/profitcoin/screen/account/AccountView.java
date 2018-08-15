package com.erdemtsynduev.profitcoin.screen.account;

import com.arellomobile.mvp.MvpView;

public interface AccountView extends MvpView {

    void openScreenLogin();

    void openScreenSignup();

    void openScreenHelp();

    void openScreenAboutApp();

    void openDialogAddApiKey();

    void showEmptyLogIn();

    void showLogIn();
}