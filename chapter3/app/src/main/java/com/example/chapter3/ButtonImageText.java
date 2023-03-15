package com.example.chapter3;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.Button;

public class ButtonImageText extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_button_image_text);

        Drawable drawable = getDrawable(R.drawable.icon);
        drawable.setBounds(0, 0, 80, 80);

        Button btn = findViewById(R.id.testButton);
//        btn.setCompoundDrawables(drawable, null, null, null);
        btn.setCompoundDrawables(drawable, null, null, null);
    }
}