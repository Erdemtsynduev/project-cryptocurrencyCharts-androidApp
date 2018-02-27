package com.erdemtsynduev.cryptocurrencyrates.screen.home;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.erdemtsynduev.cryptocurrencyrates.R;
import com.erdemtsynduev.cryptocurrencyrates.screen.home.currencylist.CurrencyListFragment;
import com.erdemtsynduev.cryptocurrencyrates.screen.home.currencylist.CurrencyListPresenter;
import com.erdemtsynduev.cryptocurrencyrates.utils.ActivityUtils;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        CurrencyListFragment currencyListFragment = (CurrencyListFragment) getSupportFragmentManager()
                .findFragmentById(R.id.contentFrame);

        if (currencyListFragment == null) {
            currencyListFragment = CurrencyListFragment.newInstance();

            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),
                    currencyListFragment, R.id.contentFrame);
        }

        new CurrencyListPresenter(currencyListFragment);
    }
}
