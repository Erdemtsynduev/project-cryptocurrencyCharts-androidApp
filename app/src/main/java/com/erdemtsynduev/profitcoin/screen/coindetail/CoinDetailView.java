package com.erdemtsynduev.profitcoin.screen.coindetail;

import com.arellomobile.mvp.MvpView;

public interface CoinDetailView extends MvpView {

    void showAddFavoriteSuccess();

    void deleteFavoriteSuccess();

    void showErrorAddFavorite();

    void showChartsDetail();
}