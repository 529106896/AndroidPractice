package com.example.chapter5;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class LoginSuccessActivity extends AppCompatActivity implements View.OnClickListener {

    // 展示当前用户电话号码的TextView
    private TextView curUserPhoneNumber;
    // 退出按钮
    private Button exitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_success);

        curUserPhoneNumber = findViewById(R.id.curUserPhoneNumber);
        exitButton = findViewById(R.id.exitButton);

        exitButton.setOnClickListener(this);

        String showPhoneNumber = getIntent().getStringExtra("phoneNumber");
        curUserPhoneNumber.setText(String.format("当前用户：%s", showPhoneNumber));
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.exitButton) {
            finish();
        }
    }
}