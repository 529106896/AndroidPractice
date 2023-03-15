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

public class BroadOrderActivity extends AppCompatActivity implements View.OnClickListener {

    private final static String ORDER_ACTION = "com.example.chapter4.order";
    private TextView showBroadCastTextView;
    private Button sendBroadCastButton;
    private SimpleDateFormat sdf;
    private CheckBox checkBox;
    private BroadcastReceiver orderAReceiver;
    private BroadcastReceiver orderBReceiver;

    private class OrderAReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent != null && intent.getAction().equals(ORDER_ACTION)) {
                String showText = String.format("%s\n%s 接收器A收到一个有序广播", showBroadCastTextView.getText(), sdf.format(System.currentTimeMillis()));
                showBroadCastTextView.setText(showText);

                if (checkBox.isChecked()) {
                    abortBroadcast();
                }
            }
        }
    }

    private class OrderBReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent != null && intent.getAction().equals(ORDER_ACTION)) {
                String showText = String.format("%s\n%s 接收器B收到一个有序广播", showBroadCastTextView.getText(), sdf.format(System.currentTimeMillis()));
                showBroadCastTextView.setText(showText);

                if (checkBox.isChecked()) {
                    abortBroadcast();
                }
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broad_order);

        showBroadCastTextView = findViewById(R.id.showBroadCastTextView);
        sendBroadCastButton = findViewById(R.id.sendBroadCastButton);
        sdf = new SimpleDateFormat("HH:mm:ss:SS");
        checkBox = findViewById(R.id.isCutBroadCastCheckBox);

        sendBroadCastButton.setOnClickListener(this);
    }

    @Override
    public void onStart() {
        super.onStart();

        orderAReceiver = new OrderAReceiver();
        IntentFilter filterA = new IntentFilter(ORDER_ACTION);
        filterA.setPriority(8);
        registerReceiver(orderAReceiver, filterA);

        orderBReceiver = new OrderBReceiver();
        IntentFilter filterB = new IntentFilter(ORDER_ACTION);
        filterB.setPriority(10);
        registerReceiver(orderBReceiver, filterB);
    }

    @Override
    public void onStop() {
        super.onStop();
        unregisterReceiver(orderAReceiver);
        unregisterReceiver(orderBReceiver);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(ORDER_ACTION);
        sendOrderedBroadcast(intent, null);
    }
}