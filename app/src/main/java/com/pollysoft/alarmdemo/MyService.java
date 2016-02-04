package com.pollysoft.alarmdemo;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class MyService extends Service {
    private static final String LOG_TAG = "MyService";
    public MyService() {
        Log.e("Logs", "server is run");
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        AlarmManager am = (AlarmManager) getSystemService(ALARM_SERVICE);
        int requestCode = 0x01;
        Intent intent1 = new Intent("com.pollysoft.alarmdemo.MyAlarmService");
        PendingIntent pi = PendingIntent.getService(this, requestCode, intent1, PendingIntent.FLAG_UPDATE_CURRENT);
        long startTime = System.currentTimeMillis() + 5000;
        am.setRepeating(AlarmManager.RTC_WAKEUP, startTime, 10000, pi);
        Log.e(LOG_TAG, "Alarm is set");
        return super.onStartCommand(intent, flags, startId);
    }

    private void showNotify(){
        Notification notice=new Notification();
        notice.icon=R.mipmap.ic_launcher;
        notice.tickerText="您有一条新的信息";
        notice.defaults=Notification.DEFAULT_SOUND;
        notice.when=10L;
        // 100 毫秒延迟后，震动 250 毫秒，暂停 100 毫秒后，再震动 500 毫秒
        notice.vibrate = new long[] { 100, 250, 100, 500 };
        //notice.setLatestEventInfo(this, "通知", "开会啦", PendingIntent.getActivity(this, 0, null, 0));
//        notice.contentIntent =
//                PendingIntent.getActivity(this, 0, new Intent(this, ResultActivity.class));//即将跳转页面，还没跳转
        NotificationManager manager=(NotificationManager)getSystemService(this.NOTIFICATION_SERVICE);
        manager.notify(0, notice);
    }
}
