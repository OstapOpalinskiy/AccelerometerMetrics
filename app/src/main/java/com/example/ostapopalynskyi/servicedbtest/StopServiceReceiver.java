package com.example.ostapopalynskyi.servicedbtest;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by ostap.opalynskyi on 22.03.2017.
 */

public class StopServiceReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(MyService.STOP_SERVICE_ACTION)) {
            Intent stopServiceIntent = new Intent(context, MyService.class);
            context.stopService(stopServiceIntent);
        }
    }
}
