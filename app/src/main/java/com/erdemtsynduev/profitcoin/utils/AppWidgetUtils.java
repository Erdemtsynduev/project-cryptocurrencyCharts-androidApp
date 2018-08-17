package com.erdemtsynduev.profitcoin.utils;

import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;

import com.erdemtsynduev.profitcoin.widget.PortfolioWidget;

public class AppWidgetUtils {

    //Updates the app widget
    public static void update(Context context) {
        Intent intent = new Intent(context, PortfolioWidget.class);
        intent.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);
        int ids[] = AppWidgetManager.getInstance(context)
                .getAppWidgetIds(new ComponentName(context, PortfolioWidget.class));

        intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, ids);
        context.sendBroadcast(intent);
    }
}
