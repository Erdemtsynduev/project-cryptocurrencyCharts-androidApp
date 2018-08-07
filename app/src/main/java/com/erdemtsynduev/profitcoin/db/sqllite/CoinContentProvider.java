package com.erdemtsynduev.profitcoin.db.sqllite;

import android.support.annotation.NonNull;

import com.erdemtsynduev.profitcoin.db.tables.CoinTable;
import com.erdemtsynduev.profitcoin.db.tables.FavoriteTable;
import com.erdemtsynduev.profitcoin.db.tables.RequestTable;

import ru.arturvasilov.sqlite.core.SQLiteConfig;
import ru.arturvasilov.sqlite.core.SQLiteContentProvider;
import ru.arturvasilov.sqlite.core.SQLiteSchema;

public class CoinContentProvider extends SQLiteContentProvider {

    private static final String DATABASE_NAME = "profitcoin.db";
    private static final String CONTENT_AUTHORITY = "com.erdemtsynduev.profitcoin";

    @Override
    protected void prepareConfig(@NonNull SQLiteConfig config) {
        config.setDatabaseName(DATABASE_NAME);
        config.setAuthority(CONTENT_AUTHORITY);
    }

    @Override
    protected void prepareSchema(@NonNull SQLiteSchema schema) {
        schema.register(CoinTable.TABLE);
        schema.register(RequestTable.TABLE);
        schema.register(FavoriteTable.TABLE);
    }
}