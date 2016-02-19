package com.pollysoft.alarmdemo.service;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.view.View;

import com.pollysoft.alarmdemo.activity.ResultActivity;
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
        //if ()
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

    //设置定时任务
    public void setAlarms(View view) {
        // 创建Intent对象，action指向广播接收类，附加信息为字符串“你该打酱油了”
//        Intent intent = new Intent("com.dwtedx.MyReceiver");
        Intent intent = new Intent(this, ResultActivity.class);
        intent.putExtra("msg", "你该起床了");
        // 创建PendingIntent对象封装Intent，由于是使用广播，注意使用getBroadcast方法
        PendingIntent pi = PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        // 获取AlarmManager对象
        AlarmManager am = (AlarmManager) getSystemService(ALARM_SERVICE);

        am.cancel(pi);
        // 设置闹钟从当前时间开始，每隔10分钟执行一次PendingIntent对象，注意第一个参数与第二个参数的关系
        am.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), 5 * 1000, pi);
    }

    MyServiceReceiver mSR = new MyServiceReceiver();
    @Override
    public void onDestroy() {
        super.onDestroy();

        if (CLOSSTYPE == 0) {//不是主动关闭
            //发送广播再次唤醒服务
            String receiverAction = "com.pollysoft.alarmdemo.receiver.MyServiceReceiver";
            Intent intent = new Intent();
            intent.setAction(receiverAction);
            sendBroadcast(intent);
            unregisterReceiver(mSR);
        }

    }
}
