package com.pollysoft.alarmdemo.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class BootCompletedReceiver extends BroadcastReceiver {
    private static final String LOG_TAG = "BootCompletedReceiver";
    public BootCompletedReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        Log.e(LOG_TAG, "》》》》》》》》》监听到开机，服务启动");

        intent.setAction("com.pollysoft.alarmdemo.MyService");
        context.startService(intent);
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
