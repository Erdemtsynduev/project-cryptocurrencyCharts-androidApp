package com.erdemtsynduev.profitcoin.network.model.listallcryptocurrency;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Datum implements Parcelable {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("symbol")
    @Expose
    private String symbol;
    @SerializedName("slug")
    @Expose
    private String slug;
    @SerializedName("cmc_rank")
    @Expose
    private String cmcRank;
    @SerializedName("num_markets")
    @Expose
    private String numMarkets;
    @SerializedName("circulating_supply")
    @Expose
    private String circulatingSupply;
    @SerializedName("total_supply")
    @Expose
    private String totalSupply;
    @SerializedName("max_supply")
    @Expose
    private String maxSupply;
    @SerializedName("last_updated")
    @Expose
    private String lastUpdated;
    @SerializedName("quote")
    @Expose
    private Quote quote;

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

    public String getCmcRank() {
        return cmcRank;
    }

    public void setCmcRank(String cmcRank) {
        this.cmcRank = cmcRank;
    }

    public String getNumMarkets() {
        return numMarkets;
    }

    public void setNumMarkets(String numMarkets) {
        this.numMarkets = numMarkets;
    }

    public String getCirculatingSupply() {
        return circulatingSupply;
    }

    public void setCirculatingSupply(String circulatingSupply) {
        this.circulatingSupply = circulatingSupply;
    }

    public String getTotalSupply() {
        return totalSupply;
    }

    public void setTotalSupply(String totalSupply) {
        this.totalSupply = totalSupply;
    }

    public String getMaxSupply() {
        return maxSupply;
    }

    public void setMaxSupply(String maxSupply) {
        this.maxSupply = maxSupply;
    }

    public String getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(String lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public Quote getQuote() {
        return quote;
    }

    public void setQuote(Quote quote) {
        this.quote = quote;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.name);
        dest.writeString(this.symbol);
        dest.writeString(this.slug);
        dest.writeString(this.cmcRank);
        dest.writeString(this.numMarkets);
        dest.writeString(this.circulatingSupply);
        dest.writeString(this.totalSupply);
        dest.writeString(this.maxSupply);
        dest.writeString(this.lastUpdated);
        dest.writeParcelable(this.quote, flags);
    }

    public Datum() {
    }

    protected Datum(Parcel in) {
        this.id = in.readString();
        this.name = in.readString();
        this.symbol = in.readString();
        this.slug = in.readString();
        this.cmcRank = in.readString();
        this.numMarkets = in.readString();
        this.circulatingSupply = in.readString();
        this.totalSupply = in.readString();
        this.maxSupply = in.readString();
        this.lastUpdated = in.readString();
        this.quote = in.readParcelable(Quote.class.getClassLoader());
    }

    public static final Parcelable.Creator<Datum> CREATOR = new Parcelable.Creator<Datum>() {
        @Override
        public Datum createFromParcel(Parcel source) {
            return new Datum(source);
        }

        @Override
        public Datum[] newArray(int size) {
            return new Datum[size];
        }
    };
}
