package com.erdemtsynduev.profitcoin.screen.splash;

import android.text.TextUtils;

import com.arellomobile.mvp.MvpPresenter;
import com.erdemtsynduev.profitcoin.ExtendApplication;
import com.erdemtsynduev.profitcoin.utils.ApiKeyUtils;

public class SplashPresenter extends MvpPresenter<SplashView> {

    @Override
    public void attachView(SplashView view) {
        super.attachView(view);

        view.setAuthorized(!TextUtils.isEmpty(ApiKeyUtils.getApiKey()));
        if (!TextUtils.isEmpty(ApiKeyUtils.getApiKey())) {
            ExtendApplication.setApiKey(ApiKeyUtils.getApiKey());
        }
    }
}
