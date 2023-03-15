package com.example.chapter4;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class ActReceiveActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act_receive);

        TextView tvReceive = findViewById(R.id.receiveText);
        Bundle bundle = getIntent().getExtras();

//        Log.d(this.getClass().getName(), bundle.);

        try {
            String requestTime = bundle.getString("requestTime");
            String requestContent = bundle.getString("requestContent");
//            int a = bundle.getInt("aaa");

            String showText = String.format("收到消息：\n请求时间：%s\n请求内容：%s", requestTime, requestContent);
            tvReceive.setText(showText);
        } catch (Exception e) {
            e.printStackTrace();
        }



    }
}