package com.erdemtsynduev.profitcoin.screen.coinlist;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.erdemtsynduev.profitcoin.R;
import com.erdemtsynduev.profitcoin.network.model.listallcryptocurrency.Datum;
import com.erdemtsynduev.profitcoin.screen.coindetail.CoinDetailActivity;
import com.erdemtsynduev.profitcoin.screen.coinlist.adapter.CoinListAdapter;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

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

    private CoinListAdapter mCoinListAdapter;

    private SelectTypeSort selectTypeSort;

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

        mCoinListPresenter.getData();

        mLinearLayoutSorting.setOnClickListener(v -> {
            showSortDialog();
        });
    }

    private void showSortDialog() {
        DialogPlus dialog = DialogPlus
                .newDialog(getActivity())
                .setContentHolder(new ViewHolder(R.layout.dialog_sort))
                .setCancelable(false)
                .setGravity(Gravity.BOTTOM)
                .create();

        LinearLayout closeDialog = (LinearLayout) dialog.findViewById(R.id.linearLayoutCloseDialog);
        LinearLayout sortByName = (LinearLayout) dialog.findViewById(R.id.linearLayoutSortName);
        LinearLayout sortByTotalSum = (LinearLayout) dialog.findViewById(R.id.linearLayoutSortTotalSum);
        LinearLayout sortByChangeDay = (LinearLayout) dialog.findViewById(R.id.linearLayoutSortChangeDay);
        LinearLayout sortBySevenDay = (LinearLayout) dialog.findViewById(R.id.linearLayoutSortChangeSevenDay);

        closeDialog.setOnClickListener(v -> dialog.dismiss());

        sortByName.setOnClickListener(v -> {
            mCoinListPresenter.sortByName();
            dialog.dismiss();
        });

        sortByTotalSum.setOnClickListener(v -> {
            mCoinListPresenter.sortByPrice();
            dialog.dismiss();
        });

        sortByChangeDay.setOnClickListener(v -> {
            mCoinListPresenter.sortByChangeDay();
            dialog.dismiss();
        });

        sortBySevenDay.setOnClickListener(v -> {
            mCoinListPresenter.sortByChangeSevenDay();
            dialog.dismiss();
        });

        dialog.show();
    }

    @Override
    public void showCoinList(List<Datum> datumList) {
        mCoinListAdapter.setNewData(datumList);
    }

    @Override
    public void showEmptyCoinList() {

    }

    @Override
    public void openScreenDetail(Datum datum) {
        Intent intent = new Intent(getContext(), CoinDetailActivity.class);
        intent.putExtra("datum", datum);
        startActivity(intent);
    }
}
