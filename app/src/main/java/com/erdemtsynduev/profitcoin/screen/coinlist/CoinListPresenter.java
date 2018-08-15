package com.erdemtsynduev.profitcoin.screen.coinlist;

import android.database.Cursor;
import android.net.Uri;
import android.widget.Toast;

import com.arellomobile.mvp.InjectViewState;
import com.erdemtsynduev.profitcoin.ExtendApplication;
import com.erdemtsynduev.profitcoin.db.provider.CoinContentProvider;
import com.erdemtsynduev.profitcoin.db.tables.CoinTable;
import com.erdemtsynduev.profitcoin.db.tables.RequestTable;
import com.erdemtsynduev.profitcoin.db.utils.GsonHolder;
import com.erdemtsynduev.profitcoin.network.CoinMarketCapService;
import com.erdemtsynduev.profitcoin.db.DbTableChangedEvent;
import com.erdemtsynduev.profitcoin.network.NetworkService;
import com.erdemtsynduev.profitcoin.network.model.listallcryptocurrency.Datum;
import com.erdemtsynduev.profitcoin.network.model.listallcryptocurrency.Quote;
import com.erdemtsynduev.profitcoin.network.model.request.NetworkRequest;
import com.erdemtsynduev.profitcoin.network.model.request.Request;
import com.erdemtsynduev.profitcoin.network.model.request.RequestStatus;
import com.erdemtsynduev.profitcoin.screen.BasePresenter;
import com.squareup.otto.Subscribe;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

