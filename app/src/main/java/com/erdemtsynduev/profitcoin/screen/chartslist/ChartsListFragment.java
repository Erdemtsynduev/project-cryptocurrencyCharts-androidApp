package com.erdemtsynduev.profitcoin.screen.chartslist;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.erdemtsynduev.profitcoin.R;

import butterknife.ButterKnife;

public class ChartsListFragment extends MvpAppCompatFragment implements ChartsListView {

    @InjectPresenter
    ChartsListPresenter mChartsListPresenter;

    public static ChartsListFragment getInstance() {
        return new ChartsListFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_chartslist, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ButterKnife.bind(this, view);
    }

    @Override
    public void showChartsList() {

    }

    @Override
    public void showEmptyChartsList() {

    }
}
