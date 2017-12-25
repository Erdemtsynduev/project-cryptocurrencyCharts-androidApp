package com.erdemtsynduev.project_currencytracker.network;

import com.erdemtsynduev.project_currencytracker.model.ResponseTicker;

import retrofit2.http.GET;
import rx.Observable;

public interface RetrofitGetInterface {

    @GET("v1/ticker/")
    Observable<ResponseTicker> getCryptocurrency();
}
