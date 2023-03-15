package com.example.chapter4;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.text.SimpleDateFormat;

public class RegisterResultActivity extends AppCompatActivity implements View.OnClickListener {

    ActivityResultLauncher activityResultLauncher;
    SimpleDateFormat sdf;
    TextView textSendRequest;
    TextView textReceiveResponse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_result);

        textReceiveResponse = findViewById(R.id.textReceiveResponse);
        findViewById(R.id.requestButton).setOnClickListener(this);
        sdf = new SimpleDateFormat("HH:mm:ss");
        activityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
            if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                Bundle bundle = result.getData().getExtras();

                String responseTime = bundle.getString("responseTime");
                String responseContent = bundle.getString("responseContent");

                String showResponse = String.format("收到返回消息：\n响应时间：%s\n响应内容：%s\n", responseTime, responseContent);

                textReceiveResponse.setText(showResponse);
            }
        });
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, ActResponseActivity.class);
        Bundle bundle = new Bundle();

        String requestTime = sdf.format(System.currentTimeMillis());
        String requestContent = "你好吗？我很好。";

        bundle.putString("requestTime", requestTime);
        bundle.putString("requestContent", requestContent);

        intent.putExtras(bundle);

        activityResultLauncher.launch(intent);
    }
}