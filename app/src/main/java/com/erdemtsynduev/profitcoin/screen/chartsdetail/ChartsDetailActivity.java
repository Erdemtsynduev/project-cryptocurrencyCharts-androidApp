package com.erdemtsynduev.profitcoin.screen.chartsdetail;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.erdemtsynduev.profitcoin.R;
import com.erdemtsynduev.profitcoin.network.model.listallcryptocurrency.Datum;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ChartsDetailActivity extends MvpAppCompatActivity implements ChartsDetailView {

    @InjectPresenter
    ChartsDetailPresenter mChartsDetailPresenter;

    @BindView(R.id.txt_title_detail)
    TextView textTittleDetail;

    @BindView(R.id.txt_price_dollar_description)
    TextView textPriceDollarDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_currencydetail);
        Toolbar myToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        if (getActionBar() != null) {
            getActionBar().setDisplayHomeAsUpEnabled(true);
        }
        ButterKnife.bind(this);

        Datum datum = null;

        // Get the requested task id
        if (getIntent().getParcelableExtra("datum") != null) {
            datum = getIntent().getParcelableExtra("datum");
        }

        if (datum != null) {
            textTittleDetail.setText(datum.getName());
            textPriceDollarDescription.setText(datum.getQuote().getUSD().getPrice());
        }
    }

    @Override
    public void showAddFavoriteSuccess() {

    }

    @Override
    public void showErrorAddFavorite() {

    }

    @Override
    public void showChartsDetail() {

    }
}
