package com.erdemtsynduev.cryptocurrencycharts.screen.home.settings;

import android.support.annotation.NonNull;

import static com.erdemtsynduev.cryptocurrencycharts.utils.Utils.checkNotNull;

public class SettingsPresenter implements SettingsContract.Presenter {

    private final SettingsContract.View mSettingsView;

    public SettingsPresenter(@NonNull SettingsContract.View tasksView) {
        mSettingsView = checkNotNull(tasksView);
        mSettingsView.setPresenter(this);
    }

    @Override
    public void start() {

    }
}