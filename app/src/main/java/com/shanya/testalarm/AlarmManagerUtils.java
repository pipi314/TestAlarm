package com.shanya.testalarm;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

public class AlarmManagerUtils {

    private static final long TIME_INTERVAL = 5 * 1000;//闹钟执行任务的时间间隔
    private Context context;
    public static AlarmManager am;
    public static PendingIntent pendingIntent;
    //
    private AlarmManagerUtils(Context aContext) {
        this.context = aContext;
    }

    //饿汉式单例设计模式 singleton
    private static AlarmManagerUtils instance = null;

    public static AlarmManagerUtils getInstance(Context aContext) {
        if (instance == null) {
            synchronized (AlarmManagerUtils.class) {
                if (instance == null) {
                    instance = new AlarmManagerUtils(aContext);
                }
            }
        }
        return instance;
    }

    public void createGetUpAlarmManager() {
        am = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent("com.shanya.testalarm.MY_BROADCAST");
        intent.setPackage("com.shanya.testalarm");
        intent.putExtra("msg", "赶紧起床");
        pendingIntent = PendingIntent.getBroadcast(context, 0, intent, 0);//每隔5秒发送一次广播
    }

    @SuppressLint("NewApi")
    public void getUpAlarmManagerStartWork() {
        //版本适配
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {// 6.0及以上
            am.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP,
                    System.currentTimeMillis(), pendingIntent);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {// 4.4及以上
            am.setExact(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(),
                    pendingIntent);
        } else {
            am.setRepeating(AlarmManager.RTC_WAKEUP,
                    System.currentTimeMillis(), TIME_INTERVAL, pendingIntent);
        }
    }

    @SuppressLint("NewApi")
    public void getUpAlarmManagerWorkOnReceiver() {
        //高版本重复设置闹钟达到低版本中setRepeating相同效果
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {// 6.0及以上
            am.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP,
                    System.currentTimeMillis() + TIME_INTERVAL, pendingIntent);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {// 4.4及以上
            am.setExact(AlarmManager.RTC_WAKEUP, System.currentTimeMillis()
                    + TIME_INTERVAL, pendingIntent);
        }
    }
}