package com.erdemtsynduev.profitcoin.di.Modules;

import com.squareup.otto.Bus;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class BusModule {

    @Provides
    @Singleton
    public Bus provideBus() {
        return new Bus();
    }
}
