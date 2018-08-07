package com.erdemtsynduev.profitcoin.network;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.erdemtsynduev.profitcoin.ExtendApplication;
import com.erdemtsynduev.profitcoin.db.GsonHolder;
import com.erdemtsynduev.profitcoin.db.tables.CoinTable;
import com.erdemtsynduev.profitcoin.db.tables.RequestTable;
import com.erdemtsynduev.profitcoin.network.model.listallcryptocurrency.Datum;
import com.erdemtsynduev.profitcoin.network.model.request.NetworkRequest;
import com.erdemtsynduev.profitcoin.network.model.request.Request;
import com.erdemtsynduev.profitcoin.network.model.request.RequestStatus;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;

import ru.arturvasilov.sqlite.core.SQLite;
import ru.arturvasilov.sqlite.core.Where;

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
        Request savedRequest = SQLite.get().querySingle(RequestTable.TABLE,
                Where.create().equalTo(RequestTable.REQUEST, request.getRequest()));

        if (savedRequest != null && request.getStatus() == RequestStatus.IN_PROGRESS) {
            return;
        }
        request.setStatus(RequestStatus.IN_PROGRESS);
        SQLite.get().insert(RequestTable.TABLE, request);
        SQLite.get().notifyTableChanged(RequestTable.TABLE);

        if (TextUtils.equals(NetworkRequest.LIST_COIN, request.getRequest())) {
            executeRequest(request);
        }
    }

    private void executeRequest(@NonNull Request request) {
        try {
            List<Datum> data = mCoinMarketCapService.getTicker().execute().body().getData();
            SQLite.get().delete(CoinTable.TABLE);
            SQLite.get().insert(CoinTable.TABLE, data);
            request.setStatus(RequestStatus.SUCCESS);
        } catch (IOException e) {
            request.setStatus(RequestStatus.ERROR);
            request.setError(e.getMessage());
        } finally {
            SQLite.get().insert(RequestTable.TABLE, request);
            SQLite.get().notifyTableChanged(RequestTable.TABLE);
        }
    }
}
