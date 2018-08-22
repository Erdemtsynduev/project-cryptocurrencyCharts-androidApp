package com.erdemtsynduev.profitcoin.screen.apikey;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

public interface ApiKeyView extends MvpView {

    void showErrorApiKey();

    @StateStrategyType(OneExecutionStateStrategy.class)
    void openScreenHome();
}