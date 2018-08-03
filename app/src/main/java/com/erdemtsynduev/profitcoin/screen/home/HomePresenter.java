package com.erdemtsynduev.profitcoin.screen.home;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

@InjectViewState
public class HomePresenter extends MvpPresenter<HomeView> {

    public void onPortfolioSelection() {
        getViewState().showPortfolioFragment();
    }

    public void onCurrencyListSelection() {
        getViewState().showCurrencyListFragment();
    }

    public void onAccountSelection() {
        getViewState().showAccountFragment();
    }
}