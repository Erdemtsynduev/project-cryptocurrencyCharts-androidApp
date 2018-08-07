package com.erdemtsynduev.profitcoin.screen.portfolio;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.erdemtsynduev.profitcoin.R;
import com.erdemtsynduev.profitcoin.network.model.listallcryptocurrency.Datum;
import com.erdemtsynduev.profitcoin.screen.coindetail.CoinDetailActivity;
import com.erdemtsynduev.profitcoin.screen.coinlist.adapter.CoinListAdapter;
import com.erdemtsynduev.profitcoin.widget.WidgetService;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.paperdb.Paper;

public class PortfolioFragment extends MvpAppCompatFragment implements PortfolioView {

    @InjectPresenter
    PortfolioPresenter mPortfolioPresenter;

    @BindView(R.id.recyclerViewPortfolio)
    RecyclerView mPortfolioListRecycler;

    @BindView(R.id.toolbarTitle)
    TextView toolbarTitle;

    private CoinListAdapter mCoinListAdapter;

    public static PortfolioFragment getInstance() {
        return new PortfolioFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_portfolio, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ButterKnife.bind(this, view);

        mPortfolioListRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(mPortfolioListRecycler.getContext(),
                DividerItemDecoration.VERTICAL);
        mPortfolioListRecycler.addItemDecoration(dividerItemDecoration);

        mCoinListAdapter = new CoinListAdapter(getContext());
        mCoinListAdapter.openLoadAnimation(BaseQuickAdapter.SCALEIN);

        toolbarTitle.setText("Profitcoin");

        mCoinListAdapter.setOnItemClickListener((adapter, view1, position) -> {
            if ((adapter.getItem(position)) != null) {
                mPortfolioPresenter.openScreenDetail((Datum) adapter.getItem(position));
            }
        });

        mPortfolioListRecycler.setAdapter(mCoinListAdapter);

        mPortfolioPresenter.getData();
    }

    @Override
    public void showPortfolioList(List<Datum> datumList) {
        WidgetService.updateWidget(getContext(), datumList);
        mCoinListAdapter.setNewData(datumList);
    }

    @Override
    public void showEmptyPortfolioList() {

    }

    @Override
    public void openScreenDetail(Datum datum) {
        Intent intent = new Intent(getContext(), CoinDetailActivity.class);
        intent.putExtra("datum", datum);
        startActivity(intent);
    }
}