@InjectViewState
public class CoinListPresenter extends BasePresenter<CoinListView> {

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
        Cursor cursorData = ExtendApplication.getAppComponent().getContext().getContentResolver()
                .query(CoinContentProvider.URI_COIN_TABLE, null,
                        null, null, null);
        datumListSaved.clear();
        if (cursorData != null && cursorData.getCount() != 0) {
            while (cursorData.moveToNext()) {
                Datum datum = new Datum();
                datum.setId(cursorData.getString(cursorData.getColumnIndex(CoinTable.COLUMN_ID)));
                datum.setName(cursorData.getString(cursorData.getColumnIndex(CoinTable.COLUMN_NAME)));
                datum.setSymbol(cursorData.getString(cursorData.getColumnIndex(CoinTable.COLUMN_SYMBOL)));
                datum.setSlug(cursorData.getString(cursorData.getColumnIndex(CoinTable.COLUMN_SLUG)));
                datum.setCmcRank(cursorData.getString(cursorData.getColumnIndex(CoinTable.COLUMN_CMC_RANK)));
                datum.setNumMarkets(cursorData.getString(cursorData.getColumnIndex(CoinTable.COLUMN_NUM_MARKETS)));
                datum.setCirculatingSupply(cursorData.getString(cursorData.getColumnIndex(CoinTable.COLUMN_CIRCULATING_SUPPLY)));
                datum.setTotalSupply(cursorData.getString(cursorData.getColumnIndex(CoinTable.COLUMN_TOTAL_SUPPLY)));
                datum.setMaxSupply(cursorData.getString(cursorData.getColumnIndex(CoinTable.COLUMN_MAX_SUPPLY)));
                datum.setLastUpdated(cursorData.getString(cursorData.getColumnIndex(CoinTable.COLUMN_LAST_UPDATED)));
                datum.setQuote(GsonHolder.getGson().fromJson(cursorData.getString(cursorData.getColumnIndex(CoinTable.COLUMN_QUOTE)), Quote.class));
                datumListSaved.add(datum);
            }
            cursorData.close();
            getViewState().showCoinList(datumListSaved);
        } else {
            getViewState().showEmptyCoinList();
        }
    }

    private void setDataList(List<Datum> dataList) {
        if (dataList != null && !dataList.isEmpty()) {
            getViewState().showCoinList(dataList);
        } else {
            Request request = new Request(NetworkRequest.LIST_COIN);
            NetworkService.start(ExtendApplication.getAppComponent().getContext(), request);
        }
    }

    public void openScreenDetail(Datum datum) {
        getViewState().openScreenDetail(datum);
    }

    public void openScreenSearch() {
        if (!datumListSaved.isEmpty()) {
            getViewState().openScreenSearch(datumListSaved);
        }
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        getViewState().showEmptyCoinList();
    }

    public void subscribeBus() {
        ExtendApplication.getAppComponent().getBus().register(this);
    }

    public void unsubscribeBus() {
        ExtendApplication.getAppComponent().getBus().unregister(this);
    }

    @Subscribe
    public void onTableChanged(DbTableChangedEvent event) {
        if (event.nameTable.equals(RequestTable.TABLE_NAME)) {
            Cursor cursor = ExtendApplication.getAppComponent().getContext().getContentResolver().
                    query(Uri.parse(CoinContentProvider.URI_REQUEST_TABLE + "/" + 1), null,
                            NetworkRequest.LIST_COIN, null, null);

            Request savedRequest = null;

            if (cursor != null && cursor.getCount() != 0) {
                while (cursor.moveToNext()) {
                    String idRequest = cursor.getString(cursor.getColumnIndex(RequestTable.COLUMN_ID));
                    String nameRequest = cursor.getString(cursor.getColumnIndex(RequestTable.COLUMN_REQUEST));
                    String statusRequest = cursor.getString(cursor.getColumnIndex(RequestTable.COLUMN_STATUS));
                    String errorRequest = cursor.getString(cursor.getColumnIndex(RequestTable.COLUMN_ERROR));
                    savedRequest = new Request(nameRequest);
                    savedRequest.setIdRequest(idRequest);
                    savedRequest.setStatus(RequestStatus.valueOf(statusRequest));
                    savedRequest.setError(errorRequest);
                }
                cursor.close();
            }

            if (savedRequest == null) {
                return;
            } else {
                if (savedRequest.getStatus() == RequestStatus.IN_PROGRESS) {
                    return;
                } else if (savedRequest.getStatus() == RequestStatus.ERROR) {
                    Toast toast = Toast.makeText(ExtendApplication.getAppComponent().getContext(),
                            savedRequest.getError(), Toast.LENGTH_SHORT);
                    toast.show();
                    return;
                }
            }

            Cursor cursorData = ExtendApplication.getAppComponent().getContext().getContentResolver()
                    .query(CoinContentProvider.URI_COIN_TABLE, null,
                            null, null, null);
            datumListSaved.clear();
            if (cursorData != null && cursorData.getCount() != 0) {
                while (cursorData.moveToNext()) {
                    Datum datum = new Datum();
                    datum.setId(cursorData.getString(cursorData.getColumnIndex(CoinTable.COLUMN_ID)));
                    datum.setName(cursorData.getString(cursorData.getColumnIndex(CoinTable.COLUMN_NAME)));
                    datum.setSymbol(cursorData.getString(cursorData.getColumnIndex(CoinTable.COLUMN_SYMBOL)));
                    datum.setSlug(cursorData.getString(cursorData.getColumnIndex(CoinTable.COLUMN_SLUG)));
                    datum.setCmcRank(cursorData.getString(cursorData.getColumnIndex(CoinTable.COLUMN_CMC_RANK)));
                    datum.setNumMarkets(cursorData.getString(cursorData.getColumnIndex(CoinTable.COLUMN_NUM_MARKETS)));
                    datum.setCirculatingSupply(cursorData.getString(cursorData.getColumnIndex(CoinTable.COLUMN_CIRCULATING_SUPPLY)));
                    datum.setTotalSupply(cursorData.getString(cursorData.getColumnIndex(CoinTable.COLUMN_TOTAL_SUPPLY)));
                    datum.setMaxSupply(cursorData.getString(cursorData.getColumnIndex(CoinTable.COLUMN_MAX_SUPPLY)));
                    datum.setLastUpdated(cursorData.getString(cursorData.getColumnIndex(CoinTable.COLUMN_LAST_UPDATED)));
                    datum.setQuote(GsonHolder.getGson().fromJson(cursorData.getString(cursorData.getColumnIndex(CoinTable.COLUMN_QUOTE)), Quote.class));
                    datumListSaved.add(datum);
                }
                cursorData.close();
                getViewState().showCoinList(datumListSaved);
            } else {
                getViewState().showEmptyCoinList();
            }
        }
    }
}