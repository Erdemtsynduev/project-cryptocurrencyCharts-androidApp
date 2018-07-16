package com.erdemtsynduev.profitcoin.screen.portfolio;

import android.support.annotation.NonNull;

import static com.erdemtsynduev.profitcoin.utils.Utils.checkNotNull;

public class PortfolioPresenter implements PortfolioContract.Presenter {

    private final PortfolioContract.View mPortfolioView;

    public PortfolioPresenter(@NonNull PortfolioContract.View tasksView) {
        mPortfolioView = checkNotNull(tasksView);
        mPortfolioView.setPresenter(this);
    }

    @Override
    public void start() {

    }
}