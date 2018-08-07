package com.erdemtsynduev.profitcoin.screen.coinlist.adapter;

import android.content.Context;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.erdemtsynduev.profitcoin.R;
import com.erdemtsynduev.profitcoin.network.model.listallcryptocurrency.Datum;

public class CoinListAdapter extends BaseQuickAdapter<Datum, BaseViewHolder> {

    Context context = null;

    public CoinListAdapter(Context context) {
        super(R.layout.item_coinlist, null);
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, Datum item) {
        helper.setText(R.id.text_symbol_currency, item.getSymbol());
        helper.setText(R.id.text_name_currency, item.getName());
        helper.setText(R.id.text_money_currency, item.getQuote().getUSD().getPrice());
        if (item.getQuote().getUSD().getPercentChange24h() != null &&
                item.getQuote().getUSD().getPercentChange24h().contains("-")) {
            setColor(helper, R.id.text_hours, false);
        } else {
            setColor(helper, R.id.text_hours, true);
        }
        if (item.getQuote().getUSD().getPercentChange7d() != null &&
                item.getQuote().getUSD().getPercentChange7d().contains("-")) {
            setColor(helper, R.id.text_days, false);
        } else {
            setColor(helper, R.id.text_days, true);
        }
        helper.setText(R.id.text_hours, item.getQuote().getUSD().getPercentChange24h());
        helper.setText(R.id.text_days, item.getQuote().getUSD().getPercentChange7d());
    }

    private void setColor(BaseViewHolder helper, int viewId, boolean positive) {
        if (context != null && positive) {
            helper.setTextColor(viewId, context.getResources().getColor(R.color.colorPositive));
        }
        if (context != null && !positive) {
            helper.setTextColor(viewId, context.getResources().getColor(R.color.colorNegative));
        }
    }
}