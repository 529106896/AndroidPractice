package com.example.chapter3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.LinearLayout;

public class ViewMarginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_margin);
        LinearLayout tv_code = findViewById(R.id.tv_code);
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) tv_code.getLayoutParams();
        Log.d("margin值", String.valueOf(marginLayoutParams.topMargin));
//        Log.d("padding值", String.valueOf(tv_code.getPadding()));
    }
}