package com.example.chapter4;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.SimpleDateFormat;

public class PageSenderActivity extends AppCompatActivity implements View.OnClickListener {

    private SimpleDateFormat sdf;
    private Button sendRequestButton;
    private TextView receiveResponseTextView;
//    private String showText;
    private ActivityResultLauncher launcher;

//    private void setShowText(String text) {
//        showText = String.format("%s%s %s\n", showText, sdf.format(System.currentTimeMillis()), text);
//        receiveResponseTextView.setText(showText);
//    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.sendRequestButton) {
            Intent intent = new Intent(this, PageReceiverActivity.class);

            Bundle bundle = new Bundle();

            bundle.putString("requestTime", sdf.format(System.currentTimeMillis()));
            bundle.putString("requestContent", "到达世界最高城！沈阳！");

            intent.putExtras(bundle);
            launcher.launch(intent);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_sender);

        sdf = new SimpleDateFormat("HH:mm:ss");
        sendRequestButton = findViewById(R.id.sendRequestButton);
        receiveResponseTextView = findViewById(R.id.receiveResponseTextView);

        sendRequestButton.setOnClickListener(this);

        launcher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
           if (result.getResultCode() == RESULT_OK && result.getData() != null) {
               Bundle bundle = result.getData().getExtras();

               String responseTime = bundle.getString("responseTime");
               String responseContent = bundle.getString("responseContent");

               String showText = String.format("收到应答消息：\n应答时间：%s\n应答内容：%s", responseTime, responseContent);
               receiveResponseTextView.setText(showText);
           }
        });
    }
}