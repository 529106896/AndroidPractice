package com.example.chapter4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.SimpleDateFormat;

public class BroadStandardActivity extends AppCompatActivity implements View.OnClickListener {

    private class StandardReceiver extends BroadcastReceiver {

        private String showText = "这里查看标准广播的收听信息。";

        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent != null && intent.getAction().equals(STANDARD_ACTION)) {
                showText = String.format("%s\n%s 收到一个标准广播", showText, sdf.format(System.currentTimeMillis()));
                showBroadCastTextView.setText(showText);
            }
        }
    }

    private final static String STANDARD_ACTION = "com.example.chapter4.standard";

    private TextView showBroadCastTextView;
    private Button sendBroadCastButton;
    private SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
    private StandardReceiver standardReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broad_standard);

        showBroadCastTextView = findViewById(R.id.showBroadCastTextView);
        sendBroadCastButton = findViewById(R.id.sendBroadCastButton);

        sendBroadCastButton.setOnClickListener(this);
    }

    @Override
    public void onStart() {
        super.onStart();
        standardReceiver = new StandardReceiver();
        IntentFilter intentFilter = new IntentFilter(STANDARD_ACTION);
        registerReceiver(standardReceiver, intentFilter);
    }

    @Override
    public void onStop() {
        super.onStop();
        unregisterReceiver(standardReceiver);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.sendBroadCastButton) {
            Intent intent = new Intent(STANDARD_ACTION);
            sendBroadcast(intent);
        }
    }
}