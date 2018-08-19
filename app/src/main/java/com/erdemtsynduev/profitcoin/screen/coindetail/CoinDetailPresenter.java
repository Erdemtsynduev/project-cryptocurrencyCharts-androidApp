package com.erdemtsynduev.profitcoin.screen.coindetail;

import android.database.Cursor;
import android.net.Uri;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.erdemtsynduev.profitcoin.ExtendApplication;
import com.erdemtsynduev.profitcoin.db.provider.CoinContentProvider;
import com.erdemtsynduev.profitcoin.db.tables.FavoriteTable;
import com.erdemtsynduev.profitcoin.network.model.favoritecoin.FavoriteCoin;
import com.erdemtsynduev.profitcoin.network.model.listallcryptocurrency.Datum;
import com.erdemtsynduev.profitcoin.screen.portfolio.event.UpdateLikeCoinListEvent;
import com.erdemtsynduev.profitcoin.utils.AppWidgetUtils;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import io.paperdb.Paper;

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

        loadCoinListFavorite();
    }

    public void loadCoinListFavorite() {
        loadData();
    }

    private void loadData() {
        Cursor cursorData = ExtendApplication.getAppComponent().getContext().getContentResolver()
                .query(CoinContentProvider.URI_FAVORITE_TABLE, null,
                        null, null, null);

        FavoriteCoin savedFavoriteCoin = null;
        if (cursorData != null && cursorData.getCount() != 0) {
            while (cursorData.moveToNext()) {
                FavoriteCoin favoriteCoin = new FavoriteCoin();
                favoriteCoin.setId(cursorData.getString(cursorData.getColumnIndex(FavoriteTable.COLUMN_ID_FAVORITE)));
                favoriteCoin.setName(cursorData.getString(cursorData.getColumnIndex(FavoriteTable.COLUMN_NAME_FAVORITE)));
                savedFavoriteCoin = favoriteCoin;
            }
            cursorData.close();
        }

        if (savedFavoriteCoin != null) {
            setFavoriteCoin(true);
        } else {
            setFavoriteCoin(false);
        }
    }

    public void updateLikes() {
        if (isFavoriteCoin) {
            getViewState().showAddFavoriteSuccess();
        } else {
            getViewState().showDeleteFavoriteSuccess();
        }
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
        changeDataInPaperDb();
        AppWidgetUtils.update(ExtendApplication.getAppComponent().getContext());
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

        changeDataInPaperDb();
        AppWidgetUtils.update(ExtendApplication.getAppComponent().getContext());
        sendBus();
        deleteFavoriteSuccess();
    }

    private void changeDataInPaperDb() {
        List<Datum> datumListFromPaper = Paper.book().read("datumList");

        if (datumListFromPaper != null && !datumListFromPaper.isEmpty()) {
            for (int i = 0; i < datumListFromPaper.size(); i++) {
                if (datumListFromPaper.get(i).getName().equals(mDatum.getName())) {
                    datumListFromPaper.remove(i);
                }
            }
            if (isFavoriteCoin) {
                datumListFromPaper.add(mDatum);
            }

            Paper.book().write("datumList", datumListFromPaper);
        }
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