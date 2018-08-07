package com.erdemtsynduev.profitcoin.db.tables;

import android.content.ContentValues;
import android.database.Cursor;
import android.support.annotation.NonNull;

import com.erdemtsynduev.profitcoin.db.GsonHolder;
import com.erdemtsynduev.profitcoin.network.model.listallcryptocurrency.Datum;
import com.erdemtsynduev.profitcoin.network.model.listallcryptocurrency.Quote;

import org.sqlite.database.sqlite.SQLiteDatabase;

import ru.arturvasilov.sqlite.core.BaseTable;
import ru.arturvasilov.sqlite.core.Table;
import ru.arturvasilov.sqlite.utils.TableBuilder;

public class CoinTable extends BaseTable<Datum> {

    public static final Table<Datum> TABLE = new CoinTable();

    public static final String ID = "id";
    public static final String COIN_NAME = "name";
    public static final String SYMBOL = "symbol";
    public static final String SLUG = "slug";
    public static final String CMC_RANK = "cmc_rank";
    public static final String NUM_MARKETS = "num_markets";
    public static final String CIRCULATING_SUPPLY = "circulating_supply";
    public static final String TOTAL_SUPPLY = "total_supply";
    public static final String MAX_SUPPLY = "max_supply";
    public static final String LAST_UPDATED = "last_updated";
    public static final String QUOTE = "quote";

    @Override
    public void onCreate(@NonNull SQLiteDatabase database) {
        TableBuilder.create(this)
                .textColumn(ID)
                .textColumn(COIN_NAME)
                .textColumn(SYMBOL)
                .textColumn(SLUG)
                .textColumn(CMC_RANK)
                .textColumn(NUM_MARKETS)
                .textColumn(CIRCULATING_SUPPLY)
                .textColumn(TOTAL_SUPPLY)
                .textColumn(MAX_SUPPLY)
                .textColumn(LAST_UPDATED)
                .textColumn(QUOTE)
                .execute(database);
    }

    @NonNull
    @Override
    public ContentValues toValues(@NonNull Datum datum) {
        ContentValues values = new ContentValues();
        values.put(ID, datum.getId());
        values.put(COIN_NAME, datum.getName());
        values.put(SYMBOL, datum.getSymbol());
        values.put(SLUG, datum.getSlug());
        values.put(CMC_RANK, datum.getCmcRank());
        values.put(NUM_MARKETS, datum.getNumMarkets());
        values.put(CIRCULATING_SUPPLY, datum.getCirculatingSupply());
        values.put(TOTAL_SUPPLY, datum.getTotalSupply());
        values.put(MAX_SUPPLY, datum.getMaxSupply());
        values.put(LAST_UPDATED, datum.getLastUpdated());
        values.put(QUOTE, GsonHolder.getGson().toJson(datum.getQuote()));
        return values;
    }

    @NonNull
    @Override
    public Datum fromCursor(@NonNull Cursor cursor) {
        Datum datum = new Datum();
        datum.setId(cursor.getString(cursor.getColumnIndex(ID)));
        datum.setName(cursor.getString(cursor.getColumnIndex(COIN_NAME)));
        datum.setSymbol(cursor.getString(cursor.getColumnIndex(SYMBOL)));
        datum.setSlug(cursor.getString(cursor.getColumnIndex(SLUG)));
        datum.setCmcRank(cursor.getString(cursor.getColumnIndex(CMC_RANK)));
        datum.setNumMarkets(cursor.getString(cursor.getColumnIndex(NUM_MARKETS)));
        datum.setCirculatingSupply(cursor.getString(cursor.getColumnIndex(CIRCULATING_SUPPLY)));
        datum.setTotalSupply(cursor.getString(cursor.getColumnIndex(TOTAL_SUPPLY)));
        datum.setMaxSupply(cursor.getString(cursor.getColumnIndex(MAX_SUPPLY)));
        datum.setLastUpdated(cursor.getString(cursor.getColumnIndex(LAST_UPDATED)));

        Quote quote = GsonHolder.getGson().fromJson(cursor.getString(cursor.getColumnIndex(QUOTE)), Quote.class);
        datum.setQuote(quote);
        return datum;
    }
}