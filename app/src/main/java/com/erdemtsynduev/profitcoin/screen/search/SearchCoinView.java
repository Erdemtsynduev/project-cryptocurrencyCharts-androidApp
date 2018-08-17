package com.erdemtsynduev.profitcoin.screen.search;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.erdemtsynduev.profitcoin.network.model.listallcryptocurrency.Datum;

public interface SearchCoinView extends MvpView {

    @StateStrategyType(OneExecutionStateStrategy.class)
    void openScreenDetail(Datum datum);

}