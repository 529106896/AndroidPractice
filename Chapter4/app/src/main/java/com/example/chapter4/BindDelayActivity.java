package com.example.chapter4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.chapter4.service.BindDelayService;

import java.text.SimpleDateFormat;

public class BindDelayActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "BindDelayActivity";
    private static String showText;
    private static TextView showServiceTextView;
    private Button startServiceButton;
    private Button bindServiceButton;
    private Button unbindServiceButton;
    private Button stopServiceButton;
    private static SimpleDateFormat sdf;
    private Intent intent;

    public static void setShowText (String text) {
        showText = String.format("%s%s %s\n", showText, sdf.format(System.currentTimeMillis()), text);

        showServiceTextView.setText(showText);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bind_delay);

        showText = "";
        showServiceTextView = findViewById(R.id.showServiceTextView);
        startServiceButton = findViewById(R.id.startServiceButton);
        bindServiceButton = findViewById(R.id.bindServiceButton);
        unbindServiceButton = findViewById(R.id.unbindServiceButton);
        stopServiceButton = findViewById(R.id.stopServiceButton);
        sdf = new SimpleDateFormat("HH:mm:ss");
        intent = new Intent(this, BindDelayService.class);

        startServiceButton.setOnClickListener(this);
        bindServiceButton.setOnClickListener(this);
        unbindServiceButton.setOnClickListener(this);
        stopServiceButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.startServiceButton) {
            startService(intent);
        } else if (v.getId() == R.id.bindServiceButton) {
            boolean bindFlag = bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);
            Log.d(TAG, "bindFlag = " + bindFlag);
        } else if (v.getId() == R.id.unbindServiceButton) {
            if (bindDelayService != null) {
                unbindService(serviceConnection);
            }
        } else if (v.getId() == R.id.stopServiceButton) {
            Log.d(TAG, "点击stopService");
            stopService(intent);
        }
    }

    private BindDelayService bindDelayService;
    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        // 获取服务对象时的操作
        public void onServiceConnected(ComponentName name, IBinder service) {
            bindDelayService = ((BindDelayService.LocalBinder) service).getService();
            Log.d(TAG, "onServiceConnected");
        }

        @Override
        // 无法获取到服务对象时的操作
        public void onServiceDisconnected(ComponentName name) {
            bindDelayService = null;
            Log.d(TAG, "onServiceDisconnected");
        }
    };
}