package com.example.chapter4;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.text.SimpleDateFormat;

public class ActResponseActivity extends AppCompatActivity implements View.OnClickListener {

    TextView textReceiveRequest;

    SimpleDateFormat sdf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act_response);

        textReceiveRequest = findViewById(R.id.textReceiveRequest);
        findViewById(R.id.responseButton).setOnClickListener(this);
        sdf = new SimpleDateFormat("HH:mm:ss");

        // 接收上一个活动的消息
        Bundle bundle = getIntent().getExtras();

        // 接收请求时间和请求内容
        String requestTime = bundle.getString("requestTime");
        String requestContent = bundle.getString("requestContent");

        String showRequest = String.format("收到Request：\n请求时间：%s\n请求内容：%s", requestTime, requestContent);

        textReceiveRequest.setText(showRequest);
    }

    @Override
    public void onClick(View v) {
        // 待返回的消息
        String response = "我吃过了，你来我家吃吧";

        Intent intent = new Intent();
        Bundle bundle = new Bundle();

        String responseTime = sdf.format(System.currentTimeMillis());

        bundle.putString("responseTime", responseTime);
        bundle.putString("responseContent", response);

        intent.putExtras(bundle);
        // 携带意图返回上一个活动，RESULT_OK表示处理成功
        setResult(Activity.RESULT_OK, intent);
        finish();
    }
}