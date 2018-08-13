package com.erdemtsynduev.profitcoin.db.converter;

import android.arch.persistence.room.TypeConverter;

import com.erdemtsynduev.profitcoin.db.utils.GsonHolder;
import com.erdemtsynduev.profitcoin.network.model.listallcryptocurrency.Quote;

public class QuoteConverter {

    @TypeConverter
    public static Quote fromStringToQuote(String value) {
        return GsonHolder.getGson().fromJson(value, Quote.class);
    }

    @TypeConverter
    public static String fromQuoteToString(Quote quote) {
        return GsonHolder.getGson().toJson(quote);
    }
}
