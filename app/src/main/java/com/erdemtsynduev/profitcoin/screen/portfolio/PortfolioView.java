package com.erdemtsynduev.profitcoin.screen.portfolio;

import com.arellomobile.mvp.MvpView;
import com.erdemtsynduev.profitcoin.network.model.listallcryptocurrency.Datum;

import java.util.List;

public interface PortfolioView extends MvpView {

    void showPortfolioList(List<Datum> datumList);

    void showEmptyPortfolioList();

    void openScreenDetail(Datum datum);
}