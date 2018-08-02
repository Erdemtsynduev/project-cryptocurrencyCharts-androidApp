package com.erdemtsynduev.profitcoin.screen.portfolio;

import com.arellomobile.mvp.MvpPresenter;

public class PortfolioPresenter extends MvpPresenter<PortfolioView> {

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();

        getViewState().showEmptyPortfolioList();
    }
}