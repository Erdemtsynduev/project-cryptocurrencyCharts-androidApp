package com.erdemtsynduev.profitcoin.screen.coindetail;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.widget.TextView;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.erdemtsynduev.profitcoin.R;
import com.erdemtsynduev.profitcoin.db.provider.CoinContentProvider;
import com.erdemtsynduev.profitcoin.db.tables.FavoriteTable;
import com.erdemtsynduev.profitcoin.network.model.favoritecoin.FavoriteCoin;
import com.erdemtsynduev.profitcoin.network.model.listallcryptocurrency.Datum;

import butterknife.BindView;
import butterknife.ButterKnife;

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

    private boolean isFavorite;
    private static final int LOADER_ID = 0x02;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coindetail);
        ButterKnife.bind(this);

        Datum datum = null;

        // Get the requested task id
        if (getIntent().getParcelableExtra("datum") != null) {
            datum = getIntent().getParcelableExtra("datum");
        }

        if (datum != null) {
            textTittleDetail.setText(datum.getName());
            textPriceDollarDescription.setText(datum.getQuote().getUSD().getPrice());
            toolbarTitle.setText(datum.getName());

            toolbarAction1.setText(getText(R.string.back));
            toolbarAction1.setOnClickListener(v -> {
                onBackPressed();
            });

            Bundle bundle = new Bundle();
            bundle.putString("id", datum.getId());
            getFavorite(bundle);

            Datum finalDatum = datum;
            toolbarAction2.setOnClickListener(v -> {
                if (isFavorite) {
                    mCoinDetailPresenter.deleteCoinInFavorite(finalDatum.getId());
                } else {
                    mCoinDetailPresenter.saveCoinInFavorite(finalDatum.getId(), finalDatum.getName());
                }
            });
        }
    }

    private void getFavorite(Bundle bundle) {
        getSupportLoaderManager().initLoader(LOADER_ID, bundle, this);
    }

    private void setFavoriteCoin(FavoriteCoin favoriteCoin) {
        if (favoriteCoin != null) {
            showAddFavoriteSuccess();
        } else {
            deleteFavoriteSuccess();
        }
    }

    @Override
    public void showAddFavoriteSuccess() {
        toolbarAction2.setText("Saved");
        toolbarAction2.setTextColor(getResources().getColor(R.color.colorPositive));
        isFavorite = true;
    }

    @Override
    public void deleteFavoriteSuccess() {
        toolbarAction2.setText("Save");
        toolbarAction2.setTextColor(getResources().getColor(R.color.colorText));
        isFavorite = false;
    }

    @Override
    public void showErrorAddFavorite() {

    }

    @Override
    public void showChartsDetail() {

    }

    @NonNull
    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        CursorLoader loader = new CursorLoader(
                this, Uri.parse(CoinContentProvider.URI_FAVORITE_TABLE + "/" + args.getString("id")),
                null, null, null, null);
        return loader;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
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
        setFavoriteCoin(savedFavoriteCoin);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }
}
