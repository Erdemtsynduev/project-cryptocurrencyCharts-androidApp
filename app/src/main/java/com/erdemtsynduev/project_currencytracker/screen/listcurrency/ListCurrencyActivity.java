package com.erdemtsynduev.project_currencytracker.screen.listcurrency;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.erdemtsynduev.project_currencytracker.R;
import com.erdemtsynduev.project_currencytracker.utils.ActivityUtils;

public class ListCurrencyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listcurrency);

        ListCurrencyFragment listCurrencyFragment = (ListCurrencyFragment) getSupportFragmentManager()
                .findFragmentById(R.id.contentFrame);

        if (listCurrencyFragment == null) {
            listCurrencyFragment = ListCurrencyFragment.newInstance();

            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),
                    listCurrencyFragment, R.id.contentFrame);
        }

        new ListCurrencyPresenter(listCurrencyFragment);
    }
}
