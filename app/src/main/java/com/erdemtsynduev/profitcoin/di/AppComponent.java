package com.erdemtsynduev.profitcoin.di;

import android.content.Context;

import com.erdemtsynduev.profitcoin.di.Modules.BusModule;
import com.erdemtsynduev.profitcoin.di.Modules.CoinMarketCapModule;
import com.erdemtsynduev.profitcoin.di.Modules.ContextModule;
import com.erdemtsynduev.profitcoin.network.CoinMarketCapService;
import com.erdemtsynduev.profitcoin.network.NetworkService;
import com.erdemtsynduev.profitcoin.screen.chartslist.ChartsListPresenter;
import com.squareup.otto.Bus;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ContextModule.class, BusModule.class, CoinMarketCapModule.class})
public interface AppComponent {
    Context getContext();

    Bus getBus();

    CoinMarketCapService getCoinMarketCapService();

    void inject(ChartsListPresenter presenter);
    void inject(NetworkService networkService);
}
