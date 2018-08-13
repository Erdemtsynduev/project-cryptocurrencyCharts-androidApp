package com.erdemtsynduev.profitcoin.db.utils;

import com.google.gson.Gson;

import io.reactivex.annotations.NonNull;

public class GsonHolder {

    private static final Gson GSON = new Gson();

    @NonNull
    public static Gson getGson() {
        return GSON;
    }
}