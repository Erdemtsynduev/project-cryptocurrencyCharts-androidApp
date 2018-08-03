package com.erdemtsynduev.profitcoin.screen.chartslist;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.erdemtsynduev.profitcoin.R;
import com.erdemtsynduev.profitcoin.network.model.listallcryptocurrency.Datum;
import com.erdemtsynduev.profitcoin.screen.chartslist.adapter.ChartsListAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ChartsListFragment extends MvpAppCompatFragment implements ChartsListView {

    @InjectPresenter
    ChartsListPresenter mChartsListPresenter;

    @BindView(R.id.recyclerView)
    RecyclerView mChartsListRecycler;

    private ChartsListAdapter mChartsListAdapter;

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
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ButterKnife.bind(this, view);

        mChartsListRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(mChartsListRecycler.getContext(),
                DividerItemDecoration.VERTICAL);
        mChartsListRecycler.addItemDecoration(dividerItemDecoration);

        mChartsListAdapter = new ChartsListAdapter();
        mChartsListAdapter.openLoadAnimation(BaseQuickAdapter.SCALEIN);

        mChartsListRecycler.setAdapter(mChartsListAdapter);

        mChartsListPresenter.getData();
    }

    @Override
    public void showChartsList(List<Datum> datumList) {
        mChartsListAdapter.setNewData(datumList);
    }

    @Override
    public void showEmptyChartsList() {

    }
}
