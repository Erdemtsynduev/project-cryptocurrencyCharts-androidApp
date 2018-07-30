package com.erdemtsynduev.profitcoin.di.Modules;

import com.erdemtsynduev.profitcoin.network.api.CoinMarketCapApi;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module(includes = {RetrofitModule.class})
public class ApiModule {

    @Provides
    @Singleton
    public CoinMarketCapApi provideCoinMarketCapApi(Retrofit retrofit) {
        return retrofit.create(CoinMarketCapApi.class);
    }
}
