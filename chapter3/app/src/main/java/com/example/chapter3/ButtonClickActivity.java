package com.example.chapter3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Locale;

public class ButtonClickActivity extends AppCompatActivity implements View.OnClickListener {

    TextView buttonTestView;

    // 这里图方便直接在这里写一个长按监听器
    class LongClickListener implements View.OnLongClickListener {
        @Override
        public boolean onLongClick(View v) {
            buttonTestView.setText(String.format(Locale.CHINA, "您长按的按钮是：%s", ((Button) v).getText()));
            return true;
        }
    }

    // 按钮少时可以每一个Button单独实现一个Listener
    // 按钮多的时候就让当前页面实现View.OnClickListener，实现统一的监听器
    @Override
    public void onClick(View v) {
        buttonTestView.setText(String.format(Locale.CHINA, "您短按的按钮是：%s", ((Button) v).getText()));
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_button_click);

        buttonTestView = findViewById(R.id.buttonClickResultTextView);

        Button singleClickButton = findViewById(R.id.singleClickButton);
//        singleClickButton.setOnClickListener(new SingleClickListener());
        singleClickButton.setOnClickListener(this);
        singleClickButton.setOnLongClickListener(new LongClickListener());

        Button publicClickButton = findViewById(R.id.publicClickButton);
        publicClickButton.setOnClickListener(this);
        publicClickButton.setOnLongClickListener(new LongClickListener());
    }


}