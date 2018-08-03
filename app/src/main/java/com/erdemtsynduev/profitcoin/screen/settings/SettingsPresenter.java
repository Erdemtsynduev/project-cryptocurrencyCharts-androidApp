package com.erdemtsynduev.profitcoin.screen.settings;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

@InjectViewState
public class SettingsPresenter extends MvpPresenter<SettingsView> {

    public void onShowSuccessClearData() {
        getViewState().showSuccessClearData();
    }

    public void onShowSuccessExportSetting() {
        getViewState().showSuccessExportSetting();
    }

    public void onShowSuccessImportSetting() {
        getViewState().showSuccessImportSetting();
    }
}