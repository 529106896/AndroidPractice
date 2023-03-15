package com.example.chapter4.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import com.example.chapter4.BindImmediateActivity;

public class BindImmediateService extends Service {

    // 创建一个黏合剂对象
    private final IBinder iBinder = new LocalBinder();

    // 定义一个当前服务的黏合剂，用于将该服务黏到活动页面的进程中
    public class LocalBinder extends Binder {
        public BindImmediateService getService() {
            return BindImmediateService.this;
        }
    }

    private void refresh(String text) {
        BindImmediateActivity.setText(text);
    }

    public BindImmediateService() {
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
        refresh("onUnbind");
        return true;
    }
}