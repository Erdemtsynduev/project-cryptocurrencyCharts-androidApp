package com.erdemtsynduev.profitcoin.screen.settings;

import com.arellomobile.mvp.MvpPresenter;

public class SettingsPresenter extends MvpPresenter<SettingsView> {

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();

        getViewState().showEmptyLogIn();
    }
}