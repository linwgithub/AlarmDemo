package com.pollysoft.alarmdemo.receiver;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.pollysoft.alarmdemo.R;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MyReceiver extends BroadcastReceiver {
    public MyReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        String msg = intent.getStringExtra("msg");
        transferLongToDate("yyyy-MM-dd HH:mm:ss", System.currentTimeMillis());
        Log.i("SendReceiver", msg);
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();

        //发出通知，该吃药了
        Notification notifi = new Notification();

    }

    private void showNotify(Context context) {
        Notification notice = new Notification();
        notice.icon = R.mipmap.ic_launcher;
        notice.tickerText = "您有一条新的信息";
        notice.defaults = Notification.DEFAULT_SOUND;
        notice.when = 10L;
        // 100 毫秒延迟后，震动 250 毫秒，暂停 100 毫秒后，再震动 500 毫秒
        notice.vibrate = new long[]{100, 250, 100, 500};

        NotificationManager manager = (NotificationManager)
                context.getSystemService(context.NOTIFICATION_SERVICE);
        //添加paddingIntent来做用户响应。
        manager.notify(0, notice);
    }


    private String transferLongToDate(String dateFormat,Long millSec){
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        Date date= new Date(millSec);
        return sdf.format(date);
    }
}
