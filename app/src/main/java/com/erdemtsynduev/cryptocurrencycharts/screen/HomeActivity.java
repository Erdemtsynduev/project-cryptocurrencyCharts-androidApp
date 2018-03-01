package com.erdemtsynduev.cryptocurrencycharts.screen;

import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.erdemtsynduev.cryptocurrencycharts.R;
import com.erdemtsynduev.cryptocurrencycharts.screen.currencylist.CurrencyListFragment;
import com.erdemtsynduev.cryptocurrencycharts.screen.currencylist.CurrencyListPresenter;
import com.erdemtsynduev.cryptocurrencycharts.screen.portfolio.PortfolioFragment;
import com.erdemtsynduev.cryptocurrencycharts.screen.portfolio.PortfolioPresenter;
import com.erdemtsynduev.cryptocurrencycharts.screen.settings.SettingsFragment;
import com.erdemtsynduev.cryptocurrencycharts.screen.settings.SettingsPresenter;
import com.erdemtsynduev.cryptocurrencycharts.utils.ActivityUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeActivity extends AppCompatActivity {

    @BindView(R.id.bottom_navigation)
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);

        bottomNavigationView.setOnNavigationItemSelectedListener(
                item -> {
                    switch (item.getItemId()) {
                        case R.id.menu_portfolio:
                            openPortfolioFragment();
                        case R.id.menu_list_currency:
                            openCurrencyListFragment();
                        case R.id.menu_setting:
                            openSettingsFragment();
                    }
                    return true;
                });
    }

    private void openCurrencyListFragment() {
        CurrencyListFragment currencyListFragment = (CurrencyListFragment) getSupportFragmentManager()
                .findFragmentById(R.id.contentFrame);

        if (currencyListFragment == null) {
            currencyListFragment = CurrencyListFragment.newInstance();

            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),
                    currencyListFragment, R.id.contentFrame);
        }

        new CurrencyListPresenter(currencyListFragment);
    }

    private void openSettingsFragment() {
        SettingsFragment settingsFragment = (SettingsFragment) getSupportFragmentManager()
                .findFragmentById(R.id.contentFrame);

        if (settingsFragment == null) {
            settingsFragment = SettingsFragment.newInstance();

            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),
                    settingsFragment, R.id.contentFrame);
        }

        new SettingsPresenter(settingsFragment);
    }

    private void openPortfolioFragment() {
        PortfolioFragment portfolioFragment = (PortfolioFragment) getSupportFragmentManager()
                .findFragmentById(R.id.contentFrame);

        if (portfolioFragment == null) {
            portfolioFragment = PortfolioFragment.newInstance();

            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),
                    portfolioFragment, R.id.contentFrame);
        }

        new PortfolioPresenter(portfolioFragment);
    }
}
