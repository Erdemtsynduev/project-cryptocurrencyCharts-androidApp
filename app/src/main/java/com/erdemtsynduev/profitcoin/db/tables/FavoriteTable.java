package com.erdemtsynduev.profitcoin.db.tables;

import android.content.ContentValues;
import android.database.Cursor;
import android.support.annotation.NonNull;

import com.erdemtsynduev.profitcoin.network.model.favoritecoin.FavoriteCoin;

import org.sqlite.database.sqlite.SQLiteDatabase;

import ru.arturvasilov.sqlite.core.BaseTable;
import ru.arturvasilov.sqlite.core.Table;
import ru.arturvasilov.sqlite.utils.TableBuilder;

public class FavoriteTable extends BaseTable<FavoriteCoin> {

    public static final Table<FavoriteCoin> TABLE = new FavoriteTable();

    public static final String ID = "id";
    public static final String COIN_NAME = "name";

    @Override
    public void onCreate(@NonNull SQLiteDatabase database) {
        TableBuilder.create(this)
                .textColumn(ID)
                .textColumn(COIN_NAME)
                .execute(database);
    }

    @NonNull
    @Override
    public ContentValues toValues(@NonNull FavoriteCoin favoriteCoin) {
        ContentValues values = new ContentValues();
        values.put(ID, favoriteCoin.getId());
        values.put(COIN_NAME, favoriteCoin.getName());
        return values;
    }

    @NonNull
    @Override
    public FavoriteCoin fromCursor(@NonNull Cursor cursor) {
        FavoriteCoin favoriteCoin = new FavoriteCoin();
        favoriteCoin.setId(cursor.getString(cursor.getColumnIndex(ID)));
        favoriteCoin.setName(cursor.getString(cursor.getColumnIndex(COIN_NAME)));

        return favoriteCoin;
    }
}