package com.example.chapter4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MockRegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private Button registerButton;
//    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mock_register);

        registerButton = findViewById(R.id.registerButton);
//        intent = new Intent(this, MockRegisterResultActivity.class);
        registerButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.registerButton) {
            Intent intent = new Intent(this, MockRegisterResultActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);

            startActivity(intent);
        }
    }
}