package com.erdemtsynduev.profitcoin.screen.portfolio;

import com.arellomobile.mvp.MvpView;

public interface PortfolioView extends MvpView {

    void showPortfolioList();

    void showEmptyPortfolioList();
}