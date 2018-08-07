package com.erdemtsynduev.profitcoin;

import android.app.Application;
import android.support.annotation.NonNull;
import android.support.annotation.VisibleForTesting;

import com.erdemtsynduev.profitcoin.di.AppComponent;
import com.erdemtsynduev.profitcoin.di.DaggerAppComponent;
import com.erdemtsynduev.profitcoin.di.Modules.ContextModule;

import io.paperdb.Paper;
import ru.arturvasilov.sqlite.core.SQLite;

public class ExtendApplication extends Application {

    private static AppComponent sAppComponent;
    private static boolean appIsFirstRun = false;

    @Override
    public void onCreate() {
        super.onCreate();

        // Init NoSQL DB
        Paper.init(this);
        SQLite.initialize(this);

        sAppComponent = DaggerAppComponent.builder()
                .contextModule(new ContextModule(this))
                .build();

    }

    public static AppComponent getAppComponent() {
        return sAppComponent;
    }

    public static boolean getIsFirstRun() {
        return appIsFirstRun;
    }

    public static void setIsFirstRun(boolean isFirstRun) {
        appIsFirstRun = isFirstRun;
    }

    @VisibleForTesting
    public static void setAppComponent(@NonNull AppComponent appComponent) {
        sAppComponent = appComponent;
    }
}
