package com.example.chapter4;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.text.SimpleDateFormat;

public class ActRequestActivity extends AppCompatActivity implements View.OnClickListener {

    SimpleDateFormat sdf;
    TextView textReceiveResponse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act_request);

        textReceiveResponse = findViewById(R.id.textReceiveResponse);
        sdf = new SimpleDateFormat("HH:mm:ss");

        findViewById(R.id.requestButton).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        // 待发送的消息
        String request = "你吃饭了吗？来我家吃吧";

        // 点击按钮后跳转到ActResponseActivity页面
        Intent intent = new Intent(this, ActResponseActivity.class);
        Bundle bundle = new Bundle();

        // 放入发送时间和发送内容
        bundle.putString("requestTime", sdf.format(System.currentTimeMillis()));
        bundle.putString("requestContent", request);

        intent.putExtras(bundle);

        // 期望接收下一个活动的返回数据，第二个参数为本次请求代码
        startActivityForResult(intent, 0);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);

        // 意图非空，且请求代码为之前的0，且结果代码为成功
        if (intent != null && requestCode == 0 && resultCode == Activity.RESULT_OK) {
            Bundle bundle = intent.getExtras();

            String responseTime = bundle.getString("responseTime");
            String responseContent = bundle.getString("responseContent");

            String showResponse = String.format("收到Response：\n响应时间：%s\n响应内容：%s", responseTime, responseContent);

            textReceiveResponse.setText(showResponse);
        }
    }
}