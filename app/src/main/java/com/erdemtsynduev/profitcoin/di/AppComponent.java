package com.erdemtsynduev.profitcoin.di;

import android.content.Context;

import com.erdemtsynduev.profitcoin.di.Modules.BusModule;
import com.erdemtsynduev.profitcoin.di.Modules.ContextModule;
import com.squareup.otto.Bus;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ContextModule.class, BusModule.class})
public interface AppComponent {
    Context getContext();
    Bus getBus();

}
