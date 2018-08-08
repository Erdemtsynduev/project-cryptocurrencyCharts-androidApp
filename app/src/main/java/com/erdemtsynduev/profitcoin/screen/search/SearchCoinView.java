package com.erdemtsynduev.profitcoin.screen.search;

import com.arellomobile.mvp.MvpView;
import com.erdemtsynduev.profitcoin.network.model.listallcryptocurrency.Datum;

public interface SearchCoinView extends MvpView {

    void openScreenDetail(Datum datum);

}