package com.erdemtsynduev.profitcoin.screen.home;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

@InjectViewState
public class HomePresenter extends MvpPresenter<HomeView> {

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        onClickItemCurrencyList();
    }

    public void onClickItemCurrencyList() {
        getViewState().onClickItemCurrencyList();
    }

    public void onPortfolioSelection() {
        getViewState().showPortfolioFragment();
    }

    public void onCurrencyListSelection() {
        getViewState().showCurrencyListFragment();
    }

    public void onAccountSelection() {
        getViewState().showAccountFragment();
    }

    public void onExitDialogOpen() {
        getViewState().showExitDialog();
    }

    public void onExitDialogAccept() {
        getViewState().closeApp();
    }

    public void onExitDialogClose() {
        getViewState().hideExitDialog();
    }
}