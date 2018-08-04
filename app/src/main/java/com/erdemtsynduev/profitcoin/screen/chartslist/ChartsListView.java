package com.erdemtsynduev.profitcoin.screen.chartslist;

import com.arellomobile.mvp.MvpView;
import com.erdemtsynduev.profitcoin.network.model.listallcryptocurrency.Datum;

import java.util.List;

public interface ChartsListView extends MvpView {

    void showChartsList(List<Datum> datumList);

    void showEmptyChartsList();

    void openScreenDetail(Datum datum);
}
