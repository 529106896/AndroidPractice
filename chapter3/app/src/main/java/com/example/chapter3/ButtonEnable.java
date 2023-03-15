package com.example.chapter3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ButtonEnable extends AppCompatActivity implements View.OnClickListener {

    Button effectShowButton;
    TextView currentButtonStatusTextView;

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.enableButton) {
            effectShowButton.setEnabled(true);
            currentButtonStatusTextView.setText("现在测试按钮被启用");
        } else if (v.getId() == R.id.disableButton) {
            effectShowButton.setEnabled(false);
            currentButtonStatusTextView.setText("现在测试按钮被禁用");
        } else if (v.getId() == R.id.effectShowButton) {
            currentButtonStatusTextView.setText("您点击了测试按钮");
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_button_enable);

        effectShowButton = findViewById(R.id.effectShowButton);
        currentButtonStatusTextView = findViewById(R.id.currentButtonStatusTextView);

        Button enableButton = findViewById(R.id.enableButton);
        Button disableButton = findViewById(R.id.disableButton);

        enableButton.setOnClickListener(this);
        disableButton.setOnClickListener(this);
        effectShowButton.setOnClickListener(this);
    }
}