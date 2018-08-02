package com.erdemtsynduev.profitcoin.screen.chartslist;

import com.arellomobile.mvp.MvpPresenter;

public class ChartsListPresenter extends MvpPresenter<ChartsListView> {

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();

        getViewState().showEmptyChartsList();
    }
}