package com.erdemtsynduev.profitcoin.screen.coinlist;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndStrategy;
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.erdemtsynduev.profitcoin.network.model.listallcryptocurrency.Datum;

import java.util.List;

public interface CoinListView extends MvpView {

    void showErrorDialog(String message);

    void hideErrorDialog();

    void showSortDialog();

    void hideSortDialog();

    void onStartLoading();

    void onFinishLoading();

    void showRefreshing();

    void hideRefreshing();

    void showListProgress();

    void hideListProgress();

    @StateStrategyType(OneExecutionStateStrategy.class)
    void openScreenDetail(Datum datum);

    @StateStrategyType(OneExecutionStateStrategy.class)
    void openScreenSearch(List<Datum> datumList);

    void showEmptyCoinList();

    void setCoinList(List<Datum> datumList, boolean maybeMore);

    @StateStrategyType(AddToEndStrategy.class)
    void addCoinList(List<Datum> datumList, boolean maybeMore);
}
