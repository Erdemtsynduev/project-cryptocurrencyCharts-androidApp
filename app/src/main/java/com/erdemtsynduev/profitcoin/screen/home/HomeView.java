package com.erdemtsynduev.profitcoin.screen.home;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

@StateStrategyType(AddToEndSingleStrategy.class)
public interface HomeView extends MvpView {

    void showPortfolioFragment();

    void showCurrencyListFragment();

    void showAccountFragment();

    void showDialogExit();
}