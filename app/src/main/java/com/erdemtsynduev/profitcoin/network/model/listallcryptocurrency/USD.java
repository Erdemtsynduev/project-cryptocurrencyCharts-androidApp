package com.erdemtsynduev.profitcoin.network.model.listallcryptocurrency;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class USD implements Parcelable {

    @SerializedName("price")
    @Expose
    private String price;
    @SerializedName("volume_24h")
    @Expose
    private String volume24h;
    @SerializedName("percent_change_1h")
    @Expose
    private String percentChange1h;
    @SerializedName("percent_change_24h")
    @Expose
    private String percentChange24h;
    @SerializedName("percent_change_7d")
    @Expose
    private String percentChange7d;
    @SerializedName("market_cap")
    @Expose
    private String marketCap;

    public String getPrice() {
        if (price == null) {
            return "0";
        }
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getVolume24h() {
        return volume24h;
    }

    public void setVolume24h(String volume24h) {
        this.volume24h = volume24h;
    }

    public String getPercentChange1h() {
        return percentChange1h;
    }

    public void setPercentChange1h(String percentChange1h) {
        this.percentChange1h = percentChange1h;
    }

    public String getPercentChange24h() {
        if (percentChange24h == null) {
            return "0";
        }
        return percentChange24h;
    }

    public void setPercentChange24h(String percentChange24h) {
        this.percentChange24h = percentChange24h;
    }

    public String getPercentChange7d() {
        if (percentChange7d == null) {
            return "0";
        }
        return percentChange7d;
    }

    public void setPercentChange7d(String percentChange7d) {
        this.percentChange7d = percentChange7d;
    }

    public String getMarketCap() {
        return marketCap;
    }

    public void setMarketCap(String marketCap) {
        this.marketCap = marketCap;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.price);
        dest.writeString(this.volume24h);
        dest.writeString(this.percentChange1h);
        dest.writeString(this.percentChange24h);
        dest.writeString(this.percentChange7d);
        dest.writeString(this.marketCap);
    }

    public USD() {
    }

    protected USD(Parcel in) {
        this.price = in.readString();
        this.volume24h = in.readString();
        this.percentChange1h = in.readString();
        this.percentChange24h = in.readString();
        this.percentChange7d = in.readString();
        this.marketCap = in.readString();
    }

    public static final Parcelable.Creator<USD> CREATOR = new Parcelable.Creator<USD>() {
        @Override
        public USD createFromParcel(Parcel source) {
            return new USD(source);
        }

        @Override
        public USD[] newArray(int size) {
            return new USD[size];
        }
    };
}
