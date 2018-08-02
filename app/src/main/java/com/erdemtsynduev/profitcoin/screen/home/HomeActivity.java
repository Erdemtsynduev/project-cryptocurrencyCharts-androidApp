package com.erdemtsynduev.profitcoin.screen.home;

import android.support.design.widget.BottomNavigationView;
import android.os.Bundle;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.erdemtsynduev.profitcoin.R;
import com.erdemtsynduev.profitcoin.screen.chartslist.ChartsListFragment;
import com.erdemtsynduev.profitcoin.screen.portfolio.PortfolioFragment;
import com.erdemtsynduev.profitcoin.screen.settings.SettingsFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeActivity extends MvpAppCompatActivity implements HomeView {

    @InjectPresenter
    HomePresenter mHomePresenter;

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
                            mHomePresenter.onPortfolioSelection();
                            return true;
                        case R.id.menu_list_currency:
                            mHomePresenter.onCurrencyListSelection();
                            return true;
                        case R.id.menu_setting:
                            mHomePresenter.onSettingsSelection();
                            return true;
                    }
                    return true;
                });
    }

    @Override
    public void showPortfolioFragment() {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.contentFrame, PortfolioFragment.getInstance())
                .commit();
    }

    @Override
    public void showCurrencyListFragment() {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.contentFrame, ChartsListFragment.getInstance())
                .commit();
    }

    @Override
    public void showSettingsFragment() {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.contentFrame, SettingsFragment.getInstance())
                .commit();
    }
}
