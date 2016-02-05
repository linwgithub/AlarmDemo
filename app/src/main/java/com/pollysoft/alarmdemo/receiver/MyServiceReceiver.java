package com.pollysoft.alarmdemo.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class MyServiceReceiver extends BroadcastReceiver {
    public MyServiceReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
         //唤醒服务
        intent.setAction("com.pollysoft.alarmdemo.MyService");
        context.startService(intent);
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
