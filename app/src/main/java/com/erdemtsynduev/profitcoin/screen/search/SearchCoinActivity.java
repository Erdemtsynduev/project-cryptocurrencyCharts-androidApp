package com.erdemtsynduev.profitcoin.screen.search;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.ListView;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.erdemtsynduev.profitcoin.R;
import com.erdemtsynduev.profitcoin.network.model.listallcryptocurrency.Datum;
import com.erdemtsynduev.profitcoin.screen.coindetail.CoinDetailActivity;
import com.erdemtsynduev.profitcoin.screen.search.adapter.SearchListAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchCoinActivity extends MvpAppCompatActivity implements SearchCoinView {

    @InjectPresenter
    SearchCoinPresenter mSearchCoinPresenter;

    @BindView(R.id.img_back)
    ImageView toolbarImgBack;

    @BindView(R.id.searchTitle)
    SearchView toolbarSearch;

    @BindView(R.id.list_view)
    ListView listView;

    private SearchListAdapter mSearchListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);

        toolbarImgBack.setOnClickListener(v -> {
            onBackPressed();
        });

        List<Datum> datumList = new ArrayList<>();

        // Get the requested task id
        if (getIntent().getParcelableArrayListExtra("datumList") != null) {
            datumList = getIntent().getParcelableArrayListExtra("datumList");
        }

        mSearchListAdapter = new SearchListAdapter(this, datumList);
        listView.setAdapter(mSearchListAdapter);

        listView.setTextFilterEnabled(true);

        listView.setOnItemClickListener((parent, view, position, id) -> {
            Datum datum = (Datum) listView.getItemAtPosition(position);
            mSearchCoinPresenter.openScreenDetail(datum);
        });

        setupSearchView();
    }

    private void setupSearchView() {
        toolbarSearch.setIconifiedByDefault(false);
        toolbarSearch.setSubmitButtonEnabled(true);
        toolbarSearch.setQueryHint("Search Here");

        toolbarSearch.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (TextUtils.isEmpty(newText)) {
                    listView.clearTextFilter();
                } else {
                    listView.setFilterText(newText);
                }
                return true;
            }
        });
    }

    @Override
    public void openScreenDetail(Datum datum) {
        Intent intent = new Intent(this, CoinDetailActivity.class);
        intent.putExtra("datum", datum);
        startActivity(intent);
    }
}
