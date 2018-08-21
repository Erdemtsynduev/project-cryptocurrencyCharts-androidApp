package com.erdemtsynduev.profitcoin.screen.account;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.erdemtsynduev.profitcoin.ExtendApplication;

@InjectViewState
public class AccountPresenter extends MvpPresenter<AccountView> {

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
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
}