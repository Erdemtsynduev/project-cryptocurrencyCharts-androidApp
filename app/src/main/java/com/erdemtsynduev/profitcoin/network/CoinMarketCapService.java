package com.erdemtsynduev.profitcoin.network;

import com.erdemtsynduev.profitcoin.network.api.CoinMarketCapApi;
import com.erdemtsynduev.profitcoin.network.model.ResponseItem;

import io.reactivex.Observable;

public class CoinMarketCapService {

    private CoinMarketCapApi mCoinMarketCapApi;

    public CoinMarketCapService(CoinMarketCapApi coinMarketCapApi) {
        mCoinMarketCapApi = coinMarketCapApi;
    }

    public Observable<ResponseItem> getTicker() {
        return mCoinMarketCapApi.getTicker();
    }
}
