package com.shanya.testalarm;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class MyBroadcastReceiver extends BroadcastReceiver {

    private static final String TAG = "MyBroadcastReceiver";

    @SuppressLint("NewApi")
    @Override
    public void onReceive(Context context, Intent intent) {
        //高版本重复设置闹钟达到低版本中setRepeating相同效果
        AlarmManagerUtils.getInstance(context).getUpAlarmManagerWorkOnReceiver();

        String extra = intent.getStringExtra("msg");
        Log.i(TAG, "extra = " + extra);

        Toast.makeText(context,extra,Toast.LENGTH_SHORT).show();
    }
}