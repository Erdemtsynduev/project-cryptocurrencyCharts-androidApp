package com.erdemtsynduev.profitcoin.screen.coinlist;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.erdemtsynduev.profitcoin.R;
import com.erdemtsynduev.profitcoin.network.model.listallcryptocurrency.Datum;
import com.erdemtsynduev.profitcoin.screen.coindetail.CoinDetailActivity;
import com.erdemtsynduev.profitcoin.screen.coinlist.adapter.CoinListAdapter;
import com.erdemtsynduev.profitcoin.screen.search.SearchCoinActivity;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CoinListFragment extends MvpAppCompatFragment implements CoinListView {

    @InjectPresenter
    CoinListPresenter mCoinListPresenter;

    @BindView(R.id.recyclerView)
    RecyclerView mChartsListRecycler;
    @BindView(R.id.linearLayoutSorting)
    LinearLayout mLinearLayoutSorting;
    @BindView(R.id.searchTitle)
    SearchView mSearchTitle;
    @BindView(R.id.swipeLayout)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @BindView(R.id.fragment_coinlist_progress_bar)
    ProgressBar mCoinListProgressBar;

    private AlertDialog mErrorDialog;
    private DialogPlus mSortDialog;
    private CoinListAdapter mCoinListAdapter;
    private View emptyView;

    public static final String INTENT_DATUM = "datum";
    public static final String INTENT_DATUM_LIST = "datumList";

    public static CoinListFragment getInstance() {
        return new CoinListFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_coinlist, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ButterKnife.bind(this, view);

        mChartsListRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(mChartsListRecycler.getContext(),
                DividerItemDecoration.VERTICAL);
        mChartsListRecycler.addItemDecoration(dividerItemDecoration);

        mCoinListAdapter = new CoinListAdapter(getContext());
        mCoinListAdapter.openLoadAnimation(BaseQuickAdapter.SCALEIN);

        mCoinListAdapter.setOnItemClickListener((adapter, view1, position) -> {
            if ((adapter.getItem(position)) != null) {
                mCoinListPresenter.openScreenDetail((Datum) adapter.getItem(position));
            }
        });

        mChartsListRecycler.setAdapter(mCoinListAdapter);

        mSwipeRefreshLayout.setOnRefreshListener(() -> mCoinListPresenter.loadCoinList(true));

        mLinearLayoutSorting.setOnClickListener(v -> mCoinListPresenter.onSortDialogOpen());

        mSearchTitle.setQueryHint(getString(R.string.search));
        mSearchTitle.setOnClickListener(v -> mCoinListPresenter.openScreenSearch());

        emptyView = getLayoutInflater().inflate(R.layout.empty_view, (ViewGroup) mChartsListRecycler.getParent(), false);
        emptyView.setOnClickListener(v -> mCoinListPresenter.loadCoinList(false));
    }

    @Override
    public void showSortDialog() {
        if (getContext() == null) {
            return;
        }

        mSortDialog = DialogPlus.newDialog(getContext())
                .setContentHolder(new ViewHolder(R.layout.dialog_sort))
                .setCancelable(false)
                .setGravity(Gravity.BOTTOM)
                .create();

        LinearLayout closeDialog = (LinearLayout) mSortDialog.findViewById(R.id.linearLayoutCloseDialog);
        LinearLayout sortByName = (LinearLayout) mSortDialog.findViewById(R.id.linearLayoutSortName);
        LinearLayout sortByTotalSum = (LinearLayout) mSortDialog.findViewById(R.id.linearLayoutSortTotalSum);
        LinearLayout sortByChangeDay = (LinearLayout) mSortDialog.findViewById(R.id.linearLayoutSortChangeDay);
        LinearLayout sortBySevenDay = (LinearLayout) mSortDialog.findViewById(R.id.linearLayoutSortChangeSevenDay);

        closeDialog.setOnClickListener(v -> mCoinListPresenter.onSortDialogCancel());
        sortByName.setOnClickListener(v -> mCoinListPresenter.onSortByNameCoinList());
        sortByTotalSum.setOnClickListener(v -> mCoinListPresenter.onSortByPriceCoinList());
        sortByChangeDay.setOnClickListener(v -> mCoinListPresenter.onSortByChangeDayCoinList());
        sortBySevenDay.setOnClickListener(v -> mCoinListPresenter.onSortByChangeSevenDayCoinList());

        mSortDialog.show();
    }

    @Override
    public void hideSortDialog() {
        if (mSortDialog != null && mSortDialog.isShowing()) {
            mSortDialog.dismiss();
        }
    }

    @Override
    public void showEmptyCoinList() {
        mCoinListAdapter.setEmptyView(emptyView);
    }

    @Override
    public void setCoinList(List<Datum> datumList, boolean maybeMore) {
        mCoinListAdapter.setNewData(datumList);
    }

    @Override
    public void addCoinList(List<Datum> datumList, boolean maybeMore) {
        mCoinListAdapter.addData(datumList);
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
        mChartsListRecycler.setVisibility(View.GONE);
        mCoinListProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideListProgress() {
        mChartsListRecycler.setVisibility(View.VISIBLE);
        mCoinListProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void showErrorDialog(String message) {
        mErrorDialog = new AlertDialog.Builder(getContext())
                .setTitle(R.string.app_name)
                .setMessage(message)
                .setOnCancelListener(dialog -> mCoinListPresenter.onErrorDialogCancel())
                .show();
    }

    @Override
    public void hideErrorDialog() {
        if (mErrorDialog != null && mErrorDialog.isShowing()) {
            mErrorDialog.hide();
        }
    }

    @Override
    public void openScreenDetail(Datum datum) {
        Intent intent = new Intent(getContext(), CoinDetailActivity.class);
        intent.putExtra(INTENT_DATUM, datum);
        startActivity(intent);
    }

    @Override
    public void openScreenSearch(List<Datum> datumList) {
        Intent intent = new Intent(getContext(), SearchCoinActivity.class);
        intent.putParcelableArrayListExtra(INTENT_DATUM_LIST, (ArrayList<? extends Parcelable>) datumList);
        startActivity(intent);
    }

    @Override
    public void onResume() {
        super.onResume();
        mCoinListPresenter.subscribeBus();
    }

    @Override
    public void onPause() {
        super.onPause();
        mCoinListPresenter.unsubscribeBus();
    }
}
