package com.erdemtsynduev.profitcoin.db.tables;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.content.ContentValues;

import com.erdemtsynduev.profitcoin.network.model.request.Request;

@Entity(tableName = RequestTable.TABLE_NAME)
public class RequestTable {

    /**
     * The name of the Request table.
     */
    public static final String TABLE_NAME = "request_table";

    public static final String COLUMN_ID = "id_request";
    public static final String COLUMN_REQUEST = "request";
    public static final String COLUMN_STATUS = "status";
    public static final String COLUMN_ERROR = "error";

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(index = true, name = COLUMN_ID)
    private long idRequest;

    @ColumnInfo(name = COLUMN_REQUEST)
    private String request;

    @ColumnInfo(name = COLUMN_STATUS)
    private String status;

    @ColumnInfo(name = COLUMN_ERROR)
    private String error;

    public long getIdRequest() {
        return idRequest;
    }

    public void setIdRequest(long idRequest) {
        this.idRequest = idRequest;
    }

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    /**
     * Create a new {@link RequestTable} from the specified {@link ContentValues}.
     *
     * @param values A {@link ContentValues} that at least contain {@link #COLUMN_REQUEST}.
     * @return A newly created {@link RequestTable} instance.
     */
    public static RequestTable fromContentValues(ContentValues values) {
        final RequestTable requestTable = new RequestTable();

        if (values.containsKey(COLUMN_ID)) {
            requestTable.setIdRequest(values.getAsLong(COLUMN_ID));
        }
        if (values.containsKey(COLUMN_REQUEST)) {
            requestTable.setRequest(values.getAsString(COLUMN_REQUEST));
        }
        if (values.containsKey(COLUMN_STATUS)) {
            requestTable.setStatus(values.getAsString(COLUMN_STATUS));
        }
        if (values.containsKey(COLUMN_ERROR)) {
            requestTable.setError(values.getAsString(COLUMN_ERROR));
        }
        return requestTable;
    }

    /**
     * Create a new {@link ContentValues} from the specified {@link Request}.
     *
     * @param request A {@link Request} that at least contain {@link #COLUMN_ID}.
     * @return A newly created {@link ContentValues} instance.
     */
    public static ContentValues toContentValues(Request request) {
        final ContentValues infoValues = new ContentValues();

        if (request.getIdRequest() != null && !request.getIdRequest().isEmpty()) {
            infoValues.put(COLUMN_ID, request.getIdRequest());
        }
        if (request.getRequest() != null && !request.getRequest().isEmpty()) {
            infoValues.put(COLUMN_REQUEST, request.getRequest());
        }
        if (request.getStatus() != null) {
            infoValues.put(COLUMN_STATUS, request.getStatus().name());
        }
        if (request.getError() != null) {
            infoValues.put(COLUMN_ERROR, request.getError());
        }
        return infoValues;
    }
}