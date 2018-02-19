package com.erdemtsynduev.project_currencytracker.screen.listcurrency;

import android.support.annotation.NonNull;

import static com.google.common.base.Preconditions.checkNotNull;

public class ListCurrencyPresenter implements ListCurrencyContract.Presenter {

    private final ListCurrencyContract.View mListCurrencyView;

    public ListCurrencyPresenter(@NonNull ListCurrencyContract.View tasksView) {
        mListCurrencyView = checkNotNull(tasksView, "tasksView cannot be null!");
        mListCurrencyView.setPresenter(this);
    }

    @Override
    public void start() {

    }
}