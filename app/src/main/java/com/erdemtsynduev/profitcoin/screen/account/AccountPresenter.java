package com.erdemtsynduev.profitcoin.screen.account;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.erdemtsynduev.profitcoin.screen.settings.SettingsView;

@InjectViewState
public class AccountPresenter extends MvpPresenter<AccountView> {

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();

        getViewState().showEmptyLogIn();
    }
}