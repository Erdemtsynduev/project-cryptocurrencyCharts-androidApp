package com.erdemtsynduev.profitcoin.widget;

import android.content.Context;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.erdemtsynduev.profitcoin.R;
import com.erdemtsynduev.profitcoin.network.model.listallcryptocurrency.Datum;

import java.util.ArrayList;
import java.util.List;

import io.paperdb.Paper;

public class ListRemoteViewsFactory implements RemoteViewsService.RemoteViewsFactory {

    private Context mContext;
    private List<Datum> mDatumList = new ArrayList<>();

    ListRemoteViewsFactory(Context context) {
        this.mContext = context;
    }

    @Override
    public void onCreate() {
        initData();
    }

    @Override
    public void onDataSetChanged() {
        initData();
    }

    @Override
    public void onDestroy() {

    }

    @Override
    public int getCount() {
        return (mDatumList != null) ? mDatumList.size() : 0;
    }

    @Override
    public RemoteViews getViewAt(int position) {
        RemoteViews view = new RemoteViews(mContext.getPackageName(),
                R.layout.list_widget_item);

        if (mDatumList != null && !mDatumList.isEmpty()) {
            if (mDatumList.get(position).getSymbol() != null && !mDatumList.get(position).getSymbol().isEmpty()) {
                view.setTextViewText(R.id.widget_text_symbol_currency, mDatumList.get(position).getSymbol());
            }
            if (mDatumList.get(position).getQuote().getUSD().getPercentChange24h() != null &&
                    !mDatumList.get(position).getQuote().getUSD().getPercentChange24h().isEmpty()) {
                view.setTextViewText(R.id.widget_text_hours, mDatumList.get(position).getQuote().getUSD().getPercentChange24h());

                if (mDatumList.get(position).getQuote().getUSD().getPercentChange24h().contains("-")) {
                    view.setTextColor(R.id.widget_text_hours, mContext.getResources().getColor(R.color.colorNegative));
                } else {
                    view.setTextColor(R.id.widget_text_hours, mContext.getResources().getColor(R.color.colorPositive));
                }
            }

            if (mDatumList.get(position).getQuote().getUSD().getPercentChange7d() != null &&
                    !mDatumList.get(position).getQuote().getUSD().getPercentChange7d().isEmpty()) {
                view.setTextViewText(R.id.widget_text_days, mDatumList.get(position).getQuote().getUSD().getPercentChange7d());

                if (mDatumList.get(position).getQuote().getUSD().getPercentChange7d().contains("-")) {
                    view.setTextColor(R.id.widget_text_days, mContext.getResources().getColor(R.color.colorNegative));
                } else {
                    view.setTextColor(R.id.widget_text_days, mContext.getResources().getColor(R.color.colorPositive));
                }
            }
        }
        return view;
    }

    @Override
    public RemoteViews getLoadingView() {
        return null;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    private void initData() {
        mDatumList.clear();
        mDatumList.addAll(mDatumList = Paper.book().read("datumList"));
    }
}
