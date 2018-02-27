package com.erdemtsynduev.cryptocurrencyrates.screen.home.currencylist;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.erdemtsynduev.cryptocurrencyrates.R;

import static com.erdemtsynduev.cryptocurrencyrates.utils.Utils.checkNotNull;

public class CurrencyListFragment extends Fragment implements CurrencyListContract.View {

    private CurrencyListContract.Presenter mPresenter;

    public static CurrencyListFragment newInstance() {
        return new CurrencyListFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_currencylist, container, false);

        return view;
    }


    @Override
    public void setPresenter(@NonNull CurrencyListContract.Presenter presenter) {
        mPresenter = checkNotNull(presenter);
    }
}
