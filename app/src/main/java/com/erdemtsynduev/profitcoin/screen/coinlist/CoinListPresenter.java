package com.erdemtsynduev.profitcoin.screen.coinlist;

import android.database.Cursor;

import com.arellomobile.mvp.InjectViewState;
import com.erdemtsynduev.profitcoin.ExtendApplication;
import com.erdemtsynduev.profitcoin.db.provider.CoinContentProvider;
import com.erdemtsynduev.profitcoin.db.tables.CoinTable;
import com.erdemtsynduev.profitcoin.db.utils.GsonHolder;
import com.erdemtsynduev.profitcoin.network.CoinMarketCapService;
import com.erdemtsynduev.profitcoin.db.DbTableChangedEvent;
import com.erdemtsynduev.profitcoin.network.NetworkService;
import com.erdemtsynduev.profitcoin.network.api.CoinMarketCapApi;
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

import io.paperdb.Paper;

@InjectViewState
public class CoinListPresenter extends BasePresenter<CoinListView> {

    @Inject
    CoinMarketCapService mCoinMarketCapService;

    private boolean mIsInLoading;
    private List<Datum> datumListSaved = new ArrayList<>();

    public CoinListPresenter() {
        ExtendApplication.getAppComponent().inject(this);
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        loadCoinList(false);
    }

    public void loadCoinList(boolean isRefreshing) {
        loadData(1, false, isRefreshing);
    }

    private void loadData(int page, boolean isPageLoading, boolean isRefreshing) {
        if (mIsInLoading) {
            return;
        }
        mIsInLoading = true;

        getViewState().onStartLoading();

        showProgress(isPageLoading, isRefreshing);

        Request request = new Request(NetworkRequest.LIST_COIN);
        NetworkService.start(ExtendApplication.getAppComponent().getContext(), request);
    }

    private void onLoadingFinish(boolean isPageLoading, boolean isRefreshing) {
        mIsInLoading = false;

        getViewState().onFinishLoading();

        hideProgress(isPageLoading, isRefreshing);
    }

    private void onLoadingSuccess(boolean isPageLoading, List<Datum> datumList) {
        boolean maybeMore = datumList.size() >= CoinMarketCapApi.PAGE_SIZE;
        if (isPageLoading) {
            getViewState().addCoinList(datumList, maybeMore);
        } else {
            getViewState().setCoinList(datumList, maybeMore);
        }
    }

    private void onLoadingFailed(String error) {
        getViewState().showErrorDialog(error);
    }

    private void showProgress(boolean isPageLoading, boolean isRefreshing) {
        if (isPageLoading) {
            return;
        }

        if (isRefreshing) {
            getViewState().showRefreshing();
        } else {
            getViewState().showListProgress();
        }
    }

    private void hideProgress(boolean isPageLoading, boolean isRefreshing) {
        if (isPageLoading) {
            return;
        }

        if (isRefreshing) {
            getViewState().hideRefreshing();
        } else {
            getViewState().hideListProgress();
        }
    }

    public void onSortByNameCoinList() {
        if (!datumListSaved.isEmpty()) {
            Collections.sort(datumListSaved, Datum.COMPARE_BY_NAME);
            getViewState().setCoinList(datumListSaved, false);
            onSortDialogCancel();
        }
    }

    public void onSortByPriceCoinList() {
        if (!datumListSaved.isEmpty()) {
            Collections.sort(datumListSaved, Datum.COMPARE_BY_PRICE);
            getViewState().setCoinList(datumListSaved, false);
            onSortDialogCancel();
        }
    }

    public void onSortByChangeDayCoinList() {
        if (!datumListSaved.isEmpty()) {
            Collections.sort(datumListSaved, Datum.COMPARE_BY_DAY);
            getViewState().setCoinList(datumListSaved, false);
            onSortDialogCancel();
        }
    }

    public void onSortByChangeSevenDayCoinList() {
        if (!datumListSaved.isEmpty()) {
            Collections.sort(datumListSaved, Datum.COMPARE_BY_SEVEN_DAY);
            getViewState().setCoinList(datumListSaved, false);
            onSortDialogCancel();
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

    public void onErrorDialogCancel() {
        getViewState().hideErrorDialog();
    }

    public void onSortDialogOpen() {
        getViewState().showSortDialog();
    }

    public void onSortDialogCancel() {
        getViewState().hideSortDialog();
    }

    public void subscribeBus() {
        ExtendApplication.getAppComponent().getBus().register(this);
    }

    public void unsubscribeBus() {
        ExtendApplication.getAppComponent().getBus().unregister(this);
    }

    @Subscribe
    public void onTableChanged(DbTableChangedEvent event) {
        if (event.nameTable.equals("RequestTable")) {
            Request savedRequest = Paper.book().read(NetworkRequest.LIST_COIN);

            if (savedRequest.getStatus() == RequestStatus.IN_PROGRESS) {
                return;
            } else if (savedRequest.getStatus() == RequestStatus.ERROR) {
                onLoadingFinish(false, true);
                onLoadingFailed(savedRequest.getError());
                return;
            }

            List<Datum> datumListBuffer = getCoinListFromDb();

            if (datumListBuffer != null && !datumListBuffer.isEmpty()) {
                datumListSaved.clear();
                datumListSaved.addAll(datumListBuffer);
                onLoadingFinish(false, false);
                onLoadingSuccess(false, datumListSaved);
            } else {
                getViewState().showEmptyCoinList();
            }
        }
    }

    private List<Datum> getCoinListFromDb() {
        List<Datum> datumList = new ArrayList<>();

        Cursor cursorData = ExtendApplication.getAppComponent().getContext().getContentResolver()
                .query(CoinContentProvider.URI_COIN_TABLE, null,
                        null, null, null);

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
                datumList.add(datum);
            }
            cursorData.close();

            return datumList;
        } else {
            return null;
        }
    }
}