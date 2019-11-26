package com.shanya.testalarm;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;
import java.util.TimeZone;

public class MainActivity extends AppCompatActivity {

    private AlarmManagerUtils alarmManagerUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        alarmManagerUtils = AlarmManagerUtils.getInstance(this);
        alarmManagerUtils.createGetUpAlarmManager();
         //
        Button button = findViewById(R.id.am);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alarmManagerUtils.getUpAlarmManagerStartWork();
//                Intent intent = new Intent("com.shanya.testalarm.MY_BROADCAST");
//                sendBroadcast(intent);
                Toast.makeText(getApplicationContext(),"ss",Toast.LENGTH_SHORT).show();
            }
        });

    }
}
