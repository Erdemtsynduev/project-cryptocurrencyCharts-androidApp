package com.erdemtsynduev.profitcoin.network.model.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.reactivex.annotations.NonNull;

public class Request {

    @SerializedName("id_request")
    @Expose
    private String mIdRequest;

    @SerializedName("request")
    @Expose
    @NetworkRequest
    private final String mRequest;

    @SerializedName("status")
    @Expose
    private RequestStatus mStatus;

    @SerializedName("error")
    @Expose
    private String mError;

    public Request(@NonNull @NetworkRequest String request) {
        mRequest = request;
    }

    public Request(@NonNull @NetworkRequest String request,
                   @NonNull RequestStatus status, @NonNull String error) {
        mRequest = request;
        mStatus = status;
        mError = error;
    }

    @NetworkRequest
    @NonNull
    public String getRequest() {
        return mRequest;
    }

    @NonNull
    public RequestStatus getStatus() {
        return mStatus;
    }

    public void setStatus(@NonNull RequestStatus status) {
        mStatus = status;
    }

    @NonNull
    public String getError() {
        return mError;
    }

    public void setError(@NonNull String error) {
        mError = error;
    }

    public String getIdRequest() {
        return mIdRequest;
    }

    public void setIdRequest(String idRequest) {
        this.mIdRequest = idRequest;
    }
}