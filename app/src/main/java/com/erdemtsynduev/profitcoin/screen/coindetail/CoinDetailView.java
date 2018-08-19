package com.erdemtsynduev.profitcoin.screen.coindetail;

import com.arellomobile.mvp.MvpView;
import com.erdemtsynduev.profitcoin.network.model.listallcryptocurrency.Datum;

public interface CoinDetailView extends MvpView {

    void showAddFavoriteSuccess();

    void showDeleteFavoriteSuccess();

    void showErrorAddFavorite();

    void showChartsDetail(Datum datum);

    void onBackPressed();
}