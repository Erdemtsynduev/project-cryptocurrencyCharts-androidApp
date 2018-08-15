package com.erdemtsynduev.profitcoin.screen.coindetail;

import android.net.Uri;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.erdemtsynduev.profitcoin.ExtendApplication;
import com.erdemtsynduev.profitcoin.db.provider.CoinContentProvider;
import com.erdemtsynduev.profitcoin.db.tables.FavoriteTable;
import com.erdemtsynduev.profitcoin.network.model.favoritecoin.FavoriteCoin;

@InjectViewState
public class CoinDetailPresenter extends MvpPresenter<CoinDetailView> {

    public void saveCoinInFavorite(String id, String name) {
        FavoriteCoin favoriteCoin = new FavoriteCoin();
        favoriteCoin.setId(id);
        favoriteCoin.setName(name);

        ExtendApplication.getAppComponent().getContext().getContentResolver().
                insert(CoinContentProvider.URI_FAVORITE_TABLE, FavoriteTable.toContentValues(favoriteCoin));

        showAddFavoriteSuccess();
    }

    public void deleteCoinInFavorite(String idCoin) {
        ExtendApplication.getAppComponent().getContext().getContentResolver().
                delete(Uri.parse(CoinContentProvider.URI_FAVORITE_TABLE + "/" + idCoin), null, null);

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