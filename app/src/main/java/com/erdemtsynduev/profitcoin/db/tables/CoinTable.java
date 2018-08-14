package com.erdemtsynduev.profitcoin.db.tables;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;
import android.content.ContentValues;

import com.erdemtsynduev.profitcoin.db.utils.GsonHolder;
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
    private long id;

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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getCmc_rank() {
        return cmc_rank;
    }

    public void setCmc_rank(String cmc_rank) {
        this.cmc_rank = cmc_rank;
    }

    public String getNum_markets() {
        return num_markets;
    }

    public void setNum_markets(String num_markets) {
        this.num_markets = num_markets;
    }

    public String getCirculating_supply() {
        return circulating_supply;
    }

    public void setCirculating_supply(String circulating_supply) {
        this.circulating_supply = circulating_supply;
    }

    public String getTotal_supply() {
        return total_supply;
    }

    public void setTotal_supply(String total_supply) {
        this.total_supply = total_supply;
    }

    public String getMax_supply() {
        return max_supply;
    }

    public void setMax_supply(String max_supply) {
        this.max_supply = max_supply;
    }

    public String getLast_updated() {
        return last_updated;
    }

    public void setLast_updated(String last_updated) {
        this.last_updated = last_updated;
    }

    public Quote getQuote() {
        return quote;
    }

    public void setQuote(Quote quote) {
        this.quote = quote;
    }

    /**
     * Create a new {@link Datum} from the specified {@link ContentValues}.
     *
     * @param values A {@link ContentValues} that at least contain {@link #COLUMN_ID}.
     * @return A newly created {@link Datum} instance.
     */
    public static CoinTable fromContentValues(ContentValues values) {
        final CoinTable coinTable = new CoinTable();
        if (values.containsKey(COLUMN_ID)) {
            coinTable.setId(values.getAsLong(COLUMN_ID));
        }
        if (values.containsKey(COLUMN_NAME)) {
            coinTable.setName(values.getAsString(COLUMN_NAME));
        }
        if (values.containsKey(COLUMN_SYMBOL)) {
            coinTable.setSymbol(values.getAsString(COLUMN_SYMBOL));
        }
        if (values.containsKey(COLUMN_SLUG)) {
            coinTable.setSlug(values.getAsString(COLUMN_SLUG));
        }
        if (values.containsKey(COLUMN_CMC_RANK)) {
            coinTable.setCmc_rank(values.getAsString(COLUMN_CMC_RANK));
        }
        if (values.containsKey(COLUMN_NUM_MARKETS)) {
            coinTable.setNum_markets(values.getAsString(COLUMN_NUM_MARKETS));
        }
        if (values.containsKey(COLUMN_CIRCULATING_SUPPLY)) {
            coinTable.setCirculating_supply(values.getAsString(COLUMN_CIRCULATING_SUPPLY));
        }
        if (values.containsKey(COLUMN_TOTAL_SUPPLY)) {
            coinTable.setTotal_supply(values.getAsString(COLUMN_TOTAL_SUPPLY));
        }
        if (values.containsKey(COLUMN_MAX_SUPPLY)) {
            coinTable.setMax_supply(values.getAsString(COLUMN_MAX_SUPPLY));
        }
        if (values.containsKey(COLUMN_LAST_UPDATED)) {
            coinTable.setLast_updated(values.getAsString(COLUMN_LAST_UPDATED));
        }
        if (values.containsKey(COLUMN_QUOTE)) {
            coinTable.setQuote(GsonHolder.getGson().fromJson(values.getAsString(COLUMN_QUOTE), Quote.class));
        }
        return coinTable;
    }

    /**
     * Create a new {@link ContentValues} from the specified {@link Datum}.
     *
     * @param datum A {@link Datum} that at least contain {@link #COLUMN_ID}.
     * @return A newly created {@link ContentValues} instance.
     */
    public static ContentValues toContentValues(Datum datum) {
        final ContentValues infoValues = new ContentValues();

        if (datum.getId() != null && !datum.getId().isEmpty()) {
            infoValues.put(COLUMN_ID, datum.getId());
        }
        if (datum.getName() != null && !datum.getName().isEmpty()) {
            infoValues.put(COLUMN_NAME, datum.getName());
        }
        if (datum.getSymbol() != null && !datum.getSymbol().isEmpty()) {
            infoValues.put(COLUMN_SYMBOL, datum.getSymbol());
        }
        if (datum.getSlug() != null && !datum.getSlug().isEmpty()) {
            infoValues.put(COLUMN_SLUG, datum.getSlug());
        }
        if (datum.getCmcRank() != null && !datum.getCmcRank().isEmpty()) {
            infoValues.put(COLUMN_CMC_RANK, datum.getCmcRank());
        }
        if (datum.getNumMarkets() != null && !datum.getNumMarkets().isEmpty()) {
            infoValues.put(COLUMN_NUM_MARKETS, datum.getNumMarkets());
        }
        if (datum.getCirculatingSupply() != null && !datum.getCirculatingSupply().isEmpty()) {
            infoValues.put(COLUMN_CIRCULATING_SUPPLY, datum.getCirculatingSupply());
        }
        if (datum.getTotalSupply() != null && !datum.getTotalSupply().isEmpty()) {
            infoValues.put(COLUMN_TOTAL_SUPPLY, datum.getTotalSupply());
        }
        if (datum.getTotalSupply() != null && !datum.getTotalSupply().isEmpty()) {
            infoValues.put(COLUMN_MAX_SUPPLY, datum.getTotalSupply());
        }
        if (datum.getLastUpdated() != null && !datum.getLastUpdated().isEmpty()) {
            infoValues.put(COLUMN_LAST_UPDATED, datum.getLastUpdated());
        }
        if (datum.getQuote() != null) {
            infoValues.put(COLUMN_QUOTE, GsonHolder.getGson().toJson(datum.getQuote()));
        }
        return infoValues;
    }
}