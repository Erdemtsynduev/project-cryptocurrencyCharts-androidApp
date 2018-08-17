package com.erdemtsynduev.profitcoin.screen.home;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

@StateStrategyType(SkipStrategy.class)
public interface HomeView extends MvpView {

    void onClickItemPortfolio();

    void onClickItemCurrencyList();

    void onClickItemAccount();

    @StateStrategyType(OneExecutionStateStrategy.class)
    void showPortfolioFragment();

    @StateStrategyType(OneExecutionStateStrategy.class)
    void showCurrencyListFragment();

    @StateStrategyType(OneExecutionStateStrategy.class)
    void showAccountFragment();

    void showExitDialog();

    void hideExitDialog();

    void closeApp();
}