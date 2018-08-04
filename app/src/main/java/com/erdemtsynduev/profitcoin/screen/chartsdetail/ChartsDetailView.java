package com.erdemtsynduev.profitcoin.screen.chartsdetail;

import com.arellomobile.mvp.MvpView;

public interface ChartsDetailView extends MvpView {

    void showAddFavoriteSuccess();

    void showErrorAddFavorite();

    void showChartsDetail();
}