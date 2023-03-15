package com.example.chapter5;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

public class SwitchActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {

    private Switch testSwitch;
    private TextView switchStatusTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_switch);

        testSwitch = findViewById(R.id.testSwitch);
        switchStatusTextView = findViewById(R.id.switchStatusTextView);

        testSwitch.setOnCheckedChangeListener(this);
    }

    @Override
    public void onCheckedChanged(CompoundButton button, boolean isChecked) {
        String showText = String.format("Switch按钮的状态是%s", isChecked ? "开" : "关");
        switchStatusTextView.setText(showText);
    }
}