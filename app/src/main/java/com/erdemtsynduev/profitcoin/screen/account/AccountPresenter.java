package com.erdemtsynduev.profitcoin.screen.account;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.erdemtsynduev.profitcoin.ExtendApplication;
import com.google.firebase.auth.FirebaseAuth;

@InjectViewState
public class AccountPresenter extends MvpPresenter<AccountView> {

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

    public void openScreenLogin() {
        getViewState().openScreenLogin();
    }

    public void openScreenSignup() {
        getViewState().openScreenSignup();
    }

    public void openScreenAboutApp() {
        getViewState().openScreenAboutApp();
    }

    public void openScreenHelp() {
        getViewState().openScreenHelp();
    }

    public void onAddApiDialogOpen() {
        getViewState().showAddApiDialog();
    }

    public void onAddApiDialogAccept(String apiKeyAdd) {
        if (apiKeyAdd != null && !apiKeyAdd.isEmpty()) {
            ExtendApplication.setApiKey(apiKeyAdd);
        }
        getViewState().hideAddApiDialog();
    }

    public void onAddApiDialogCancel() {
        getViewState().hideAddApiDialog();
    }

    public void exitAccount() {
        FirebaseAuth.getInstance().signOut();
        getViewState().showEmptyLogIn();
    }
}