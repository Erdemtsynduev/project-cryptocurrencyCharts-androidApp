package com.erdemtsynduev.profitcoin.widget;

import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViewsService;

import com.erdemtsynduev.profitcoin.network.model.listallcryptocurrency.Datum;

import java.util.List;

import io.paperdb.Paper;

/**
 * WidgetService is the {@link RemoteViewsService} that will return our RemoteViewsFactory
 */
public class WidgetService extends RemoteViewsService {

    public static void updateWidget(Context context, List<Datum> datumList) {
        Paper.book().write("datumList", datumList);

        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
        int[] appWidgetIds = appWidgetManager.getAppWidgetIds(new ComponentName(context, WidgetDataProvider.class));
        PortfolioWidget.updateAppWidgets(context, appWidgetManager, appWidgetIds);
    }

    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new WidgetDataProvider(this, intent);
    }
}