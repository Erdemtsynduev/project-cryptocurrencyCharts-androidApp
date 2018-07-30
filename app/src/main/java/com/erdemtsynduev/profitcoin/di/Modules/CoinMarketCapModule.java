package com.erdemtsynduev.profitcoin.di.Modules;

import com.erdemtsynduev.profitcoin.network.CoinMarketCapService;
import com.erdemtsynduev.profitcoin.network.api.CoinMarketCapApi;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module(includes = {ApiModule.class})
public class CoinMarketCapModule {

    @Provides
    @Singleton
    public CoinMarketCapService provideCoinMarketCapService(CoinMarketCapApi coinMarketCapApi) {
        return new CoinMarketCapService(coinMarketCapApi);
    }
}
