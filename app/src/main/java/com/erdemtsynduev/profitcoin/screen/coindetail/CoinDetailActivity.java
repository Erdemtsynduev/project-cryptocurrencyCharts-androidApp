package com.erdemtsynduev.profitcoin.screen.coindetail;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.view.View;
import android.widget.TextView;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.erdemtsynduev.profitcoin.R;
import com.erdemtsynduev.profitcoin.db.provider.CoinContentProvider;
import com.erdemtsynduev.profitcoin.db.tables.FavoriteTable;
import com.erdemtsynduev.profitcoin.network.model.favoritecoin.FavoriteCoin;
import com.erdemtsynduev.profitcoin.network.model.listallcryptocurrency.Datum;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.erdemtsynduev.profitcoin.screen.coindetail.CoinDetailPresenter.KEY_DATUM_LOADER;
import static com.erdemtsynduev.profitcoin.screen.coinlist.CoinListFragment.INTENT_DATUM;

public class CoinDetailActivity extends MvpAppCompatActivity implements CoinDetailView,
        LoaderManager.LoaderCallbacks<Cursor> {

    @InjectPresenter
    CoinDetailPresenter mCoinDetailPresenter;

    @BindView(R.id.txt_title_detail)
    TextView textTittleDetail;
    @BindView(R.id.txt_price_dollar_description)
    TextView textPriceDollarDescription;
    @BindView(R.id.toolbarTitle)
    TextView toolbarTitle;
    @BindView(R.id.toolbarAction1)
    TextView toolbarAction1;
    @BindView(R.id.toolbarAction2)
    TextView toolbarAction2;

    private static final int LOADER_ID = 0x02;
    private static final String IS_FAVORITE = "IS_FAVORITE";

    @ProvidePresenter
    CoinDetailPresenter provideCoinDetailPresenter() {
        Datum datum = null;
        if (getIntent().getParcelableExtra(INTENT_DATUM) != null) {
            datum = getIntent().getParcelableExtra(INTENT_DATUM);
        }

        return new CoinDetailPresenter(datum);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coindetail);
        ButterKnife.bind(this);

        toolbarAction1.setText(getText(R.string.back));
        toolbarAction1.setOnClickListener(v -> mCoinDetailPresenter.onClickButtonBack());

        if (savedInstanceState != null) {
            mCoinDetailPresenter.setFavoriteCoin(savedInstanceState.getBoolean(IS_FAVORITE));
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(IS_FAVORITE, mCoinDetailPresenter.isFavoriteCoin());
    }

    @Override
    public void getFavoriteDataFromDb(Bundle bundle) {
        getSupportLoaderManager().initLoader(LOADER_ID, bundle, this);
    }

    @Override
    public void showAddFavoriteSuccess() {
        toolbarAction2.setText(getString(R.string.profile_saved));
        toolbarAction2.setTextColor(getResources().getColor(R.color.colorPositive));
    }

    @Override
    public void showDeleteFavoriteSuccess() {
        toolbarAction2.setText(getString(R.string.profile_save));
        toolbarAction2.setTextColor(getResources().getColor(R.color.colorText));
    }

    @Override
    public void showErrorAddFavorite() {
        toolbarAction2.setVisibility(View.GONE);
    }

    @Override
    public void showChartsDetail(Datum datum) {
        if (datum.getName() != null && !datum.getName().isEmpty()) {
            textTittleDetail.setText(datum.getName());
            toolbarTitle.setText(datum.getName());
        }

        if (datum.getQuote().getUSD().getPrice() != null && !datum.getQuote().getUSD().getPrice().isEmpty()) {
            textPriceDollarDescription.setText(datum.getQuote().getUSD().getPrice());
        }

        toolbarAction2.setVisibility(View.VISIBLE);
        toolbarAction2.setOnClickListener(v -> {
            if (mCoinDetailPresenter.isFavoriteCoin()) {
                mCoinDetailPresenter.deleteFavoriteCoinInDb();
            } else {
                mCoinDetailPresenter.saveFavoriteCoinInDb();
            }
        });
    }

    @NonNull
    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new CursorLoader(this, Uri.parse(CoinContentProvider.URI_FAVORITE_TABLE + "/" + args.getString(KEY_DATUM_LOADER)),
                null, null, null, null);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<Cursor> loader, Cursor data) {
        FavoriteCoin savedFavoriteCoin = null;
        if (data != null && data.getCount() != 0) {
            while (data.moveToNext()) {
                FavoriteCoin favoriteCoin = new FavoriteCoin();
                favoriteCoin.setId(data.getString(data.getColumnIndex(FavoriteTable.COLUMN_ID_FAVORITE)));
                favoriteCoin.setName(data.getString(data.getColumnIndex(FavoriteTable.COLUMN_NAME_FAVORITE)));
                savedFavoriteCoin = favoriteCoin;
            }
            data.close();
        }

        if (savedFavoriteCoin != null) {
            mCoinDetailPresenter.setFavoriteCoin(true);
        } else {
            mCoinDetailPresenter.setFavoriteCoin(false);
        }
    }

    @Override
    public void onLoaderReset(@NonNull Loader<Cursor> loader) {

    }
}
