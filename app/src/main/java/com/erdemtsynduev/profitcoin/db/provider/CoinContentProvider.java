package com.erdemtsynduev.profitcoin.db.provider;

import android.content.ContentProvider;
import android.content.ContentProviderOperation;
import android.content.ContentProviderResult;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.OperationApplicationException;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.erdemtsynduev.profitcoin.db.AppDatabase;
import com.erdemtsynduev.profitcoin.db.tables.CoinTable;
import com.erdemtsynduev.profitcoin.db.tables.CoinTableDao;
import com.erdemtsynduev.profitcoin.db.tables.FavoriteTable;
import com.erdemtsynduev.profitcoin.db.tables.FavoriteTableDao;
import com.erdemtsynduev.profitcoin.db.tables.RequestTable;
import com.erdemtsynduev.profitcoin.db.tables.RequestTableDao;

import java.util.ArrayList;

public class CoinContentProvider extends ContentProvider {

    /**
     * The authority of this content provider.
     */
    public static final String CONTENT_AUTHORITY = "com.erdemtsynduev.profitcoin";

    /**
     * The URI for the table.
     */
    public static final Uri URI_COIN_TABLE = Uri.parse("content://" + CONTENT_AUTHORITY + "/" + CoinTable.TABLE_NAME);
    public static final Uri URI_FAVORITE_TABLE = Uri.parse("content://" + CONTENT_AUTHORITY + "/" + FavoriteTable.TABLE_NAME);
    public static final Uri URI_REQUEST_TABLE = Uri.parse("content://" + CONTENT_AUTHORITY + "/" + RequestTable.TABLE_NAME);

    private static final int CODE_COIN_TABLE_DIR = 1;
    private static final int CODE_COIN_TABLE_ITEM = 2;
    private static final int CODE_FAVORITE_TABLE_DIR = 3;
    private static final int CODE_FAVORITE_TABLE_ITEM = 4;
    private static final int CODE_REQUEST_TABLE_DIR = 5;
    private static final int CODE_REQUEST_TABLE_ITEM = 6;

