package com.example.chapter4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.chapter4.service.DataService;

import java.text.SimpleDateFormat;
import java.util.Random;

public class ServiceDataActivity extends AppCompatActivity implements View.OnClickListener {

    private SimpleDateFormat sdf;
    private final static String TAG = "ServiceDataActivity";
    private Button startAndBindServiceButton;
    private Button unbindAndStopServiceButton;
    private TextView showServiceTextView;
    private static String showText;
    private Intent intent;

    private void setShowText(String text) {
        showText = String.format("%s%s %s\n", showText, sdf.format(System.currentTimeMillis()), text);

        showServiceTextView.setText(showText);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_data);

        sdf = new SimpleDateFormat("HH:mm:ss");
        startAndBindServiceButton = findViewById(R.id.startAndBindServiceButton);
        unbindAndStopServiceButton = findViewById(R.id.unbindAndStopServiceButton);
        showServiceTextView = findViewById(R.id.showServiceTextView);
        showText = "";
        intent = new Intent(this, DataService.class);

        startAndBindServiceButton.setOnClickListener(this);
        unbindAndStopServiceButton.setOnClickListener(this);


    }

    private DataService.LocalBinder binder;

    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            binder = (DataService.LocalBinder) service;
            String response = binder.getNumber(new Random().nextInt(100));
            setShowText("绑定服务应答：" + response);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            binder = null;
        }
    };

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.startAndBindServiceButton) {
            boolean bindFlag = bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);
        } else if (v.getId() == R.id.unbindAndStopServiceButton) {
            if (binder != null) {
                unbindService(serviceConnection);
                setShowText("成功解绑服务");
            }
        }
    }
}