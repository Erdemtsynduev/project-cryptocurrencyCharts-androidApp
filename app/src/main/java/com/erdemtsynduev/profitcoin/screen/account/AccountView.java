package com.erdemtsynduev.profitcoin.screen.account;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

public interface AccountView extends MvpView {

    void showAddApiDialog();

    void hideAddApiDialog();

    @StateStrategyType(OneExecutionStateStrategy.class)
    void openScreenHelp();

    @StateStrategyType(OneExecutionStateStrategy.class)
    void openScreenAboutApp();
}