    /**
     * The URI matcher.
     */
    private static final UriMatcher MATCHER = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        MATCHER.addURI(CONTENT_AUTHORITY, CoinTable.TABLE_NAME, CODE_COIN_TABLE_DIR);
        MATCHER.addURI(CONTENT_AUTHORITY, CoinTable.TABLE_NAME + "/*", CODE_COIN_TABLE_ITEM);
        MATCHER.addURI(CONTENT_AUTHORITY, FavoriteTable.TABLE_NAME, CODE_FAVORITE_TABLE_DIR);
        MATCHER.addURI(CONTENT_AUTHORITY, FavoriteTable.TABLE_NAME + "/*", CODE_FAVORITE_TABLE_ITEM);
        MATCHER.addURI(CONTENT_AUTHORITY, RequestTable.TABLE_NAME, CODE_REQUEST_TABLE_DIR);
        MATCHER.addURI(CONTENT_AUTHORITY, RequestTable.TABLE_NAME + "/*", CODE_REQUEST_TABLE_ITEM);
    }

    @Override
    public boolean onCreate() {
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection,
                        @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        final Context context = getContext();
        if (context == null) {
            return null;
        }
        final Cursor cursor;
        switch (MATCHER.match(uri)) {
            case CODE_COIN_TABLE_DIR:
            case CODE_COIN_TABLE_ITEM:
                CoinTableDao coinTableDao = AppDatabase.getInstance(context).coinTableDao();
                if (MATCHER.match(uri) == CODE_COIN_TABLE_DIR) {
                    cursor = coinTableDao.selectAll();
                } else {
                    cursor = coinTableDao.selectById(ContentUris.parseId(uri));
                }
                cursor.setNotificationUri(context.getContentResolver(), uri);
                return cursor;
            case CODE_FAVORITE_TABLE_DIR:
            case CODE_FAVORITE_TABLE_ITEM:
                FavoriteTableDao favoriteTableDao = AppDatabase.getInstance(context).favoriteTableDao();
                if (MATCHER.match(uri) == CODE_FAVORITE_TABLE_DIR) {
                    cursor = favoriteTableDao.selectAll();
                } else {
                    cursor = favoriteTableDao.selectById(ContentUris.parseId(uri));
                }
                cursor.setNotificationUri(context.getContentResolver(), uri);
                return cursor;
            case CODE_REQUEST_TABLE_DIR:
            case CODE_REQUEST_TABLE_ITEM:
                RequestTableDao requestTableDao = AppDatabase.getInstance(context).requestTableDao();
                if (MATCHER.match(uri) == CODE_REQUEST_TABLE_DIR) {
                    cursor = requestTableDao.selectAll();
                } else {
                    cursor = requestTableDao.selectById(ContentUris.parseId(uri));
                }
                cursor.setNotificationUri(context.getContentResolver(), uri);
                return cursor;
            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        switch (MATCHER.match(uri)) {
            case CODE_COIN_TABLE_DIR:
                return "vnd.android.cursor.dir/" + CONTENT_AUTHORITY + "." + CoinTable.TABLE_NAME;
            case CODE_COIN_TABLE_ITEM:
                return "vnd.android.cursor.item/" + CONTENT_AUTHORITY + "." + CoinTable.TABLE_NAME;
            case CODE_FAVORITE_TABLE_DIR:
                return "vnd.android.cursor.dir/" + CONTENT_AUTHORITY + "." + FavoriteTable.TABLE_NAME;
            case CODE_FAVORITE_TABLE_ITEM:
                return "vnd.android.cursor.item/" + CONTENT_AUTHORITY + "." + FavoriteTable.TABLE_NAME;
            case CODE_REQUEST_TABLE_DIR:
                return "vnd.android.cursor.dir/" + CONTENT_AUTHORITY + "." + RequestTable.TABLE_NAME;
            case CODE_REQUEST_TABLE_ITEM:
                return "vnd.android.cursor.item/" + CONTENT_AUTHORITY + "." + RequestTable.TABLE_NAME;
            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        final Context context = getContext();
        if (context == null) {
            return null;
        }
        if (values == null) {
            return null;
        }
        final long id;
        switch (MATCHER.match(uri)) {
            case CODE_COIN_TABLE_DIR:
                id = AppDatabase.getInstance(context).coinTableDao()
                        .insert(CoinTable.fromContentValues(values));
                context.getContentResolver().notifyChange(uri, null);
                return ContentUris.withAppendedId(uri, id);
            case CODE_FAVORITE_TABLE_DIR:
                id = AppDatabase.getInstance(context).favoriteTableDao()
                        .insert(FavoriteTable.fromContentValues(values));
                context.getContentResolver().notifyChange(uri, null);
                return ContentUris.withAppendedId(uri, id);
            case CODE_REQUEST_TABLE_DIR:
                id = AppDatabase.getInstance(context).requestTableDao()
                        .insert(RequestTable.fromContentValues(values));
                context.getContentResolver().notifyChange(uri, null);
                return ContentUris.withAppendedId(uri, id);
            case CODE_COIN_TABLE_ITEM:
            case CODE_FAVORITE_TABLE_ITEM:
            case CODE_REQUEST_TABLE_ITEM:
                throw new IllegalArgumentException("Invalid URI, cannot insert with ID: " + uri);
            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection,
                      @Nullable String[] selectionArgs) {
        final Context context = getContext();
        if (context == null) {
            return 0;
        }
        final int count;
        switch (MATCHER.match(uri)) {
            case CODE_COIN_TABLE_DIR:
            case CODE_FAVORITE_TABLE_DIR:
            case CODE_REQUEST_TABLE_DIR:
                throw new IllegalArgumentException("Invalid URI, cannot update without ID" + uri);
            case CODE_COIN_TABLE_ITEM:
                count = AppDatabase.getInstance(context).coinTableDao()
                        .deleteById(ContentUris.parseId(uri));
                context.getContentResolver().notifyChange(uri, null);
                return count;
            case CODE_FAVORITE_TABLE_ITEM:
                count = AppDatabase.getInstance(context).favoriteTableDao()
                        .deleteById(ContentUris.parseId(uri));
                context.getContentResolver().notifyChange(uri, null);
                return count;
            case CODE_REQUEST_TABLE_ITEM:
                count = AppDatabase.getInstance(context).requestTableDao()
                        .deleteById(ContentUris.parseId(uri));
                context.getContentResolver().notifyChange(uri, null);
                return count;
            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection,
                      @Nullable String[] selectionArgs) {
        final Context context = getContext();
        if (context == null) {
            return 0;
        }
        if (values == null) {
            return 0;
        }
        final int count;
        switch (MATCHER.match(uri)) {
            case CODE_COIN_TABLE_DIR:
            case CODE_FAVORITE_TABLE_DIR:
            case CODE_REQUEST_TABLE_DIR:
                throw new IllegalArgumentException("Invalid URI, cannot update without ID" + uri);
            case CODE_COIN_TABLE_ITEM:
                CoinTable coinTable = CoinTable.fromContentValues(values);
                count = AppDatabase.getInstance(context).coinTableDao().update(coinTable);
                context.getContentResolver().notifyChange(uri, null);
                return count;
            case CODE_FAVORITE_TABLE_ITEM:
                FavoriteTable favoriteTable = FavoriteTable.fromContentValues(values);
                count = AppDatabase.getInstance(context).favoriteTableDao().update(favoriteTable);
                context.getContentResolver().notifyChange(uri, null);
                return count;
            case CODE_REQUEST_TABLE_ITEM:
                RequestTable requestTable = RequestTable.fromContentValues(values);
                count = AppDatabase.getInstance(context).requestTableDao().update(requestTable);
                context.getContentResolver().notifyChange(uri, null);
                return count;
            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }
    }

    @NonNull
    @Override
    public ContentProviderResult[] applyBatch(
            @NonNull ArrayList<ContentProviderOperation> operations)
            throws OperationApplicationException {
        final Context context = getContext();
        if (context == null) {
            return new ContentProviderResult[0];
        }
        final AppDatabase database = AppDatabase.getInstance(context);
        database.beginTransaction();
        try {
            final ContentProviderResult[] result = super.applyBatch(operations);
            database.setTransactionSuccessful();
            return result;
        } finally {
            database.endTransaction();
        }
    }

    @Override
    public int bulkInsert(@NonNull Uri uri, @NonNull ContentValues[] valuesArray) {
        final Context context = getContext();
        if (context == null) {
            return 0;
        }
        final AppDatabase database;
        switch (MATCHER.match(uri)) {
            case CODE_COIN_TABLE_DIR:
                database = AppDatabase.getInstance(context);
                CoinTable[] coinTables = new CoinTable[valuesArray.length];
                for (int i = 0; i < valuesArray.length; i++) {
                    coinTables[i] = CoinTable.fromContentValues(valuesArray[i]);
                }
                return database.coinTableDao().insertAll(coinTables).length;
            case CODE_FAVORITE_TABLE_DIR:
                database = AppDatabase.getInstance(context);
                FavoriteTable[] favoriteTables = new FavoriteTable[valuesArray.length];
                for (int i = 0; i < valuesArray.length; i++) {
                    favoriteTables[i] = FavoriteTable.fromContentValues(valuesArray[i]);
                }
                return database.favoriteTableDao().insertAll(favoriteTables).length;
            case CODE_REQUEST_TABLE_DIR:
                database = AppDatabase.getInstance(context);
                RequestTable[] requestTables = new RequestTable[valuesArray.length];
                for (int i = 0; i < valuesArray.length; i++) {
                    requestTables[i] = RequestTable.fromContentValues(valuesArray[i]);
                }
                return database.requestTableDao().insertAll(requestTables).length;
            case CODE_COIN_TABLE_ITEM:
            case CODE_FAVORITE_TABLE_ITEM:
            case CODE_REQUEST_TABLE_ITEM:
                throw new IllegalArgumentException("Invalid URI, cannot insert with ID: " + uri);
            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }
    }
}