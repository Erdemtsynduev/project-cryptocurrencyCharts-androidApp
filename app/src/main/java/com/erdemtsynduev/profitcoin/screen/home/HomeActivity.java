package com.erdemtsynduev.profitcoin.screen.home;

import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.erdemtsynduev.profitcoin.R;
import com.erdemtsynduev.profitcoin.screen.currencylist.CurrencyListFragment;
import com.erdemtsynduev.profitcoin.screen.currencylist.CurrencyListPresenter;
import com.erdemtsynduev.profitcoin.screen.portfolio.PortfolioFragment;
import com.erdemtsynduev.profitcoin.screen.portfolio.PortfolioPresenter;
import com.erdemtsynduev.profitcoin.screen.settings.SettingsFragment;
import com.erdemtsynduev.profitcoin.screen.settings.SettingsPresenter;
import com.erdemtsynduev.profitcoin.utils.ActivityUtils;

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
                            return true;
                        case R.id.menu_list_currency:
                            openCurrencyListFragment();
                            return true;
                        case R.id.menu_setting:
                            openSettingsFragment();
                            return true;
                    }
                    return true;
                });
    }

    private void openPortfolioFragment() {
        PortfolioFragment portfolioFragment = PortfolioFragment.newInstance();

        ActivityUtils.replaceFragmentToActivity(getSupportFragmentManager(),
                portfolioFragment, R.id.contentFrame);

        new PortfolioPresenter(portfolioFragment);
    }

    private void openCurrencyListFragment() {
        CurrencyListFragment currencyListFragment = CurrencyListFragment.newInstance();

        ActivityUtils.replaceFragmentToActivity(getSupportFragmentManager(),
                currencyListFragment, R.id.contentFrame);

        new CurrencyListPresenter(currencyListFragment);
    }

    private void openSettingsFragment() {
        SettingsFragment settingsFragment = SettingsFragment.newInstance();

        ActivityUtils.replaceFragmentToActivity(getSupportFragmentManager(),
                settingsFragment, R.id.contentFrame);

        new SettingsPresenter(settingsFragment);
    }
}
