package com.example.chapter4;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.SimpleDateFormat;

public class PageReceiverActivity extends AppCompatActivity implements View.OnClickListener {

    private Button sendResponseButton;
    private TextView receiveTextView;
    private SimpleDateFormat sdf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_receiver);

        sendResponseButton = findViewById(R.id.sendResponseButton);
        receiveTextView = findViewById(R.id.receiveTextView);
        sdf = new SimpleDateFormat("HH:mm:ss");

        sendResponseButton.setOnClickListener(this);

        Bundle bundle = getIntent().getExtras();

        String requestTime = bundle.getString("requestTime");
        String requestContent = bundle.getString("requestContent");

        String showText = String.format("收到请求消息：\n请求时间：%s\n请求内容：%s", requestTime, requestContent);
        receiveTextView.setText(showText);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.sendResponseButton) {
            String responseContent = "太美丽了沈阳！";

//            Intent intent = new Intent(this, PageSenderActivity.class);
            Intent intent = new Intent();
            Bundle bundle = new Bundle();

            bundle.putString("responseTime", sdf.format(System.currentTimeMillis()));
            bundle.putString("responseContent", responseContent);

            intent.putExtras(bundle);
            setResult(Activity.RESULT_OK, intent);
            finish();
        }
    }
}