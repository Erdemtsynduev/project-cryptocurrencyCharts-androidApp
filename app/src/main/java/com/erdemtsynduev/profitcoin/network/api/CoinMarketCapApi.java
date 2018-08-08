package com.erdemtsynduev.profitcoin.network.api;

import com.erdemtsynduev.profitcoin.network.model.listallcryptocurrency.ListAllCryptocurrencies;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface CoinMarketCapApi {

    @GET("v1/cryptocurrency/listings/latest")
    Call<ListAllCryptocurrencies> getTicker(@Header("X-CMC_PRO_API_KEY") String apiKey);
}
