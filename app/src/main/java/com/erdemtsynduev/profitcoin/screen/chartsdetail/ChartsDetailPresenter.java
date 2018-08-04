package com.erdemtsynduev.profitcoin.screen.chartsdetail;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

@InjectViewState
public class ChartsDetailPresenter extends MvpPresenter<ChartsDetailView> {

    public void showAddFavotireSuccess() {
        getViewState().showAddFavoriteSuccess();
    }

    public void showErrorAddFavorite() {
        getViewState().showErrorAddFavorite();
    }

    public void showChartsDetail() {
        getViewState().showChartsDetail();
    }
}