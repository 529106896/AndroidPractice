package com.example.chapter5;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EditFocusActivity extends AppCompatActivity implements View.OnFocusChangeListener {

    private EditText inputPhoneNumber;
    private EditText inputPassword;
    private Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_focus);

        inputPhoneNumber = findViewById(R.id.inputPhoneNumber);
        inputPassword = findViewById(R.id.inputPassword);
        loginButton = findViewById(R.id.loginButton);

        inputPhoneNumber.setOnFocusChangeListener(this);
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        if (v.getId() == R.id.inputPhoneNumber) {
            if (!hasFocus) {
                String phoneNumber = inputPhoneNumber.getText().toString();
                if (phoneNumber == null || phoneNumber.isEmpty() || phoneNumber.length() < 11) {
//                inputPhoneNumber.requestFocus();
                    inputPhoneNumber.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            inputPhoneNumber.requestFocus();
                        }
                    }, 200);
                    Toast.makeText(this, "请输入11位手机号码", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

}