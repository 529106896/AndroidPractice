package com.example.chapter5;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;

import com.example.chapter5.util.ViewUtil;

public class EditHideActivity extends AppCompatActivity {

    private EditText inputPhoneNumber;
    private EditText inputPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_hide);

        inputPhoneNumber = findViewById(R.id.inputPhoneNumber);
        inputPassword = findViewById(R.id.inputPassword);

        Log.d("inputPhoneNumber的maxEms: ", String.valueOf(inputPhoneNumber.getMaxEms()));
        Log.d("inputPassword的maxEms: ", String.valueOf(inputPassword.getMaxEms()));

        inputPhoneNumber.addTextChangedListener(new HideTextWatcher(inputPhoneNumber, 11));
        inputPassword.addTextChangedListener(new HideTextWatcher(inputPassword, 8));
    }

    private class HideTextWatcher implements TextWatcher {

        private EditText editText;
        private int maxLength;

        public HideTextWatcher(EditText editText, int maxLength) {
            super();
            this.editText = editText;
            this.maxLength = maxLength;
        }

        public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
        public void onTextChanged(CharSequence s, int start, int before, int count) {}

        public void afterTextChanged(Editable s) {
            String str = s.toString();

            if (str.length() >= maxLength) {
                ViewUtil.hideOneInputMethod(EditHideActivity.this, editText);
            }

//            if (editText.getId() == R.id.inputPhoneNumber) {
//                if (str.length() >= maxLength) {
//                    ViewUtil.hideOneInputMethod(EditHideActivity.this, editText);
//                }
//            } else if (editText.getId() == R.id.inputPassword) {
//                if (str.length() >= maxLength) {
//                    ViewUtil.hideOneInputMethod(EditHideActivity.this, editText);
//                }
//            }
        }
    }
}