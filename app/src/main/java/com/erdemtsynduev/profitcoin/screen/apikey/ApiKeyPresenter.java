package com.erdemtsynduev.profitcoin.screen.apikey;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

@InjectViewState
public class ApiKeyPresenter extends MvpPresenter<ApiKeyView> {

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
    }

    public void openScreenHome() {
        getViewState().openScreenHome();
    }

    public void showErrorEnterApiLey() {
        getViewState().showErrorApiKey();
    }
}