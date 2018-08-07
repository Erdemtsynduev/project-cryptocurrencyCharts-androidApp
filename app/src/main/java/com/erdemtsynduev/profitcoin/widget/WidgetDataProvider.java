package com.erdemtsynduev.profitcoin.widget;

import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.erdemtsynduev.profitcoin.R;
import com.erdemtsynduev.profitcoin.network.model.listallcryptocurrency.Datum;

import java.util.ArrayList;
import java.util.List;

import io.paperdb.Paper;

/**
 * WidgetDataProvider acts as the adapter for the collection view widget,
 * providing RemoteViews to the widget in the getViewAt method.
 */
public class WidgetDataProvider implements RemoteViewsService.RemoteViewsFactory {

    private static final String TAG = "WidgetDataProvider";

    List<Datum> mDatumList = new ArrayList<>();
    Context mContext = null;

    public WidgetDataProvider(Context context, Intent intent) {
        mContext = context;
    }

    @Override
    public void onCreate() {

    }

    @Override
    public void onDataSetChanged() {
        mDatumList = Paper.book().read("datumList");
    }

    @Override
    public void onDestroy() {

    }

    @Override
    public int getCount() {
        return mDatumList.size();
    }

    @Override
    public RemoteViews getViewAt(int position) {
        RemoteViews view = new RemoteViews(mContext.getPackageName(),
                R.layout.list_widget_item);

        if (mDatumList != null && !mDatumList.isEmpty()) {
            view.setTextViewText(R.id.widget_text_symbol_currency, mDatumList.get(position).getSymbol());
            view.setTextViewText(R.id.widget_text_name_currency, mDatumList.get(position).getName());
            if (mDatumList.get(position).getQuote().getUSD().getPercentChange24h() != null &&
                    mDatumList.get(position).getQuote().getUSD().getPercentChange24h().contains("-")) {
                view.setTextColor(R.id.widget_text_hours, mContext.getResources().getColor(R.color.colorNegative));
            } else {
                view.setTextColor(R.id.widget_text_hours, mContext.getResources().getColor(R.color.colorPositive));
            }
            if (mDatumList.get(position).getQuote().getUSD().getPercentChange7d() != null &&
                    mDatumList.get(position).getQuote().getUSD().getPercentChange7d().contains("-")) {
                view.setTextColor(R.id.widget_text_days, mContext.getResources().getColor(R.color.colorNegative));
            } else {
                view.setTextColor(R.id.widget_text_days, mContext.getResources().getColor(R.color.colorPositive));
            }
            view.setTextViewText(R.id.widget_text_hours, mDatumList.get(position).getQuote().getUSD().getPercentChange24h());
            view.setTextViewText(R.id.widget_text_days, mDatumList.get(position).getQuote().getUSD().getPercentChange7d());
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
    public long getItemId(int position) {
        return position;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }
}