package com.erdemtsynduev.profitcoin.db.converter;

import android.arch.persistence.room.TypeConverter;

import com.erdemtsynduev.profitcoin.network.model.request.RequestStatus;

public class RequestStatusConverter {

    @TypeConverter
    public static RequestStatus fromStringToRequestStatus(String value) {
        return RequestStatus.valueOf(value);
    }

    @TypeConverter
    public static String fromRequestStatusToString(RequestStatus requestStatus) {
        return requestStatus.name();
    }
}
