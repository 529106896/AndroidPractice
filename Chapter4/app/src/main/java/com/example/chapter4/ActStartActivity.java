package com.example.chapter4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ActStartActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.changeButton).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        // 跳转：startActivity(new Intent(源页面, 目标页面))
        startActivity(new Intent(this, ActFinishActivity.class));
    }
}