package com.erdemtsynduev.profitcoin.db.tables;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;
import android.database.Cursor;

import com.erdemtsynduev.profitcoin.network.model.listallcryptocurrency.Datum;

/**
 * Data access object for CoinTable.
 */
@Dao
public interface CoinTableDao {

    /**
     * Select a altcoin by the ID.
     *
     * @param coinId The row ID.
     * @return A {@link Cursor} of the selected altcoin.
     */
    @Query("SELECT * FROM " + CoinTable.TABLE_NAME + " WHERE " + CoinTable.COLUMN_ID + " = :coinId")
    Cursor selectById(long coinId);

    /**
     * Select all altcoin.
     *
     * @return A {@link Cursor} of all the coin in the table.
     */
    @Query("SELECT * FROM " + CoinTable.TABLE_NAME)
    Cursor selectAll();

    /**
     * Select a altcoin by the name.
     *
     * @param altCoinName The name altcoin.
     * @return A {@link Cursor} of the selected altcoin.
     */
    @Query("SELECT * FROM " + CoinTable.TABLE_NAME + " WHERE " +
            CoinTable.COLUMN_NAME + " LIKE  :altCoinName ")
    Cursor selectByName(String altCoinName);

    /**
     * Counts the number of altcoin in the table.
     *
     * @return The number of altcoin.
     */
    @Query("SELECT COUNT(*) FROM " + CoinTable.TABLE_NAME)
    int count();

    /**
     * Inserts a altcoin into the table.
     *
     * @param datum A new Datum.
     * @return The row ID of the newly inserted altcoin in table.
     */
    @Insert
    long insert(Datum datum);

    /**
     * Inserts multiple altcoin into the database
     *
     * @param datum An array of new altcoin.
     * @return The row IDs of the newly inserted altcoin.
     */
    @Insert
    long[] insertAll(Datum[] datum);

    /**
     * Delete a altcoin.
     *
     * @param datum The data with altcoin.
     * @return A number of altcoin deleted. This should always be {@code 1}.
     */
    @Delete
    int delete(Datum datum);

    /**
     * Delete a altcoin by the name.
     *
     * @param altCoinName The name altCoin.
     * @return A number of altcoin deleted. This should always be {@code 1}.
     */
    @Query("DELETE FROM " + CoinTable.TABLE_NAME + " WHERE " +
            CoinTable.COLUMN_NAME + " LIKE  :altCoinName ")
    int deleteByName(String altCoinName);

    /**
     * Delete a altcoin by the id.
     *
     * @param altCoinId The row altcoin id.
     * @return A number of altcoin deleted. This should always be {@code 1}.
     */
    @Query("DELETE FROM " + CoinTable.TABLE_NAME + " WHERE " + CoinTable.COLUMN_ID + " = :altCoinId")
    int deleteById(long altCoinId);

    /**
     * Update the datum. The cheese is identified by the row ID.
     *
     * @param datum The altcoin to update.
     * @return A number of datum updated. This should always be {@code 1}.
     */
    @Update
    int update(Datum datum);
}
