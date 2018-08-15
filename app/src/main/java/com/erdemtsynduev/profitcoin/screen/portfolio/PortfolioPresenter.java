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

import java.util.ArrayList;
import java.util.List;

@InjectViewState
public class PortfolioPresenter extends MvpPresenter<PortfolioView> {

    private List<Datum> datumListSaved = new ArrayList<>();
    private List<FavoriteCoin> favoriteCoinsSaved = new ArrayList<>();

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        getViewState().showEmptyPortfolioList();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    public void openScreenDetail(Datum datum) {
        getViewState().openScreenDetail(datum);
    }

    public void getData() {
        datumListSaved.clear();
        favoriteCoinsSaved.clear();
        getCoinDataFromDb();
        getCoinFavoriteDataFromDb();
        setDataList();
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
            setDataList();
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

    private void setDataList() {
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
            getViewState().showPortfolioList(datumListFavorite);
        } else {
            getViewState().showEmptyPortfolioList();
        }
    }
}