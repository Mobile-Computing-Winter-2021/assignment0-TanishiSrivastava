package com.example.assignment2;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.nfc.Tag;
import android.os.Build;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

import android.util.Log;
import android.view.View;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    public final String TAG="Fragment";
    MyReceiver receiver;
    IntentFilter i;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FragmentManager fm=getSupportFragmentManager();
        Fragment f=fm.findFragmentById(R.id.fragment_container);
        if(f==null){
            Log.i(TAG,"Null Fragment");
            f=new FirstFragment();
            fm.beginTransaction()
                    .add(R.id.fragment_container,f)
                    .commit();
        }
        Log.i(TAG,"Inflating");
        receiver=new MyReceiver();
        i=new IntentFilter();
        this.registerReceiver(receiver,new IntentFilter(getIntent().ACTION_POWER_DISCONNECTED));
        this.registerReceiver(receiver,new IntentFilter(getIntent().ACTION_BATTERY_LOW));
        this.registerReceiver(receiver,new IntentFilter(getIntent().ACTION_BATTERY_OKAY));

    }
}
