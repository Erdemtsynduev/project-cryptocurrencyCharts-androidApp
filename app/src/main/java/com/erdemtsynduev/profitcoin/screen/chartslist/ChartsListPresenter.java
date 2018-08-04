package com.erdemtsynduev.profitcoin.screen.chartslist;

import com.arellomobile.mvp.InjectViewState;
import com.erdemtsynduev.profitcoin.ExtendApplication;
import com.erdemtsynduev.profitcoin.network.CoinMarketCapService;
import com.erdemtsynduev.profitcoin.network.model.listallcryptocurrency.Datum;
import com.erdemtsynduev.profitcoin.screen.BasePresenter;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;

@InjectViewState
public class ChartsListPresenter extends BasePresenter<ChartsListView> {

    @Inject
    CoinMarketCapService mCoinMarketCapService;

    public ChartsListPresenter() {
        ExtendApplication.getAppComponent().inject(this);
    }

    public void getData() {
        Disposable disposable = mCoinMarketCapService.getTicker()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(ticker -> {
                    getViewState().showChartsList(ticker.getData());
                }, exception -> {
                    exception.printStackTrace();
                });

        unsubscribeOnDestroy(disposable);
    }

    public void openScreenDetail(Datum datum){
        getViewState().openScreenDetail(datum);
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();

        getViewState().showEmptyChartsList();
    }
}