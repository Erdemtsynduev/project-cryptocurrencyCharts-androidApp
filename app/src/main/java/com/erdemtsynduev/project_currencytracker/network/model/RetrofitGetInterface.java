package com.erdemtsynduev.project_currencytracker.network.model;

import com.erdemtsynduev.project_currencytracker.model.ResponseItem;

import retrofit2.http.GET;
import rx.Observable;

public interface RetrofitGetInterface {

    @GET("v1/ticker/")
    Observable<ResponseItem> getCryptocurrency();
}
