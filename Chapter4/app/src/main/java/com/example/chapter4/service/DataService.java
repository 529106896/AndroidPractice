package com.example.chapter4.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

public class DataService extends Service {

    private final IBinder binder = new LocalBinder();

    public class LocalBinder extends Binder {
        public DataService getService() {
            return DataService.this;
        }

        public String getNumber(int number) {
            return "我收到了数字 " + number;
        }
    }

    public DataService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }
}