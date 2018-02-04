package com.erdemtsynduev.project_currencytracker.utils;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import rx.Observable;

import rx.schedulers.Schedulers;

public final class RxSchedulers {

    private RxSchedulers() {
    }

    @NonNull
    public static <T> Observable.Transformer<T, T> async() {
        return observable -> observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
