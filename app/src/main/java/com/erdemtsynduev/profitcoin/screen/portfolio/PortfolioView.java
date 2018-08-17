package com.erdemtsynduev.profitcoin.screen.portfolio;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.erdemtsynduev.profitcoin.network.model.listallcryptocurrency.Datum;

import java.util.List;

public interface PortfolioView extends MvpView {

    void onStartLoading();

    void onFinishLoading();

    void showRefreshing();

    void hideRefreshing();

    void showListProgress();

    void hideListProgress();

    void showPortfolioList(List<Datum> datumList);

    void showEmptyPortfolioList();

    @StateStrategyType(OneExecutionStateStrategy.class)
    void openScreenDetail(Datum datum);
}