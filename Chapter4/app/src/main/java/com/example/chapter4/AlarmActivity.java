package com.example.chapter4;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import java.text.SimpleDateFormat;

public class AlarmActivity extends AppCompatActivity implements View.OnClickListener {

    // 闹钟广播时间的标识符
    private final static String ALARM_ACTION = "com.example.chapter4.alarm";
    // 闹钟时间到达的表述
    private String alarmArriveString = "";
    // 时间
    private SimpleDateFormat sdf;
    // 闹钟按钮
    private Button setAlarmButton;
    // 展示闹钟信息
    private TextView showAlarmInfoTextView;
    // 是否允许重复闹钟
    private CheckBox isAllowRepeatAlarmCheckBox;

    public class AlarmReceiver extends BroadcastReceiver {
        // 收到闹钟时间到达的广播后，就会触发接收器的onReceive方法
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent != null) {
                alarmArriveString = String.format("%s\n%s 闹钟时间到达", alarmArriveString, sdf.format(System.currentTimeMillis()));
                showAlarmInfoTextView.setText(alarmArriveString);

                Vibrator vb = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
                long [] pattern = {500, 1000, 500, 1000};
//                vb.vibrate(pattern, -1);
                vb.vibrate(500);
                if (isAllowRepeatAlarmCheckBox.isChecked()) {
                    sendAlarm();
                }
            }
        }
    }

    private AlarmReceiver alarmReceiver;

    private void sendAlarm() {
        Intent intent = new Intent(ALARM_ACTION);

//        Log.d("Current Android Version:", String.valueOf(Build.VERSION.SDK_INT));

        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_IMMUTABLE);

        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

        long delayTime = System.currentTimeMillis() + 8000;

//        Log.d("delayTime: ", sdf.format(delayTime));000000000000000000000

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            alarmManager.setAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, delayTime, pendingIntent);
        } else {
            alarmManager.set(AlarmManager.RTC_WAKEUP, delayTime, pendingIntent);
        }
    }

    @Override
    public void onClick(View v) {
        sendAlarm();

        alarmArriveString = String.format("%s\n%s 设置闹钟", alarmArriveString, sdf.format(System.currentTimeMillis()));
        showAlarmInfoTextView.setText(alarmArriveString);
    }

    @Override
    public void onStart() {
        super.onStart();
        alarmReceiver = new AlarmReceiver();
        IntentFilter intentFilter = new IntentFilter(ALARM_ACTION);
        registerReceiver(alarmReceiver, intentFilter);
    }

    @Override
    public void onStop() {
        super.onStop();
        unregisterReceiver(alarmReceiver);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);

        sdf = new SimpleDateFormat("HH:mm:ss:SS");
        setAlarmButton = findViewById(R.id.setAlarmButton);
        showAlarmInfoTextView = findViewById(R.id.showAlarmInfoTextView);
        isAllowRepeatAlarmCheckBox = findViewById(R.id.isAllowRepeatAlarmCheckBox);

        setAlarmButton.setOnClickListener(this);
    }


}