package com.example.chapter3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.Locale;

public class ButtonStyle extends AppCompatActivity {

    public int buttonClickTimes = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_button_style);
    }

    public void onClickTest(View view) {
        TextView tvTest = findViewById(R.id.onClickResultTextView);
        buttonClickTimes++;
        tvTest.setText(String.format(Locale.CHINA, "您已经点击按钮 %d 次", buttonClickTimes));
    }
}