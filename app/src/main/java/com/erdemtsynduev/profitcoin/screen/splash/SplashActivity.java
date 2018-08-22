package com.erdemtsynduev.profitcoin.screen.splash;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.erdemtsynduev.profitcoin.screen.apikey.ApiKeyActivity;
import com.erdemtsynduev.profitcoin.screen.home.HomeActivity;

public class SplashActivity extends MvpAppCompatActivity implements SplashView {

    @InjectPresenter
    SplashPresenter mSplashPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getMvpDelegate().onAttach();
    }

    @Override
    public void setAuthorized(boolean isAuthorized) {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            Bundle bundle = ActivityOptions.makeSceneTransitionAnimation(this).toBundle();
            startActivity(new Intent(this, isAuthorized ? HomeActivity.class : ApiKeyActivity.class), bundle);
        } else {
            startActivity(new Intent(this, isAuthorized ? HomeActivity.class : ApiKeyActivity.class));
        }
    }
}