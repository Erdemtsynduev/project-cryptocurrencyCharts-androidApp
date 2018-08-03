package com.erdemtsynduev.profitcoin.screen.chartslist.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.erdemtsynduev.profitcoin.R;
import com.erdemtsynduev.profitcoin.network.model.listallcryptocurrency.Datum;

public class ChartsListAdapter extends BaseQuickAdapter<Datum, BaseViewHolder> {

    public ChartsListAdapter() {
        super(R.layout.item_currencylist, null);
    }

    @Override
    protected void convert(BaseViewHolder helper, Datum item) {
        helper.setText(R.id.text_symbol_currency, item.getSymbol());
        helper.setText(R.id.text_name_currency, item.getName());
        helper.setText(R.id.text_money_currency, item.getQuote().getUSD().getPrice());
        helper.setText(R.id.text_hours, item.getQuote().getUSD().getPercentChange24h());
        helper.setText(R.id.text_days, item.getQuote().getUSD().getPercentChange7d());
    }
}