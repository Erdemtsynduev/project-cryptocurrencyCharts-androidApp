package com.erdemtsynduev.cryptocurrencycharts.screen.home.currencylist;

import android.support.annotation.NonNull;

import static com.erdemtsynduev.cryptocurrencycharts.utils.Utils.checkNotNull;

public class CurrencyListPresenter implements CurrencyListContract.Presenter {

    private final CurrencyListContract.View mListCurrencyView;

    public CurrencyListPresenter(@NonNull CurrencyListContract.View tasksView) {
        mListCurrencyView = checkNotNull(tasksView);
        mListCurrencyView.setPresenter(this);
    }

    @Override
    public void start() {

    }
}