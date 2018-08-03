package com.erdemtsynduev.profitcoin.screen.portfolio;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

@InjectViewState
public class PortfolioPresenter extends MvpPresenter<PortfolioView> {

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();

        getViewState().showEmptyPortfolioList();
    }
}