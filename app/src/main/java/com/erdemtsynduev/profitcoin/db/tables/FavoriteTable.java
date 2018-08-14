package com.erdemtsynduev.profitcoin.db.tables;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.content.ContentValues;

import com.erdemtsynduev.profitcoin.network.model.favoritecoin.FavoriteCoin;
import com.erdemtsynduev.profitcoin.network.model.listallcryptocurrency.Datum;

@Entity(tableName = FavoriteTable.TABLE_NAME)
public class FavoriteTable {

    /**
     * The name of the Request table.
     */
    public static final String TABLE_NAME = "favorite_table";

    public static final String COLUMN_ID_FAVORITE = "id_favorite";
    public static final String COLUMN_NAME_FAVORITE = "name_favorite";

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(index = true, name = COLUMN_ID_FAVORITE)
    private long id_favorite;

    @ColumnInfo(name = COLUMN_NAME_FAVORITE)
    private String name_favorite;

    public long getId_favorite() {
        return id_favorite;
    }

    public void setId_favorite(long id_favorite) {
        this.id_favorite = id_favorite;
    }

    public String getName_favorite() {
        return name_favorite;
    }

    public void setName_favorite(String name_favorite) {
        this.name_favorite = name_favorite;
    }

    /**
     * Create a new {@link FavoriteCoin} from the specified {@link ContentValues}.
     *
     * @param values A {@link ContentValues} that at least contain {@link #COLUMN_ID_FAVORITE}.
     * @return A newly created {@link FavoriteCoin} instance.
     */
    public static FavoriteTable fromContentValues(ContentValues values) {
        final FavoriteTable favoriteTable = new FavoriteTable();
        if (values.containsKey(COLUMN_ID_FAVORITE)) {
            favoriteTable.setId_favorite(values.getAsLong(COLUMN_ID_FAVORITE));
        }
        if (values.containsKey(COLUMN_NAME_FAVORITE)) {
            favoriteTable.setName_favorite(values.getAsString(COLUMN_NAME_FAVORITE));
        }
        return favoriteTable;
    }

    /**
     * Create a new {@link ContentValues} from the specified {@link Datum}.
     *
     * @param favoriteCoin A {@link FavoriteCoin} that at least contain {@link #COLUMN_ID_FAVORITE}.
     * @return A newly created {@link ContentValues} instance.
     */
    public static ContentValues toContentValues(FavoriteCoin favoriteCoin) {
        final ContentValues infoValues = new ContentValues();

        if (favoriteCoin.getId() != null && !favoriteCoin.getId().isEmpty()) {
            infoValues.put(COLUMN_ID_FAVORITE, favoriteCoin.getId());
        }
        if (favoriteCoin.getName() != null && !favoriteCoin.getName().isEmpty()) {
            infoValues.put(COLUMN_NAME_FAVORITE, favoriteCoin.getName());
        }
        return infoValues;
    }
}