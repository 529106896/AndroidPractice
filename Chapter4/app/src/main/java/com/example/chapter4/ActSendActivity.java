
package com.example.chapter4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.SimpleDateFormat;

public class ActSendActivity extends AppCompatActivity implements View.OnClickListener {

    TextView tvSend;

    SimpleDateFormat sdf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act_send);

        tvSend = findViewById(R.id.sendText);

        sdf = new SimpleDateFormat("HH:mm:ss");

        findViewById(R.id.sendButton).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, ActReceiveActivity.class);
        Bundle bundle = new Bundle();

        bundle.putString("requestTime", sdf.format(System.currentTimeMillis()));
        bundle.putString("requestContent", tvSend.getText().toString());

        intent.putExtras(bundle);

        startActivity(intent);
    }
}