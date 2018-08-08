package com.erdemtsynduev.profitcoin.screen.home;

import android.app.AlertDialog;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

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

    private FragmentManager fragmentManager;
    private Fragment currentFragment;

    private static final String TAG_FRAGMENT_PORTFOLIO = "fragment_portfolio";
    private static final String TAG_FRAGMENT_CURRENCY = "fragment_currency";
    private static final String TAG_FRAGMENT_SETTING = "fragment_settings";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);

        // instantiate the fragment manager
        fragmentManager = getSupportFragmentManager();

        showCurrencyListFragment();

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
    }

    @Override
    public void showPortfolioFragment() {
        Fragment fragment = fragmentManager.findFragmentByTag(TAG_FRAGMENT_PORTFOLIO);
        if (fragment == null) {
            fragment = PortfolioFragment.getInstance();
        }
        replaceFragment(fragment, TAG_FRAGMENT_PORTFOLIO);
    }

    @Override
    public void showCurrencyListFragment() {
        Fragment fragment = fragmentManager.findFragmentByTag(TAG_FRAGMENT_CURRENCY);
        if (fragment == null) {
            fragment = CoinListFragment.getInstance();
        }
        replaceFragment(fragment, TAG_FRAGMENT_CURRENCY);
    }

    @Override
    public void showAccountFragment() {
        Fragment fragment = fragmentManager.findFragmentByTag(TAG_FRAGMENT_SETTING);
        if (fragment == null) {
            fragment = AccountFragment.getInstance();
        }
        replaceFragment(fragment, TAG_FRAGMENT_SETTING);
    }

    private void replaceFragment(@NonNull Fragment fragment, @NonNull String tag) {
        if (!fragment.equals(currentFragment)) {
            fragmentManager
                    .beginTransaction()
                    .replace(R.id.contentFrame, fragment, tag)
                    .commit();
            currentFragment = fragment;
        }
    }

    @Override
    public void showDialogExit() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder
                .setTitle(getString(R.string.app_name))
                .setMessage(getString(R.string.dialog_exit_description))
                .setCancelable(false)
                .setPositiveButton(getString(R.string.dialog_exit_positive), (dialog, which) -> {
                    closeApp();
                })
                .setNegativeButton(getString(R.string.dialog_exit_negative), (dialog, which) -> {
                    dialog.cancel();
                });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    @Override
    public void onBackPressed() {
        mHomePresenter.showDialogExit();
    }

    private void closeApp() {
        finishAffinity();
        System.exit(0);
    }
}
