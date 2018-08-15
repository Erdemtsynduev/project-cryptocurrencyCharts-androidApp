package com.erdemtsynduev.profitcoin.db.tables;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;
import android.database.Cursor;

/**
 * Data access object for RequestTable.
 */
@Dao
public interface RequestTableDao {

    /**
     * Select all request.
     *
     * @return A {@link Cursor} of all the request in the table.
     */
    @Query("SELECT * FROM " + RequestTable.TABLE_NAME)
    Cursor selectAll();

    /**
     * Select a request by the ID.
     *
     * @param requestId The row ID.
     * @return A {@link Cursor} of the selected request
     */
    @Query("SELECT * FROM " + RequestTable.TABLE_NAME + " WHERE " + RequestTable.COLUMN_ID + " = :requestId")
    Cursor selectById(long requestId);

    /**
     * Select a request by the nameRequest.
     *
     * @param requestName The request.
     * @return A {@link Cursor} of the selected request.
     */
    @Query("SELECT * FROM " + RequestTable.TABLE_NAME + " WHERE " +
            RequestTable.COLUMN_REQUEST + " LIKE  :requestName ")
    Cursor selectByName(String requestName);

    /**
     * Counts the number of request in the table.
     *
     * @return The number of request.
     */
    @Query("SELECT COUNT(*) FROM " + RequestTable.TABLE_NAME)
    int count();

    /**
     * Inserts a request into the table.
     *
     * @param requestTable A new request.
     * @return The row ID of the newly inserted request.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insert(RequestTable requestTable);

    /**
     * Inserts multiple request into the database
     *
     * @param requestTables An array of new request.
     * @return The row IDs of the newly inserted request.
     */
    @Insert
    long[] insertAll(RequestTable[] requestTables);

    /**
     * Delete a request.
     *
     * @param requestTable The request.
     * @return A number of request deleted. This should always be {@code 1}.
     */
    @Delete
    int delete(RequestTable requestTable);

    /**
     * Delete a request by the name.
     *
     * @param requestName The request name.
     * @return A number of request deleted. This should always be {@code 1}.
     */
    @Query("DELETE FROM " + RequestTable.TABLE_NAME + " WHERE " +
            RequestTable.COLUMN_REQUEST + " LIKE  :requestName ")
    int deleteByName(String requestName);

    /**
     * Delete a request by the id.
     *
     * @param requestId The row request id.
     * @return A number of request deleted. This should always be {@code 1}.
     */
    @Query("DELETE FROM " + RequestTable.TABLE_NAME + " WHERE " + RequestTable.COLUMN_ID + " = :requestId")
    int deleteById(long requestId);

    /**
     * Update the request. The cheese is identified by the row ID.
     *
     * @param requestTable The request to update.
     * @return A number of request updated. This should always be {@code 1}.
     */
    @Update
    int update(RequestTable requestTable);
}
