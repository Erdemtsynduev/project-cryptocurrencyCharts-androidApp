package com.erdemtsynduev.cryptocurrencycharts.network.api;

import com.erdemtsynduev.cryptocurrencycharts.network.model.ResponseItem;

import retrofit2.http.GET;
import rx.Observable;

public interface RetrofitGetInterface {

    @GET("v1/ticker/")
    Observable<ResponseItem> getCryptocurrency();
}
