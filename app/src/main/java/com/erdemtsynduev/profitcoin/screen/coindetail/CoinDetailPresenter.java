package com.erdemtsynduev.profitcoin.screen.coindetail;

import android.net.Uri;
import android.os.Bundle;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.erdemtsynduev.profitcoin.ExtendApplication;
import com.erdemtsynduev.profitcoin.db.provider.CoinContentProvider;
import com.erdemtsynduev.profitcoin.db.tables.FavoriteTable;
import com.erdemtsynduev.profitcoin.network.model.favoritecoin.FavoriteCoin;
import com.erdemtsynduev.profitcoin.network.model.listallcryptocurrency.Datum;
import com.erdemtsynduev.profitcoin.screen.portfolio.event.UpdateLikeCoinListEvent;

import org.greenrobot.eventbus.EventBus;

@InjectViewState
public class CoinDetailPresenter extends MvpPresenter<CoinDetailView> {

    private Datum mDatum;
    private boolean isFavoriteCoin;

    public static final String KEY_DATUM_LOADER = "id";

    public CoinDetailPresenter(Datum datum) {
        super();

        this.mDatum = datum;
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();

        if (mDatum != null) {
            getViewState().showChartsDetail(mDatum);
        } else {
            getViewState().showErrorAddFavorite();
        }

        updateLikes();
        getFavoriteDataFromDb();
    }

    public void updateLikes() {
        if (isFavoriteCoin) {
            getViewState().showAddFavoriteSuccess();
        } else {
            getViewState().showDeleteFavoriteSuccess();
        }
    }

    public void getFavoriteDataFromDb() {
        if (mDatum == null) {
            return;
        }
        Bundle bundle = new Bundle();
        bundle.putString(KEY_DATUM_LOADER, mDatum.getId());
        getViewState().getFavoriteDataFromDb(bundle);
    }

    public boolean isFavoriteCoin() {
        return isFavoriteCoin;
    }

    public void setFavoriteCoin(boolean favoriteCoin) {
        isFavoriteCoin = favoriteCoin;
        updateLikes();
    }

    public void saveFavoriteCoinInDb() {
        if (mDatum == null) {
            return;
        }
        FavoriteCoin favoriteCoin = new FavoriteCoin(mDatum.getId(), mDatum.getName());

        ExtendApplication.getAppComponent().getContext().getContentResolver().
                insert(CoinContentProvider.URI_FAVORITE_TABLE, FavoriteTable.toContentValues(favoriteCoin));

        isFavoriteCoin = true;
        sendBus();
        showAddFavoriteSuccess();
    }

    public void deleteFavoriteCoinInDb() {
        if (mDatum == null) {
            return;
        }

        ExtendApplication.getAppComponent().getContext().getContentResolver().
                delete(Uri.parse(CoinContentProvider.URI_FAVORITE_TABLE + "/" + mDatum.getId()), null, null);

        isFavoriteCoin = false;
        sendBus();
        deleteFavoriteSuccess();
    }

    private void sendBus() {
        EventBus.getDefault().post(new UpdateLikeCoinListEvent());
    }

    public void showAddFavoriteSuccess() {
        getViewState().showAddFavoriteSuccess();
    }

    public void deleteFavoriteSuccess() {
        getViewState().showDeleteFavoriteSuccess();
    }

    public void onClickButtonBack() {
        getViewState().onBackPressed();
    }
}