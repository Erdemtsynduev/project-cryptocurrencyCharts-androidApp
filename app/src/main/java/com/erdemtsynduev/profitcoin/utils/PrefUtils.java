package com.erdemtsynduev.profitcoin.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.erdemtsynduev.profitcoin.ExtendApplication;

public class PrefUtils {
    private static final String PREF_NAME = "profitcoin";

    public static SharedPreferences getPrefs() {
        return ExtendApplication.getAppComponent().getContext().getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }

    public static SharedPreferences.Editor getEditor() {
        return getPrefs().edit();
    }
}
