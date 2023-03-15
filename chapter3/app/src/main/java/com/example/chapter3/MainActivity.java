package com.example.chapter3;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView textTest1 = findViewById(R.id.textTest1);
        TextView textTest2 = findViewById(R.id.textTest2);
        TextView textTest3 = findViewById(R.id.textTest3);
        TextView textTest4 = findViewById(R.id.textTest4);

        // 设置成绿色可以 Color.GREEN、0xff00ff00
        // 但不推荐 R.color.green
        textTest1.setTextColor(Color.GREEN);
        textTest2.setTextColor(Color.RED);
        textTest3.setTextColor(Color.BLUE);
        textTest4.setBackgroundColor(Color.GREEN);
//        textTest3.setBackgroundResource(R.drawable.avatar);

    }
}