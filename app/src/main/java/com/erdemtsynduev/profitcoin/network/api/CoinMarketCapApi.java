package com.erdemtsynduev.profitcoin.network.api;

import com.erdemtsynduev.profitcoin.network.model.ResponseItem;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface CoinMarketCapApi {

    @GET("v1/ticker/")
    Observable<ResponseItem> getTicker();
}
