package com.erdemtsynduev.cryptocurrencycharts.screen;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.erdemtsynduev.cryptocurrencycharts.R;
import com.erdemtsynduev.cryptocurrencycharts.screen.currencylist.CurrencyListFragment;
import com.erdemtsynduev.cryptocurrencycharts.screen.currencylist.CurrencyListPresenter;
import com.erdemtsynduev.cryptocurrencycharts.utils.ActivityUtils;

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
