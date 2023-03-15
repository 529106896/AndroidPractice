package com.example.chapter4.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Vibrator;
import android.util.Log;

public class ShockReceiver extends BroadcastReceiver {

    public static final String SHOCK_ACTION = "com.example.chapter4.shock";

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent != null && intent.getAction().equals(SHOCK_ACTION)) {
            Vibrator vibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
//            Log.d("Vibrator: ", String.valueOf(vibrator.hasVibrator()));
            long [] pattern = {500, 1000, 500, 1000};
//            vibrator.vibrate(500);
            vibrator.vibrate(pattern, -1);
//            Log.d("收到震动广播：", "我要开始震动！");
        }
    }
}