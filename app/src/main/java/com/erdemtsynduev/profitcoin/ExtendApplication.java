package com.erdemtsynduev.profitcoin;

import android.app.Application;
import android.support.annotation.NonNull;
import android.support.annotation.VisibleForTesting;

import com.erdemtsynduev.profitcoin.di.AppComponent;
import com.erdemtsynduev.profitcoin.di.DaggerAppComponent;
import com.erdemtsynduev.profitcoin.di.Modules.ContextModule;

public class ExtendApplication extends Application {

    private static AppComponent sAppComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        sAppComponent = DaggerAppComponent.builder()
                .contextModule(new ContextModule(this))
                .build();

    }

    public static AppComponent getAppComponent() {
        return sAppComponent;
    }


    @VisibleForTesting
    public static void setAppComponent(@NonNull AppComponent appComponent) {
        sAppComponent = appComponent;
    }
}
