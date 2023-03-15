package com.example.chapter4;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

public class ActFinishActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act_finish);

        findViewById(R.id.backButton).setOnClickListener(this);
        findViewById(R.id.finishButton).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        finish();
    }
}