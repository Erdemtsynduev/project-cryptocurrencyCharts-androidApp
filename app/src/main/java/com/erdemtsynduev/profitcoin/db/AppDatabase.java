package com.erdemtsynduev.profitcoin.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.erdemtsynduev.profitcoin.db.tables.CoinTable;
import com.erdemtsynduev.profitcoin.db.tables.CoinTableDao;
import com.erdemtsynduev.profitcoin.db.tables.FavoriteTable;
import com.erdemtsynduev.profitcoin.db.tables.FavoriteTableDao;

/**
 * The Room database.
 */
@Database(entities = {CoinTable.class, FavoriteTable.class}, version = 3)
public abstract class AppDatabase extends RoomDatabase {

    /**
     * @return The DAO for the Coin table.
     */
    @SuppressWarnings("WeakerAccess")
    public abstract CoinTableDao coinTableDao();

    /**
     * @return The DAO for the Favorite table.
     */
    @SuppressWarnings("WeakerAccess")
    public abstract FavoriteTableDao favoriteTableDao();

    /** The only instance */
    private static AppDatabase sInstance;

    /**
     * Gets the singleton instance of SampleDatabase.
     *
     * @param context The context.
     * @return The singleton instance of SampleDatabase.
     */
    public static synchronized AppDatabase getInstance(Context context) {
        if (sInstance == null) {
            sInstance = Room
                    .databaseBuilder(context.getApplicationContext(), AppDatabase.class, "profitcoin")
                    .allowMainThreadQueries().build();
        }
        return sInstance;
    }
}