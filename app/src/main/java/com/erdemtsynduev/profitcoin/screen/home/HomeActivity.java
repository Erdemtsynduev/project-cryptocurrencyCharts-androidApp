package com.erdemtsynduev.profitcoin.screen.home;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.design.widget.BottomNavigationView;
import android.os.Bundle;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.erdemtsynduev.profitcoin.R;
import com.erdemtsynduev.profitcoin.screen.account.AccountFragment;
import com.erdemtsynduev.profitcoin.screen.coinlist.CoinListFragment;
import com.erdemtsynduev.profitcoin.screen.portfolio.PortfolioFragment;

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
                            mHomePresenter.onAccountSelection();
                            return true;
                    }
                    return true;
                });

        bottomNavigationView.setSelectedItemId(R.id.menu_list_currency);

        showCurrencyListFragment();
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
                .replace(R.id.contentFrame, CoinListFragment.getInstance())
                .commit();
    }

    @Override
    public void showAccountFragment() {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.contentFrame, AccountFragment.getInstance())
                .commit();
    }

    @Override
    public void showDialogExit() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder
                .setTitle("PROFITCOIN")
                .setMessage("Do you really want to quit?")
                .setCancelable(false)
                .setPositiveButton("YES", (dialog, which) -> {
                    closeAppliction();
                })
                .setNegativeButton("CANCEL", (dialog, which) -> {
                    dialog.cancel();
                });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    @Override
    public void onBackPressed() {
        mHomePresenter.showDialogExit();
    }

    private void closeAppliction() {
        finishAffinity();
        System.exit(0);
    }
}
