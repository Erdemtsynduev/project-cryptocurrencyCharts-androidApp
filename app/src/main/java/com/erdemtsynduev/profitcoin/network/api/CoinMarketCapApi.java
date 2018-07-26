package com.erdemtsynduev.profitcoin.network.api;

import com.erdemtsynduev.profitcoin.network.model.ResponseItem;

import retrofit2.http.GET;
import rx.Observable;

public interface CoinMarketCapApi {

    @GET("v1/ticker/")
    Observable<ResponseItem> getCryptocurrency();
}
