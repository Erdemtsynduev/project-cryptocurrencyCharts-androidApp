package com.erdemtsynduev.profitcoin.network;

import com.erdemtsynduev.profitcoin.network.api.CoinMarketCapApi;
import com.erdemtsynduev.profitcoin.network.model.listallcryptocurrency.ListAllCryptocurrencies;

import retrofit2.Call;

public class CoinMarketCapService {

    private CoinMarketCapApi mCoinMarketCapApi;

    public CoinMarketCapService(CoinMarketCapApi coinMarketCapApi) {
        mCoinMarketCapApi = coinMarketCapApi;
    }

    public Call<ListAllCryptocurrencies> getTicker() {
        return mCoinMarketCapApi.getTicker();
    }
}
