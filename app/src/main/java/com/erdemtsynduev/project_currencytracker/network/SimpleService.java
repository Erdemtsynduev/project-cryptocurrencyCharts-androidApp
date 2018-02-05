package com.erdemtsynduev.project_currencytracker.network;


import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import io.reactivex.annotations.Nullable;

public class SimpleService extends Service {

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}