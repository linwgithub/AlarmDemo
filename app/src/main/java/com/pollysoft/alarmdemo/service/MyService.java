package com.pollysoft.alarmdemo.service;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import com.pollysoft.alarmdemo.R;
import com.pollysoft.alarmdemo.receiver.MyServiceReceiver;

public class MyService extends Service {
    private static final String LOG_TAG = "MyService";

    private static final int CLOSSTYPE = 0;

    /*private MyServerReceiver receiver;
    private class MyServerReceiver extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            //启动服务
        }
    }*/

    public MyService() {

    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        // 获取数据
        String strName = intent.getStringExtra("name");
        // ... 数据库操作
        new Thread(new Runnable() {
            @Override
            public void run() {
                //耗时的操作
            }
        }).run();
//        if ()
        //判断是否开启服务

        return Service.START_STICKY;

        /*AlarmManager am = (AlarmManager) getSystemService(ALARM_SERVICE);
        int requestCode = 0x01;
        Intent intent1 = new Intent("com.pollysoft.alarmdemo.service.MyAlarmService");
        PendingIntent pi = PendingIntent.getService(this, requestCode, intent1, PendingIntent.FLAG_UPDATE_CURRENT);
        long startTime = System.currentTimeMillis() + 5000;
        am.setRepeating(AlarmManager.RTC_WAKEUP, startTime, 10000, pi);
        Log.e(LOG_TAG, "Alarm is set");
        return START_STICKY;*/
    }

    private void showNotify() {
        Notification notice = new Notification();
        notice.icon = R.mipmap.ic_launcher;
        notice.tickerText = "您有一条新的信息";
        notice.defaults = Notification.DEFAULT_SOUND;
        notice.when = 10L;
        // 100 毫秒延迟后，震动 250 毫秒，暂停 100 毫秒后，再震动 500 毫秒
        notice.vibrate = new long[]{100, 250, 100, 500};

        NotificationManager manager = (NotificationManager) getSystemService(this.NOTIFICATION_SERVICE);
        manager.notify(0, notice);
    }

    MyServiceReceiver mSR = new MyServiceReceiver();
    @Override
    public void onDestroy() {
        super.onDestroy();

        if (CLOSSTYPE == 0) {//不是主动关闭

            String receiverAction = "com.pollysoft.alarmdemo.receiver.MyServiceReceiver";
            Intent intent = new Intent();
            intent.setAction(receiverAction);
            sendBroadcast(intent);
            unregisterReceiver(mSR);
        }

    }
}
