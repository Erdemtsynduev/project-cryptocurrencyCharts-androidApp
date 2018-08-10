package com.erdemtsynduev.profitcoin.db.tables;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.content.ContentValues;

import com.erdemtsynduev.profitcoin.network.model.favoritecoin.FavoriteCoin;

@Entity(tableName = FavoriteTable.TABLE_NAME)
public class FavoriteTable{

    /**
     * The name of the Request table.
     */
    public static final String TABLE_NAME = "favorite_table";

    public static final String COLUMN_ID_FAVORITE = "id_favorite";
    public static final String COLUMN_NAME_FAVORITE = "name_favorite";

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(index = true, name = COLUMN_ID_FAVORITE)
    private String id_favorite;

    @ColumnInfo(name = COLUMN_NAME_FAVORITE)
    private String name_favorite;

    /**
     * Create a new {@link FavoriteCoin} from the specified {@link ContentValues}.
     *
     * @param values A {@link ContentValues} that at least contain {@link #COLUMN_ID_FAVORITE}.
     * @return A newly created {@link FavoriteCoin} instance.
     */
    public static FavoriteCoin fromContentValues(ContentValues values) {
        final FavoriteCoin favoriteCoin = new FavoriteCoin();
        if (values.containsKey(COLUMN_ID_FAVORITE)) {
            favoriteCoin.setId(values.getAsString(COLUMN_ID_FAVORITE));
        }
        if (values.containsKey(COLUMN_NAME_FAVORITE)) {
            favoriteCoin.setName(values.getAsString(COLUMN_NAME_FAVORITE));
        }
        return favoriteCoin;
    }
}