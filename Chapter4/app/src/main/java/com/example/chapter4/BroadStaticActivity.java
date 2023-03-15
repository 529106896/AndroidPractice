package com.example.chapter4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.chapter4.receiver.ShockReceiver;

public class BroadStaticActivity extends AppCompatActivity implements View.OnClickListener {

    private Button sendVibrateButton;
    private ShockReceiver shockReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broad_static);

        sendVibrateButton = findViewById(R.id.sendVibrateButton);
        sendVibrateButton.setOnClickListener(this);
    }

    @Override
    public void onStart() {
        super.onStart();
        shockReceiver = new ShockReceiver();
        IntentFilter filter = new IntentFilter(ShockReceiver.SHOCK_ACTION);
        registerReceiver(shockReceiver, filter);
    }

    @Override
    public void onStop() {
        super.onStop();
        unregisterReceiver(shockReceiver);
    }

    @Override
    public void onClick(View v) {
        String receiverPath = "com.example.chapter4.receiver.ShockReceiver";

        Intent intent = new Intent(ShockReceiver.SHOCK_ACTION);

        ComponentName componentName = new ComponentName(this, receiverPath);
        intent.setComponent(componentName);
        sendBroadcast(intent);
        Toast.makeText(this, "已发送震动广播", Toast.LENGTH_SHORT).show();
    }
}