package com.erdemtsynduev.profitcoin.screen.settings;

import com.arellomobile.mvp.MvpView;

public interface SettingsView extends MvpView {

    void showSuccessImportSetting();

    void showSuccessExportSetting();

    void showSuccessClearData();
}