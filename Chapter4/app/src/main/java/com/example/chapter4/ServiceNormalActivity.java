package com.example.chapter4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.chapter4.service.NormalService;

import java.text.SimpleDateFormat;

public class ServiceNormalActivity extends AppCompatActivity implements View.OnClickListener {

    private static SimpleDateFormat sdf;
    private Button startServiceButton;
    private Button stopServiceButton;
    private static TextView showServiceTextView;
    private static String showText;
    private Intent intent;

    public static void setText(String text) {
        String time = sdf.format(System.currentTimeMillis());

        showText = String.format("%s%s %s\n", showText, time, text);
        showServiceTextView.setText(showText);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_normal);

        sdf = new SimpleDateFormat("HH:mm:ss");
        startServiceButton = findViewById(R.id.startServiceButton);
        stopServiceButton = findViewById(R.id.stopServiceButton);
        showServiceTextView = findViewById(R.id.showServiceTextView);
        showText = "";
        intent = new Intent(this, NormalService.class);

        startServiceButton.setOnClickListener(this);
        stopServiceButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.startServiceButton) {
            startService(intent);
        } else if (v.getId() == R.id.stopServiceButton) {
            stopService(intent);
        }
    }
}