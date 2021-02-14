package com.example.assignment2;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class MyReceiver extends BroadcastReceiver {
    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    public void onReceive(Context context, Intent intent) {
        if(intent.getAction().equals(Intent.ACTION_POWER_DISCONNECTED)){
            Toast.makeText(context,""+"Disconnected",Toast.LENGTH_SHORT).show();
            Log.i(TAG,"onReceive- Power is Disconnected");
        }
        if(intent.getAction().equals(Intent.ACTION_BATTERY_LOW)){
            Toast.makeText(context,""+"Battery Low",Toast.LENGTH_SHORT).show();
            Log.i(TAG,"onReceive- Battery is Low");
        }
        if(intent.getAction().equals(Intent.ACTION_BATTERY_OKAY)){
            Toast.makeText(context,""+"Battery Okay",Toast.LENGTH_SHORT).show();
            Log.i(TAG,"onReceive- Battery is Okay");
        }
    }
}