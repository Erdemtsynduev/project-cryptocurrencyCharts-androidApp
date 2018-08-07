package com.erdemtsynduev.profitcoin.screen.portfolio;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.erdemtsynduev.profitcoin.ExtendApplication;
import com.erdemtsynduev.profitcoin.db.tables.CoinTable;
import com.erdemtsynduev.profitcoin.db.tables.FavoriteTable;
import com.erdemtsynduev.profitcoin.network.model.favoritecoin.FavoriteCoin;
import com.erdemtsynduev.profitcoin.network.model.listallcryptocurrency.Datum;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import ru.arturvasilov.sqlite.core.SQLite;
import ru.arturvasilov.sqlite.core.Where;
import ru.arturvasilov.sqlite.rx.RxSQLite;

@InjectViewState
public class PortfolioPresenter extends MvpPresenter<PortfolioView> {

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();

        getViewState().showEmptyPortfolioList();
    }

    public void openScreenDetail(Datum datum) {
        getViewState().openScreenDetail(datum);
    }

    public void getData() {
        if (ExtendApplication.getIsFirstRun()) {
            getDataFromDb();
        } else {
            ExtendApplication.setIsFirstRun(true);
            setDataList(null);
        }
    }

    private void getDataFromDb() {
        RxSQLite.get().query(CoinTable.TABLE)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(coin -> {
                    setDataList(coin);
                }, throwable -> {
                    throwable.printStackTrace();
                });
    }

    private void setDataList(List<Datum> dataList) {
        List<FavoriteCoin> savedFavoriteCoin = SQLite.get().query(FavoriteTable.TABLE);

        List<Datum> datumList = new ArrayList<>();
        if (savedFavoriteCoin != null && !savedFavoriteCoin.isEmpty()) {
            for (int i = 0; i < dataList.size(); i++) {
                for (int a = 0; a < savedFavoriteCoin.size(); a++) {
                    if (dataList.get(i).getName().equals(savedFavoriteCoin.get(a).getName())) {
                        datumList.add(dataList.get(i));
                    }
                }
            }
        }

        if (!datumList.isEmpty()) {
            getViewState().showPortfolioList(datumList);
        } else {
            getViewState().showEmptyPortfolioList();
        }
    }
}