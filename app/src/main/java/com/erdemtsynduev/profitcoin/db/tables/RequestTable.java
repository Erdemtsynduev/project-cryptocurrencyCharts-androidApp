package com.erdemtsynduev.profitcoin.db.tables;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;
import android.content.ContentValues;

import com.erdemtsynduev.profitcoin.db.converter.RequestStatusConverter;
import com.erdemtsynduev.profitcoin.network.model.request.NetworkRequest;
import com.erdemtsynduev.profitcoin.network.model.request.Request;
import com.erdemtsynduev.profitcoin.network.model.request.RequestStatus;

@Entity(tableName = RequestTable.TABLE_NAME)
public class RequestTable {

    /**
     * The name of the Request table.
     */
    public static final String TABLE_NAME = "request_table";

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_REQUEST = "request";
    public static final String COLUMN_STATUS = "status";
    public static final String COLUMN_ERROR = "error";

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(index = true, name = COLUMN_ID)
    private String id;

    @ColumnInfo(name = COLUMN_REQUEST)
    private String request;

    @TypeConverters({RequestStatusConverter.class})
    @ColumnInfo(name = COLUMN_STATUS)
    private RequestStatus status;

    @ColumnInfo(name = COLUMN_ERROR)
    private String error;

    /**
     * Create a new {@link Request} from the specified {@link ContentValues}.
     *
     * @param values A {@link ContentValues} that at least contain {@link #COLUMN_REQUEST}.
     * @return A newly created {@link Request} instance.
     */
    public static Request fromContentValues(ContentValues values) {
        @NetworkRequest String request = null;
        RequestStatus status = null;
        String error = null;

        if (values.containsKey(COLUMN_REQUEST)) {
            request = values.getAsString(COLUMN_REQUEST);
        }
        if (values.containsKey(COLUMN_STATUS)) {
            status = RequestStatus.valueOf(values.getAsString(COLUMN_STATUS));
        }
        if (values.containsKey(COLUMN_ERROR)) {
            error = values.getAsString(COLUMN_ERROR);
        }
        return new Request(request, status, error);
    }
}