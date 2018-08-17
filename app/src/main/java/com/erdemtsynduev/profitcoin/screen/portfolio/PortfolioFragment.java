package com.erdemtsynduev.profitcoin.screen.portfolio;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.erdemtsynduev.profitcoin.R;
import com.erdemtsynduev.profitcoin.network.model.listallcryptocurrency.Datum;
import com.erdemtsynduev.profitcoin.screen.coindetail.CoinDetailActivity;
import com.erdemtsynduev.profitcoin.screen.coinlist.adapter.CoinListAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.erdemtsynduev.profitcoin.screen.coinlist.CoinListFragment.INTENT_DATUM;

public class PortfolioFragment extends MvpAppCompatFragment implements PortfolioView {

    @InjectPresenter
    PortfolioPresenter mPortfolioPresenter;

    @BindView(R.id.toolbarTitle)
    TextView toolbarTitle;
    @BindView(R.id.fragment_portfolio_swipe_refresh_layout)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @BindView(R.id.fragment_portfolio_recycler_view_favorite)
    RecyclerView mPortfolioListRecycler;
    @BindView(R.id.fragment_portfolio_progress_bar)
    ProgressBar mPortfolioProgressBar;

    private CoinListAdapter mCoinListAdapter;

    private View emptyView;

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

        toolbarTitle.setText(getString(R.string.app_name));

        mPortfolioListRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(mPortfolioListRecycler.getContext(),
                DividerItemDecoration.VERTICAL);
        mPortfolioListRecycler.addItemDecoration(dividerItemDecoration);

        mCoinListAdapter = new CoinListAdapter(getContext());
        mCoinListAdapter.openLoadAnimation(BaseQuickAdapter.SCALEIN);

        mCoinListAdapter.setOnItemClickListener((adapter, view1, position) -> {
            if ((adapter.getItem(position)) != null) {
                mPortfolioPresenter.openScreenDetail((Datum) adapter.getItem(position));
            }
        });

        mPortfolioListRecycler.setAdapter(mCoinListAdapter);

        mSwipeRefreshLayout.setOnRefreshListener(() -> mPortfolioPresenter.loadFavoriteCoinList(true));

        emptyView = getLayoutInflater().inflate(R.layout.empty_view, (ViewGroup) mPortfolioListRecycler.getParent(), false);
        emptyView.setOnClickListener(v -> mPortfolioPresenter.loadFavoriteCoinList(true));
    }

    @Override
    public void onStartLoading() {
        mSwipeRefreshLayout.setEnabled(false);
    }

    @Override
    public void onFinishLoading() {
        mSwipeRefreshLayout.setEnabled(true);
    }

    @Override
    public void showRefreshing() {
        mSwipeRefreshLayout.post(() -> mSwipeRefreshLayout.setRefreshing(true));
    }

    @Override
    public void hideRefreshing() {
        mSwipeRefreshLayout.post(() -> mSwipeRefreshLayout.setRefreshing(false));
    }

    @Override
    public void showListProgress() {
        mPortfolioListRecycler.setVisibility(View.GONE);
        mPortfolioProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideListProgress() {
        mPortfolioListRecycler.setVisibility(View.VISIBLE);
        mPortfolioProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void showPortfolioList(List<Datum> datumList) {
        mCoinListAdapter.setNewData(datumList);
    }

    @Override
    public void showEmptyPortfolioList() {
        mCoinListAdapter.setEmptyView(emptyView);
    }

    @Override
    public void openScreenDetail(Datum datum) {
        Intent intent = new Intent(getContext(), CoinDetailActivity.class);
        intent.putExtra(INTENT_DATUM, datum);
        startActivity(intent);
    }
}
