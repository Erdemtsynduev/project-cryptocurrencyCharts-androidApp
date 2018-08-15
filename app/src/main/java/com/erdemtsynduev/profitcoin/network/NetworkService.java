package com.erdemtsynduev.profitcoin.network;

import android.app.IntentService;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.erdemtsynduev.profitcoin.ExtendApplication;
import com.erdemtsynduev.profitcoin.db.DbTableChangedEvent;
import com.erdemtsynduev.profitcoin.db.provider.CoinContentProvider;
import com.erdemtsynduev.profitcoin.db.utils.GsonHolder;
import com.erdemtsynduev.profitcoin.db.tables.CoinTable;
import com.erdemtsynduev.profitcoin.db.tables.RequestTable;
import com.erdemtsynduev.profitcoin.network.model.listallcryptocurrency.Datum;
import com.erdemtsynduev.profitcoin.network.model.request.NetworkRequest;
import com.erdemtsynduev.profitcoin.network.model.request.Request;
import com.erdemtsynduev.profitcoin.network.model.request.RequestStatus;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;

public class NetworkService extends IntentService {

    @Inject
    CoinMarketCapService mCoinMarketCapService;

    private static final String REQUEST_KEY = "request";

    public static void start(@NonNull Context context, @NonNull Request request) {
        Intent intent = new Intent(context, NetworkService.class);
        intent.putExtra(REQUEST_KEY, GsonHolder.getGson().toJson(request));
        context.startService(intent);
    }

    public NetworkService() {
        super(NetworkService.class.getName());
        ExtendApplication.getAppComponent().inject(this);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Request request = GsonHolder.getGson().fromJson(intent.getStringExtra(REQUEST_KEY), Request.class);

        Cursor cursor = getContentResolver().query(Uri.parse(CoinContentProvider.URI_REQUEST_TABLE + "/" + 1),
                null, null, null, null);

        Request savedRequest = null;

        if (cursor != null && cursor.getCount() != 0) {
            while (cursor.moveToNext()) {
                String idRequest = cursor.getString(cursor.getColumnIndex(RequestTable.COLUMN_ID));
                String nameRequest = cursor.getString(cursor.getColumnIndex(RequestTable.COLUMN_REQUEST));
                String statusRequest = cursor.getString(cursor.getColumnIndex(RequestTable.COLUMN_STATUS));
                String errorRequest = cursor.getString(cursor.getColumnIndex(RequestTable.COLUMN_ERROR));
                savedRequest = new Request(nameRequest);
                savedRequest.setIdRequest(idRequest);
                savedRequest.setStatus(RequestStatus.valueOf(statusRequest));
                savedRequest.setError(errorRequest);
            }
            cursor.close();
        }

        if (savedRequest != null && savedRequest.getStatus() == RequestStatus.IN_PROGRESS) {
            return;
        }

        request.setStatus(RequestStatus.IN_PROGRESS);
        request.setIdRequest(1 + "");

        getContentResolver().insert(CoinContentProvider.URI_REQUEST_TABLE, RequestTable.toContentValues(request));
        sendPostBus();

        if (TextUtils.equals(NetworkRequest.LIST_COIN, request.getRequest())) {
            executeRequest(request);
        }
    }

    private void executeRequest(@NonNull Request request) {
        try {
            List<Datum> data = mCoinMarketCapService.getTicker().execute().body().getData();
            ContentValues[] contentValues = new ContentValues[data.size()];
            for (int i = 0; i < data.size(); i++) {
                contentValues[i] = CoinTable.toContentValues(data.get(i));
            }
            getContentResolver().bulkInsert(CoinContentProvider.URI_COIN_TABLE, contentValues);
            request.setStatus(RequestStatus.SUCCESS);
        } catch (IOException e) {
            request.setStatus(RequestStatus.ERROR);
            request.setError(e.getMessage());
        } finally {
            getContentResolver().insert(CoinContentProvider.URI_REQUEST_TABLE, RequestTable.toContentValues(request));
            sendPostBus();
        }
    }

    private void sendPostBus() {
        Handler handler = new Handler(Looper.getMainLooper());
        handler.post(() -> ExtendApplication.getAppComponent().getBus().post(new DbTableChangedEvent(RequestTable.TABLE_NAME)));
    }
}
