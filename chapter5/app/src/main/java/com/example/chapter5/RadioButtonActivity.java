package com.example.chapter5;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.RadioGroup;
import android.widget.TextView;

public class RadioButtonActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener {

    private TextView showRadioGroupResultTextView;
    private RadioGroup sexSelectRadioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_radio_button);

        sexSelectRadioGroup = findViewById(R.id.sexSelectRadioGroup);
        showRadioGroupResultTextView = findViewById(R.id.showRadioGroupResultTextView);

        sexSelectRadioGroup.setOnCheckedChangeListener(this);
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
        if (checkedId == R.id.radioButtonMale) {
            showRadioGroupResultTextView.setText("您选择的性别是男");
        } else if (checkedId == R.id.radioButtonFemale) {
            showRadioGroupResultTextView.setText("您选择的性别是女");
        }
    }
}