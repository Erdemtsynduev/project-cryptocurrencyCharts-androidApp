package com.erdemtsynduev.profitcoin.db.tables;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;
import android.content.ContentValues;

import com.erdemtsynduev.profitcoin.db.GsonHolder;
import com.erdemtsynduev.profitcoin.db.converter.QuoteConverter;
import com.erdemtsynduev.profitcoin.network.model.listallcryptocurrency.Datum;
import com.erdemtsynduev.profitcoin.network.model.listallcryptocurrency.Quote;

@Entity(tableName = CoinTable.TABLE_NAME)
public class CoinTable {

    /**
     * The name of the Coin table.
     */
    public static final String TABLE_NAME = "coin_table";

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_SYMBOL = "symbol";
    public static final String COLUMN_SLUG = "slug";
    public static final String COLUMN_CMC_RANK = "cmc_rank";
    public static final String COLUMN_NUM_MARKETS = "num_markets";
    public static final String COLUMN_CIRCULATING_SUPPLY = "circulating_supply";
    public static final String COLUMN_TOTAL_SUPPLY = "total_supply";
    public static final String COLUMN_MAX_SUPPLY = "max_supply";
    public static final String COLUMN_LAST_UPDATED = "last_updated";
    public static final String COLUMN_QUOTE = "quote";

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(index = true, name = COLUMN_ID)
    private String id;

    @ColumnInfo(name = COLUMN_NAME)
    private String name;

    @ColumnInfo(name = COLUMN_SYMBOL)
    private String symbol;

    @ColumnInfo(name = COLUMN_SLUG)
    private String slug;

    @ColumnInfo(name = COLUMN_CMC_RANK)
    private String cmc_rank;

    @ColumnInfo(name = COLUMN_NUM_MARKETS)
    private String num_markets;

    @ColumnInfo(name = COLUMN_CIRCULATING_SUPPLY)
    private String circulating_supply;

    @ColumnInfo(name = COLUMN_TOTAL_SUPPLY)
    private String total_supply;

    @ColumnInfo(name = COLUMN_MAX_SUPPLY)
    private String max_supply;

    @ColumnInfo(name = COLUMN_LAST_UPDATED)
    private String last_updated;

    @TypeConverters({QuoteConverter.class})
    @ColumnInfo(name = COLUMN_QUOTE)
    private Quote quote;

    /**
     * Create a new {@link Datum} from the specified {@link ContentValues}.
     *
     * @param values A {@link ContentValues} that at least contain {@link #COLUMN_ID}.
     * @return A newly created {@link Datum} instance.
     */
    public static Datum fromContentValues(ContentValues values) {
        final Datum datum = new Datum();
        if (values.containsKey(COLUMN_ID)) {
            datum.setId(values.getAsString(COLUMN_ID));
        }
        if (values.containsKey(COLUMN_NAME)) {
            datum.setName(values.getAsString(COLUMN_NAME));
        }
        if (values.containsKey(COLUMN_SYMBOL)) {
            datum.setSymbol(values.getAsString(COLUMN_SYMBOL));
        }
        if (values.containsKey(COLUMN_SLUG)) {
            datum.setSlug(values.getAsString(COLUMN_SLUG));
        }
        if (values.containsKey(COLUMN_CMC_RANK)) {
            datum.setCmcRank(values.getAsString(COLUMN_CMC_RANK));
        }
        if (values.containsKey(COLUMN_NUM_MARKETS)) {
            datum.setNumMarkets(values.getAsString(COLUMN_NUM_MARKETS));
        }
        if (values.containsKey(COLUMN_CIRCULATING_SUPPLY)) {
            datum.setCirculatingSupply(values.getAsString(COLUMN_CIRCULATING_SUPPLY));
        }
        if (values.containsKey(COLUMN_TOTAL_SUPPLY)) {
            datum.setTotalSupply(values.getAsString(COLUMN_TOTAL_SUPPLY));
        }
        if (values.containsKey(COLUMN_MAX_SUPPLY)) {
            datum.setMaxSupply(values.getAsString(COLUMN_MAX_SUPPLY));
        }
        if (values.containsKey(COLUMN_LAST_UPDATED)) {
            datum.setMaxSupply(values.getAsString(COLUMN_LAST_UPDATED));
        }
        if (values.containsKey(COLUMN_QUOTE)) {
            datum.setQuote(GsonHolder.getGson().fromJson(values.getAsString(COLUMN_QUOTE), Quote.class));
        }
        return datum;
    }
}