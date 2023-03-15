package com.example.chapter4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.text.SimpleDateFormat;

public class ActLifeActivity extends AppCompatActivity implements View.OnClickListener {

    final static String TAG = "ActLifeActivity";
    String showText = "";
    TextView tvLife;
    SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss:SS");

    public void refreshTextView(String act) {
        showText = String.format("%s%s %s %s\n", showText, df.format(System.currentTimeMillis()), TAG, act);
        tvLife.setText(showText);

        Log.d("活动状态：\n", showText);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act_life);

        tvLife = findViewById(R.id.textLife);
        findViewById(R.id.nextPageButton).setOnClickListener(this);

        refreshTextView("OnCreate");
    }

    @Override
    public void onStart() {
        super.onStart();
        refreshTextView("OnStart");
    }

    @Override
    public void onStop() {
        super.onStop();
        refreshTextView("OnStop");
    }

    @Override
    public void onResume() {
        super.onResume();
        refreshTextView("OnResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        refreshTextView("OnPause");
    }

    @Override
    public void onRestart() {
        super.onRestart();
        refreshTextView("OnRestart");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        refreshTextView("OnDestroy");
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.nextPageButton) {
            startActivity(new Intent(this, ActNextActivity.class));
        }
    }
}