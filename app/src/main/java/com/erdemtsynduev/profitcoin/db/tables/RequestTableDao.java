package com.erdemtsynduev.profitcoin.db.tables;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;
import android.database.Cursor;

import com.erdemtsynduev.profitcoin.network.model.request.Request;

/**
 * Data access object for RequestTable.
 */
@Dao
public interface RequestTableDao {

    /**
     * Select a request by the ID.
     *
     * @param requestId The row ID.
     * @return A {@link Cursor} of the selected request.
     */
    @Query("SELECT * FROM " + RequestTable.TABLE_NAME + " WHERE " + RequestTable.COLUMN_ID + " = :requestId")
    Cursor selectById(long requestId);

    /**
     * Select all request.
     *
     * @return A {@link Cursor} of all the request in the table.
     */
    @Query("SELECT * FROM " + RequestTable.TABLE_NAME)
    Cursor selectAll();

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
     * @param request A new request.
     * @return The row ID of the newly inserted request.
     */
    @Insert
    long insert(Request request);

    /**
     * Inserts multiple request into the database
     *
     * @param request An array of new request.
     * @return The row IDs of the newly inserted request.
     */
    @Insert
    long[] insertAll(Request[] request);

    /**
     * Delete a request.
     *
     * @param request The request.
     * @return A number of request deleted. This should always be {@code 1}.
     */
    @Delete
    int delete(Request request);

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
     * @param request The request to update.
     * @return A number of request updated. This should always be {@code 1}.
     */
    @Update
    int update(Request request);
}
