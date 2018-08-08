package com.erdemtsynduev.profitcoin.screen.coindetail;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.erdemtsynduev.profitcoin.db.tables.FavoriteTable;
import com.erdemtsynduev.profitcoin.network.model.favoritecoin.FavoriteCoin;

import ru.arturvasilov.sqlite.core.SQLite;
import ru.arturvasilov.sqlite.core.Where;

@InjectViewState
public class CoinDetailPresenter extends MvpPresenter<CoinDetailView> {

    public void saveCoinInFavorite(String id, String name) {
        FavoriteCoin favoriteCoin = new FavoriteCoin();
        favoriteCoin.setId(id);
        favoriteCoin.setName(name);
        SQLite.get().insert(FavoriteTable.TABLE, favoriteCoin);
        SQLite.get().notifyTableChanged(FavoriteTable.TABLE);

        showAddFavoriteSuccess();
    }

    public void deleteCoinInFavorite(String nameCoin) {
        Where where = Where.create().equalTo(FavoriteTable.COIN_NAME_FAVORITE, nameCoin);
        SQLite.get().delete(FavoriteTable.TABLE, where);
        SQLite.get().notifyTableChanged(FavoriteTable.TABLE);

        deleteFavoriteSuccess();
    }

    public void showAddFavoriteSuccess() {
        getViewState().showAddFavoriteSuccess();
    }

    public void deleteFavoriteSuccess() {
        getViewState().deleteFavoriteSuccess();
    }

    public void showErrorAddFavorite() {
        getViewState().showErrorAddFavorite();
    }

    public void showChartsDetail() {
        getViewState().showChartsDetail();
    }
}