package com.erdemtsynduev.profitcoin.db.tables;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;
import android.database.Cursor;

import com.erdemtsynduev.profitcoin.network.model.favoritecoin.FavoriteCoin;

/**
 * Data access object for FavoriteTable.
 */
@Dao
public interface FavoriteTableDao {

    /**
     * Select a favorite altcoin by the ID.
     *
     * @param favoriteId The row ID.
     * @return A {@link Cursor} of the selected favorite altcoin
     */
    @Query("SELECT * FROM " + FavoriteTable.TABLE_NAME + " WHERE " + FavoriteTable.COLUMN_ID_FAVORITE + " = :favoriteId")
    Cursor selectById(long favoriteId);

    /**
     * Select all favorite altcoin.
     *
     * @return A {@link Cursor} of all the favorite altcoin in the table.
     */
    @Query("SELECT * FROM " + FavoriteTable.TABLE_NAME)
    Cursor selectAll();

    /**
     * Select a favorite altcoin by the name.
     *
     * @param altCoinName The altcoin name.
     * @return A {@link Cursor} of the selected favorite altcoin.
     */
    @Query("SELECT * FROM " + FavoriteTable.TABLE_NAME + " WHERE " +
            FavoriteTable.COLUMN_NAME_FAVORITE + " LIKE  :altCoinName ")
    Cursor selectByName(String altCoinName);

    /**
     * Counts the number of favoriteMovies in the table.
     *
     * @return The number of favoriteMovies.
     */
    @Query("SELECT COUNT(*) FROM " + FavoriteTable.TABLE_NAME)
    int count();

    /**
     * Inserts a FavoriteCoin into the table.
     *
     * @param favoriteCoin A new favorite altcoin.
     * @return The row ID of the newly inserted favoriteCoin.
     */
    @Insert
    long insert(FavoriteCoin favoriteCoin);

    /**
     * Inserts multiple FavoriteCoin into the database
     *
     * @param favoriteCoin An array of new favorite altcoin.
     * @return The row IDs of the newly inserted FavoriteCoin.
     */
    @Insert
    long[] insertAll(FavoriteCoin[] favoriteCoin);

    /**
     * Delete a FavoriteCoin.
     *
     * @param favoriteCoin The favoriteCoin.
     * @return A number of favoriteCoin deleted. This should always be {@code 1}.
     */
    @Delete
    int delete(FavoriteCoin favoriteCoin);

    /**
     * Delete a favoriteCoin by the name.
     *
     * @param altCoinName The altcoin name.
     * @return A number of favoriteCoin deleted. This should always be {@code 1}.
     */
    @Query("DELETE FROM " + FavoriteTable.TABLE_NAME + " WHERE " +
            FavoriteTable.COLUMN_NAME_FAVORITE + " LIKE  :altCoinName ")
    int deleteByName(String altCoinName);

    /**
     * Delete a favoriteCoin by the id.
     *
     * @param altCoinId The row altcoin id.
     * @return A number of favorite altcoin deleted. This should always be {@code 1}.
     */
    @Query("DELETE FROM " + FavoriteTable.TABLE_NAME + " WHERE " + FavoriteTable.COLUMN_ID_FAVORITE + " = :altCoinId")
    int deleteById(long altCoinId);

    /**
     * Update the favoriteCoin. The cheese is identified by the row ID.
     *
     * @param favoriteCoin The FavoriteCoin to update.
     * @return A number of favoriteCoin updated. This should always be {@code 1}.
     */
    @Update
    int update(FavoriteCoin favoriteCoin);
}
