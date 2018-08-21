package com.erdemtsynduev.profitcoin;

import android.app.Application;
import android.support.annotation.NonNull;
import android.support.annotation.VisibleForTesting;

import com.erdemtsynduev.profitcoin.di.AppComponent;
import com.erdemtsynduev.profitcoin.di.DaggerAppComponent;
import com.erdemtsynduev.profitcoin.di.Modules.ContextModule;

import io.paperdb.Paper;

public class ExtendApplication extends Application {

    private static AppComponent sAppComponent;
    private static String apiKey = BuildConfig.API_KEY;

    @Override
    public void onCreate() {
        super.onCreate();

        Paper.init(this);

        sAppComponent = DaggerAppComponent.builder()
                .contextModule(new ContextModule(this))
                .build();

    }

    public static AppComponent getAppComponent() {
        return sAppComponent;
    }

    public static void setApiKey(String apiKeyNew) {
        apiKey = apiKeyNew;
    }

    public static String getApiKey() {
        return apiKey;
    }

    @VisibleForTesting
    public static void setAppComponent(@NonNull AppComponent appComponent) {
        sAppComponent = appComponent;
    }
}
