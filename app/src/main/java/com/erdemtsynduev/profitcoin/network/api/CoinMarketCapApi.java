package com.erdemtsynduev.profitcoin.network.api;

import com.erdemtsynduev.profitcoin.network.model.listallcryptocurrency.ListAllCryptocurrencies;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Headers;

public interface CoinMarketCapApi {

    @Headers("X-CMC_PRO_API_KEY: ADD YOUR API KEY")
    @GET("v1/cryptocurrency/listings/latest")
    Observable<ListAllCryptocurrencies> getTicker();
}
