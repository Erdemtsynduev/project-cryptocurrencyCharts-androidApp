package com.erdemtsynduev.profitcoin.screen.home;

import com.arellomobile.mvp.MvpPresenter;

public class HomePresenter extends MvpPresenter<HomeView> {

    public void onPortfolioSelection() {
        getViewState().showPortfolioFragment();
    }

    public void onCurrencyListSelection() {
        getViewState().showCurrencyListFragment();
    }

    public void onSettingsSelection() {
        getViewState().showSettingsFragment();
    }
}