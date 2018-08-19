package com.erdemtsynduev.profitcoin.screen.coindetail;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.erdemtsynduev.profitcoin.R;
import com.erdemtsynduev.profitcoin.network.model.listallcryptocurrency.Datum;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.erdemtsynduev.profitcoin.screen.coinlist.CoinListFragment.INTENT_DATUM;

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
}
