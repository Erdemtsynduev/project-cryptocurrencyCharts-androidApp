package com.erdemtsynduev.profitcoin.screen.search;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.erdemtsynduev.profitcoin.network.model.listallcryptocurrency.Datum;

@InjectViewState
public class SearchCoinPresenter extends MvpPresenter<SearchCoinView> {

    public void openScreenDetail(Datum datum) {
        getViewState().openScreenDetail(datum);
    }
}