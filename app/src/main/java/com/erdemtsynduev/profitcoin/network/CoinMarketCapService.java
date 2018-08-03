package com.erdemtsynduev.profitcoin.network;

import com.erdemtsynduev.profitcoin.network.api.CoinMarketCapApi;
import com.erdemtsynduev.profitcoin.network.model.listallcryptocurrency.ListAllCryptocurrencies;

import io.reactivex.Observable;

public class CoinMarketCapService {

    private CoinMarketCapApi mCoinMarketCapApi;

    public CoinMarketCapService(CoinMarketCapApi coinMarketCapApi) {
        mCoinMarketCapApi = coinMarketCapApi;
    }

    public Observable<ListAllCryptocurrencies> getTicker() {
        return mCoinMarketCapApi.getTicker();
    }
}
