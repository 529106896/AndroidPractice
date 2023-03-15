package com.example.chapter4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import java.text.SimpleDateFormat;

public class OrderedBroadcastActivity extends AppCompatActivity implements View.OnClickListener {

    private class OrderAReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent != null && intent.getAction().equals(ORDER_ACTION)) {
                showText = String.format("%s%s 接收器A收到一个有序广播\n", showText, sdf.format(System.currentTimeMillis()));
                showBroadCastTextView.setText(showText);

                if (isCutBroadCastCheckBox.isChecked()) {
                    abortBroadcast();
                }
            }
        }
    }

    private class OrderBReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent != null && intent.getAction().equals(ORDER_ACTION)) {
                showText = String.format("%s%s 接收器B收到一个有序广播\n", showText, sdf.format(System.currentTimeMillis()));
                showBroadCastTextView.setText(showText);

                if (isCutBroadCastCheckBox.isChecked()) {
                    abortBroadcast();
                }
            }
        }
    }

    private CheckBox isCutBroadCastCheckBox;
    private Button sendOrderBroadcastButton;
    private TextView showBroadCastTextView;
    private SimpleDateFormat sdf;
    private String showText;
    private final static String ORDER_ACTION = "orderBroadcast";

    private OrderAReceiver orderAReceiver;
    private OrderBReceiver orderBReceiver;

    @Override
    public void onStart() {
        super.onStart();
        orderAReceiver = new OrderAReceiver();
        orderBReceiver = new OrderBReceiver();

        IntentFilter intentFilterForA = new IntentFilter(ORDER_ACTION);
        IntentFilter intentFilterForB = new IntentFilter(ORDER_ACTION);
        intentFilterForA.setPriority(8);
        intentFilterForB.setPriority(10);

        registerReceiver(orderAReceiver, intentFilterForA);
        registerReceiver(orderBReceiver, intentFilterForB);
    }

    @Override
    public void onStop() {
        super.onStop();
        unregisterReceiver(orderAReceiver);
        unregisterReceiver(orderBReceiver);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ordered_broadcast);

        isCutBroadCastCheckBox = findViewById(R.id.isCutBroadCastCheckBox);
        sendOrderBroadcastButton = findViewById(R.id.sendOrderBroadcastButton);
        showBroadCastTextView = findViewById(R.id.showBroadCastTextView);
        sdf = new SimpleDateFormat("HH:mm:ss");
        showText = "";

        sendOrderBroadcastButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.sendOrderBroadcastButton) {
            Intent intent = new Intent(ORDER_ACTION);
            sendOrderedBroadcast(intent, null);
        }
    }
}