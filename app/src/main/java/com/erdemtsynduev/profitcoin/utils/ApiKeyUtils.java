package com.erdemtsynduev.profitcoin.utils;

public class ApiKeyUtils {
    private static final String API_KEY = "apikey";

    public static String getApiKey() {
        return PrefUtils.getPrefs().getString(API_KEY, "97b8ef41-3c54-4c22-98ee-16c807029099");
    }

    public static void setApiKey(String token) {
        PrefUtils.getEditor().putString(API_KEY, token).commit();
    }
}
