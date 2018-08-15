package com.erdemtsynduev.profitcoin.screen.account;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.google.firebase.auth.FirebaseAuth;

@InjectViewState
public class AccountPresenter extends MvpPresenter<AccountView> {

    public void openScreenLogin() {
        getViewState().openScreenLogin();
    }

    public void openScreenSignin() {
        getViewState().openScreenSignup();
    }

    public void openScreenAboutApp() {
        getViewState().openScreenAboutApp();
    }

    public void openScreenHelp() {
        getViewState().openScreenHelp();
    }

    public void openDialogAddApiKey() {
        getViewState().openDialogAddApiKey();
    }

    public void exitAccount() {
        FirebaseAuth.getInstance().signOut();
        getViewState().showEmptyLogIn();
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();

        checkLogin();
    }

    private void checkLogin() {
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        if (firebaseAuth.getCurrentUser() != null) {
            getViewState().showLogIn();
        } else {
            getViewState().showEmptyLogIn();
        }
    }
}