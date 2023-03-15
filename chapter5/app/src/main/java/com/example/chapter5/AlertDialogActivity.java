package com.example.chapter5;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class AlertDialogActivity extends AppCompatActivity implements View.OnClickListener {

    private Button showAlertDialogButton;
    private TextView showDialogResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alert_dialog);

        showAlertDialogButton = findViewById(R.id.showAlertDialogButton);
        showDialogResult = findViewById(R.id.showDialogResult);

        showAlertDialogButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.showAlertDialogButton) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("尊敬的用户");
            builder.setMessage("你是否玩过原神这款游戏？");

            builder.setPositiveButton("玩过", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    showDialogResult.setText("我超，op！");
                }
            });

            builder.setNegativeButton("没玩过", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    showDialogResult.setText("不玩原神，就像西方没有了耶路撒冷。");
                }
            });

            AlertDialog alertDialog = builder.create();
            alertDialog.show();
        }
    }
}