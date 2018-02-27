package com.erdemtsynduev.cryptocurrencyrates.network.api;

import com.erdemtsynduev.cryptocurrencyrates.network.model.ResponseItem;

import retrofit2.http.GET;
import rx.Observable;

public interface RetrofitGetInterface {

    @GET("v1/ticker/")
    Observable<ResponseItem> getCryptocurrency();
}
