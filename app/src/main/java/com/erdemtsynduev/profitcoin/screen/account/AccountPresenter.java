package com.erdemtsynduev.profitcoin.screen.account;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

@InjectViewState
public class AccountPresenter extends MvpPresenter<AccountView> {

    public void openScreenLogin(){
        getViewState().openScreenLogin();
    }

    public void openScreenSignin(){
        getViewState().openScreenSignup();
    }

    public void openScreenAboutApp(){
        getViewState().openScreenAboutApp();
    }

    public void openScreenHelp(){
        getViewState().openScreenHelp();
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();

        getViewState().showEmptyLogIn();
    }
}