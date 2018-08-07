package com.erdemtsynduev.profitcoin.screen.coindetail;

import android.os.Bundle;
import android.widget.TextView;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.erdemtsynduev.profitcoin.R;
import com.erdemtsynduev.profitcoin.db.tables.FavoriteTable;
import com.erdemtsynduev.profitcoin.network.model.favoritecoin.FavoriteCoin;
import com.erdemtsynduev.profitcoin.network.model.listallcryptocurrency.Datum;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.arturvasilov.sqlite.core.SQLite;
import ru.arturvasilov.sqlite.core.Where;

public class CoinDetailActivity extends MvpAppCompatActivity implements CoinDetailView {

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

            getFavorite(datum.getName());

            Datum finalDatum = datum;
            toolbarAction2.setOnClickListener(v -> {
                if (isFavorite) {
                    mCoinDetailPresenter.deleteCoinInFavorite(finalDatum.getName());
                } else {
                    mCoinDetailPresenter.saveCoinInFavorite(finalDatum.getId(), finalDatum.getName());
                }
            });
        }
    }

    private void getFavorite(String whereString) {
        FavoriteCoin savedFavoriteCoin = SQLite.get().querySingle(FavoriteTable.TABLE,
                Where.create().equalTo(FavoriteTable.COIN_NAME_FAVORITE, whereString));

        setFavoriteCoin(savedFavoriteCoin);
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
}
