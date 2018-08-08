package com.erdemtsynduev.profitcoin.screen.coinlist;

import com.arellomobile.mvp.InjectViewState;
import com.erdemtsynduev.profitcoin.ExtendApplication;
import com.erdemtsynduev.profitcoin.db.tables.CoinTable;
import com.erdemtsynduev.profitcoin.db.tables.RequestTable;
import com.erdemtsynduev.profitcoin.network.CoinMarketCapService;
import com.erdemtsynduev.profitcoin.network.NetworkService;
import com.erdemtsynduev.profitcoin.network.model.listallcryptocurrency.Datum;
import com.erdemtsynduev.profitcoin.network.model.request.NetworkRequest;
import com.erdemtsynduev.profitcoin.network.model.request.Request;
import com.erdemtsynduev.profitcoin.network.model.request.RequestStatus;
import com.erdemtsynduev.profitcoin.screen.BasePresenter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import ru.arturvasilov.sqlite.core.BasicTableObserver;
import ru.arturvasilov.sqlite.core.SQLite;
import ru.arturvasilov.sqlite.core.Where;
import ru.arturvasilov.sqlite.rx.RxSQLite;

@InjectViewState
public class CoinListPresenter extends BasePresenter<CoinListView> implements BasicTableObserver {

    @Inject
    CoinMarketCapService mCoinMarketCapService;

    private List<Datum> datumListSaved = new ArrayList<>();

    public CoinListPresenter() {
        ExtendApplication.getAppComponent().inject(this);
    }

    public void getData() {
        if (ExtendApplication.getIsFirstRun()) {
            getDataFromDb();
        } else {
            ExtendApplication.setIsFirstRun(true);
            setDataList(null);
        }
    }

    public void sortByName() {
        if (!datumListSaved.isEmpty()) {
            Collections.sort(datumListSaved, Datum.COMPARE_BY_NAME);
            setDataList(datumListSaved);
        }
    }

    public void sortByPrice() {
        if (!datumListSaved.isEmpty()) {
            Collections.sort(datumListSaved, Datum.COMPARE_BY_PRICE);
            setDataList(datumListSaved);
        }
    }

    public void sortByChangeDay() {
        if (!datumListSaved.isEmpty()) {
            Collections.sort(datumListSaved, Datum.COMPARE_BY_DAY);
            setDataList(datumListSaved);
        }
    }

    public void sortByChangeSevenDay() {
        if (!datumListSaved.isEmpty()) {
            Collections.sort(datumListSaved, Datum.COMPARE_BY_SEVEN_DAY);
            setDataList(datumListSaved);
        }
    }

    private void getDataFromDb() {
        RxSQLite.get().query(CoinTable.TABLE)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(coin -> {
                    datumListSaved = coin;
                    setDataList(coin);
                }, throwable -> {
                    throwable.printStackTrace();
                });
    }

    private void setDataList(List<Datum> dataList) {
        if (dataList != null && !dataList.isEmpty()) {
            getViewState().showCoinList(dataList);
        } else {
            SQLite.get().registerObserver(RequestTable.TABLE, this);
            Request request = new Request(NetworkRequest.LIST_COIN);
            NetworkService.start(ExtendApplication.getAppComponent().getContext(), request);
        }
    }

    public void openScreenDetail(Datum datum) {
        getViewState().openScreenDetail(datum);
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();

        getViewState().showEmptyCoinList();
    }

    @Override
    public void onTableChanged() {
        Where where = Where.create().equalTo(RequestTable.REQUEST, NetworkRequest.LIST_COIN);
        RxSQLite.get().querySingle(RequestTable.TABLE, where)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap(request -> {
                    if (request.getStatus() == RequestStatus.IN_PROGRESS) {
                        return Observable.empty();
                    } else if (request.getStatus() == RequestStatus.ERROR) {
                        return Observable.error(new IOException(request.getError()));
                    }
                    return RxSQLite.get().query(CoinTable.TABLE)
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread());
                })
                .subscribe(coin -> {
                    datumListSaved = coin;
                    getViewState().showCoinList(coin);
                }, throwable -> {
                    throwable.printStackTrace();
                });
    }
}