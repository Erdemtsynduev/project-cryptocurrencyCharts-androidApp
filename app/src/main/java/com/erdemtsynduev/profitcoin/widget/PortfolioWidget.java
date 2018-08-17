package com.erdemtsynduev.profitcoin.widget;

import android.annotation.TargetApi;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.RemoteViews;

import com.erdemtsynduev.profitcoin.R;

import java.util.Objects;

public class PortfolioWidget extends AppWidgetProvider {

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {

        RemoteViews remoteViews = getFavoriteCountriesFromGridView(context);

        appWidgetManager.updateAppWidget(appWidgetId, remoteViews);
    }

    private static RemoteViews getFavoriteCountriesFromGridView(Context context) {
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.widget_layout);

        Intent gridIntent = new Intent(context, WidgetService.class);
        views.setRemoteAdapter(R.id.widget_list, gridIntent);

        return views;
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onAppWidgetOptionsChanged(Context context, AppWidgetManager appWidgetManager,
                                          int appWidgetId, Bundle newOptions) {
        updateAppWidget(context, appWidgetManager, appWidgetId);
        super.onAppWidgetOptionsChanged(context, appWidgetManager, appWidgetId, newOptions);
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent != null) {
            if (Objects.requireNonNull(intent.getAction()).equals(AppWidgetManager.ACTION_APPWIDGET_UPDATE)) {

                AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
                ComponentName thisWidget = new ComponentName(context, PortfolioWidget.class);

                int[] appWidgetIds = appWidgetManager.getAppWidgetIds(thisWidget);

                RemoteViews remoteViews = getFavoriteCountriesFromGridView(context);

                appWidgetManager.updateAppWidget(appWidgetIds, remoteViews);
                appWidgetManager.notifyAppWidgetViewDataChanged(appWidgetIds, R.id.widget_list);
                onUpdate(context, appWidgetManager, appWidgetIds);
            }
        }
    }
}