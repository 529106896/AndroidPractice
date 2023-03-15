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

import com.example.chapter4.service.BindImmediateService;

import java.text.SimpleDateFormat;

public class BindImmediateActivity extends AppCompatActivity implements View.OnClickListener {

    private static TextView showServiceTextView;
    private static SimpleDateFormat sdf;
    private Button startAndBindServiceButton;
    private Button unbindAndStopServiceButton;
    private static String showText;
    private Intent intent;

    public static void setText(String text) {
        showText = String.format("%s%s %s\n", showText, sdf.format(System.currentTimeMillis()), text);
        showServiceTextView.setText(showText);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bind_immediate);

        showText = "";
        showServiceTextView = findViewById(R.id.showServiceTextView);
        sdf = new SimpleDateFormat("HH:mm:ss");
        startAndBindServiceButton = findViewById(R.id.startAndBindServiceButton);
        unbindAndStopServiceButton = findViewById(R.id.unbindAndStopServiceButton);
        intent = new Intent(this, BindImmediateService.class);

        startAndBindServiceButton.setOnClickListener(this);
        unbindAndStopServiceButton.setOnClickListener(this);
    }

    private BindImmediateService bindImmediateService;
    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        // 获取到服务对象时的操作
        public void onServiceConnected(ComponentName name, IBinder service) {
            // 如果服务运行于另外一个进程，则不能直接强制转换类型，否则会报错
            bindImmediateService = ((BindImmediateService.LocalBinder) service).getService();
        }

        @Override
        // 无法获取到服务对象时的操作
        public void onServiceDisconnected(ComponentName name) {
            bindImmediateService = null;
        }
    };

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.startAndBindServiceButton) {
            // 绑定服务。如果服务未启动，则系统先启动该服务，再进行绑定
            boolean bindFlag = bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);
        } else if (v.getId() == R.id.unbindAndStopServiceButton) {
            if (bindImmediateService != null) {
                // 解绑服务。如果先前服务已绑定，则此时解绑之后自动停止服务
                unbindService(serviceConnection);
                bindImmediateService = null;
            }
        }
    }
}