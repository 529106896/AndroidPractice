package com.example.chapter5;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class DrawableShapeActivity extends AppCompatActivity implements View.OnClickListener {

    private View vContent;
    private Button changeShapeButton;
    private boolean isClick;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawable_shape);

        vContent = findViewById(R.id.vContent);
        changeShapeButton = findViewById(R.id.changeShapeButton);
        isClick = true;

        vContent.setBackgroundResource(R.drawable.shape_rect_gold);
        changeShapeButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.changeShapeButton) {
            isClick = !isClick;
            if (isClick == true) {
                vContent.setBackgroundResource(R.drawable.shape_rect_gold);
            } else {
                vContent.setBackgroundResource(R.drawable.shape_oval_rose);
            }
        }
    }
}