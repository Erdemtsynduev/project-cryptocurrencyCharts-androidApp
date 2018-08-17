package com.erdemtsynduev.profitcoin.screen.portfolio;

import android.database.Cursor;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.erdemtsynduev.profitcoin.ExtendApplication;
import com.erdemtsynduev.profitcoin.db.provider.CoinContentProvider;
import com.erdemtsynduev.profitcoin.db.tables.CoinTable;
import com.erdemtsynduev.profitcoin.db.tables.FavoriteTable;
import com.erdemtsynduev.profitcoin.db.utils.GsonHolder;
import com.erdemtsynduev.profitcoin.network.model.favoritecoin.FavoriteCoin;
import com.erdemtsynduev.profitcoin.network.model.listallcryptocurrency.Datum;
import com.erdemtsynduev.profitcoin.network.model.listallcryptocurrency.Quote;
import com.erdemtsynduev.profitcoin.screen.portfolio.event.UpdateLikeCoinListEvent;
import com.erdemtsynduev.profitcoin.widget.WidgetService;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

@InjectViewState
public class PortfolioPresenter extends MvpPresenter<PortfolioView> {

    private List<Datum> datumListSaved = new ArrayList<>();
    private List<FavoriteCoin> favoriteCoinsSaved = new ArrayList<>();

    private boolean mIsInLoading;

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();

        subscribeBus();
        loadFavoriteCoinList(false);
    }

    public void loadFavoriteCoinList(boolean isRefreshing) {
        loadData(isRefreshing);
    }

    private void loadData(boolean isRefreshing) {
        if (mIsInLoading) {
            return;
        }
        mIsInLoading = true;

        getViewState().onStartLoading();
        showProgress(isRefreshing);

        getData(isRefreshing);
    }

    private void showProgress(boolean isRefreshing) {
        if (isRefreshing) {
            getViewState().showRefreshing();
        } else {
            getViewState().showListProgress();
        }
    }

    private void hideProgress(boolean isRefreshing) {
        if (isRefreshing) {
            getViewState().hideRefreshing();
        } else {
            getViewState().hideListProgress();
        }
    }

    public void openScreenDetail(Datum datum) {
        getViewState().openScreenDetail(datum);
    }

    public void getData(boolean mIsRefreshing) {
        datumListSaved.clear();
        favoriteCoinsSaved.clear();
        getCoinDataFromDb();
        getCoinFavoriteDataFromDb();
        setDataList(mIsRefreshing);
    }

    private void getCoinDataFromDb() {
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
                datumListSaved.add(datum);
            }
            cursorData.close();
        }
    }

    private void getCoinFavoriteDataFromDb() {
        Cursor cursorData = ExtendApplication.getAppComponent().getContext().getContentResolver()
                .query(CoinContentProvider.URI_FAVORITE_TABLE, null,
                        null, null, null);
        if (cursorData != null && cursorData.getCount() != 0) {
            while (cursorData.moveToNext()) {
                FavoriteCoin favoriteCoin = new FavoriteCoin();
                favoriteCoin.setId(cursorData.getString(cursorData.getColumnIndex(FavoriteTable.COLUMN_ID_FAVORITE)));
                favoriteCoin.setName(cursorData.getString(cursorData.getColumnIndex(FavoriteTable.COLUMN_NAME_FAVORITE)));
                favoriteCoinsSaved.add(favoriteCoin);
            }
            cursorData.close();
        }
    }

    private void setDataList(boolean isRefreshing) {
        List<Datum> datumListFavorite = new ArrayList<>();
        if (!favoriteCoinsSaved.isEmpty() && !datumListSaved.isEmpty()) {
            for (int i = 0; i < datumListSaved.size(); i++) {
                for (int a = 0; a < favoriteCoinsSaved.size(); a++) {
                    if (datumListSaved.get(i).getName().equals(favoriteCoinsSaved.get(a).getName())) {
                        datumListFavorite.add(datumListSaved.get(i));
                    }
                }
            }
        }
        if (datumListFavorite.size() != 0) {
            onLoadingFinish(isRefreshing);
            onLoadingSuccess(datumListFavorite);
        } else {
            onLoadingFinish(isRefreshing);
            onLoadingFailed();
        }
    }

    private void onLoadingFinish(boolean isRefreshing) {
        mIsInLoading = false;

        getViewState().onFinishLoading();

        hideProgress(isRefreshing);
    }

    private void onLoadingSuccess(List<Datum> datumListFavorite) {
        WidgetService.updateWidget(ExtendApplication.getAppComponent().getContext(), datumListFavorite);
        getViewState().showPortfolioList(datumListFavorite);
    }

    private void onLoadingFailed() {
        getViewState().showEmptyPortfolioList();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unsubscribeBus();
    }

    public void subscribeBus() {
        EventBus.getDefault().register(this);
    }

    public void unsubscribeBus() {
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onTableChanged(UpdateLikeCoinListEvent event) {
        loadFavoriteCoinList(false);
    }
}