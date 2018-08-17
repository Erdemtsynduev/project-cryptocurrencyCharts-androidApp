package com.erdemtsynduev.profitcoin.network.model.favoritecoin;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FavoriteCoin implements Parcelable {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("name")
    @Expose
    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.name);
    }

    public FavoriteCoin() {
    }

    public FavoriteCoin(String id, String name) {
        this.id = id;
        this.name = name;
    }

    protected FavoriteCoin(Parcel in) {
        this.id = in.readString();
        this.name = in.readString();
    }

    public static final Parcelable.Creator<FavoriteCoin> CREATOR = new Parcelable.Creator<FavoriteCoin>() {
        @Override
        public FavoriteCoin createFromParcel(Parcel source) {
            return new FavoriteCoin(source);
        }

        @Override
        public FavoriteCoin[] newArray(int size) {
            return new FavoriteCoin[size];
        }
    };
}
