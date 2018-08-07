package com.erdemtsynduev.profitcoin.network.api;

import com.erdemtsynduev.profitcoin.network.model.listallcryptocurrency.ListAllCryptocurrencies;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

public interface CoinMarketCapApi {

    @Headers("X-CMC_PRO_API_KEY: ADD YOUR KEY")
    @GET("v1/cryptocurrency/listings/latest")
    Call<ListAllCryptocurrencies> getTicker();
}
