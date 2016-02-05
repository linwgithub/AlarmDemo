package com.pollysoft.alarmdemo.service;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;

import com.pollysoft.alarmdemo.R;
import com.pollysoft.alarmdemo.activity.ResultActivity;

public class MyAlarmService extends Service {
    public MyAlarmService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e("TAG", "MyAlarmService is run");
//        Intent intentForActivi = new Intent(this, ResultActivity.class);
//        intentForActivi.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        startActivity(intentForActivi);

        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        PendingIntent pendingIntent3 = PendingIntent.getActivity(this, 0,
                new Intent(this, ResultActivity.class), 0);

        Notification notify3 = new Notification.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setTicker("TickerText:" + "您有新短消息，请注意查收！")
                .setContentTitle("Notification Title")
                .setContentText("This is the notification message")
                .setContentIntent(pendingIntent3).setNumber(1)
                .build();
        notify3.flags |= Notification.FLAG_AUTO_CANCEL; // FLAG_AUTO_CANCEL表明当通知被用户点击时，通知将被清除。
        manager.notify(1, notify3);// 步骤4：通过通知管理器来发起通知。如果id不同，则每click，在status哪里增加一个提示

        /*
        // "自定义通知：您有新短信息了，请注意查收！", System.currentTimeMillis());
        Notification myNotify = new Notification();
        myNotify.icon = R.drawable.message;
        myNotify.tickerText = "TickerText:您有新短消息，请注意查收！";
        myNotify.when = System.currentTimeMillis();
        myNotify.flags = Notification.FLAG_NO_CLEAR;// 不能够自动清除
        RemoteViews rv = new RemoteViews(getPackageName(),
                R.layout.my_notification);
        rv.setTextViewText(R.id.text_content, "hello wrold!");
        myNotify.contentView = rv;
        Intent intent = new Intent(Intent.ACTION_MAIN);
        PendingIntent contentIntent = PendingIntent.getActivity(this, 1,
                intent, 1);
        myNotify.contentIntent = contentIntent;
        manager.notify(NOTIFICATION_FLAG, myNotify);
        */
        return super.onStartCommand(intent, flags, startId);
    }
}
