package com.example.chapter4.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import com.example.chapter4.BindDelayActivity;

public class BindDelayService extends Service {

    private final static String TAG = "BindDelayService";

    private final IBinder iBinder = new LocalBinder();

    public class LocalBinder extends Binder {
        public BindDelayService getService() {
            return BindDelayService.this;
        }
    }

    private void refresh(String text) {
        Log.d(TAG, text);
        BindDelayActivity.setShowText(text);
    }

    public BindDelayService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        refresh("onCreate");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        refresh("onDestroy");
    }

    @Override
    public IBinder onBind(Intent intent) {
        refresh("onBind");
        return iBinder;
    }

    @Override
    public void onRebind(Intent intent) {
        super.onRebind(intent);
        refresh("onRebind");
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.d(TAG, "绑定服务结束");
        refresh("onUnbind");
        return true;
    }


}