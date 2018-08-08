package com.erdemtsynduev.profitcoin.screen.coinlist;

import com.arellomobile.mvp.MvpView;
import com.erdemtsynduev.profitcoin.network.model.listallcryptocurrency.Datum;

import java.util.List;

public interface CoinListView extends MvpView {

    void showCoinList(List<Datum> datumList);

    void showEmptyCoinList();

    void openScreenDetail(Datum datum);

    void openScreenSearch(List<Datum> datumList);
}
