package com.example.assignment2;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.IBinder;

import androidx.core.app.NotificationCompat;


public class MyService extends Service {
    private MediaPlayer mediaPlayer;
    public MyService() {
    }
    String Channel_id="Foreground service";

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O) {
            NotificationChannel s = new NotificationChannel(Channel_id, "Service", NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(s);
        }


            Intent notification=new Intent(this,MainActivity.class);
        PendingIntent pend=PendingIntent.getActivity(this,0,notification,0);
        Notification notify=new NotificationCompat.Builder(this,Channel_id).setContentText("Service")
                .setSmallIcon(R.drawable.android)
                .setContentIntent(pend).build();
        startForeground(1,notify);



        String song=intent.getStringExtra("song");
        if(song.equals("1"))
            mediaPlayer = MediaPlayer.create(this, R.raw.tumhiho);
        else if(song.equals("2"))
            mediaPlayer = MediaPlayer.create(this, R.raw.avastha);
        else if(song.equals("3"))
            mediaPlayer = MediaPlayer.create(this, R.raw.ghalatfemi);
        else if(song.equals("4"))
            mediaPlayer = MediaPlayer.create(this, R.raw.kgfsong);

        mediaPlayer.start();
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        mediaPlayer.stop();
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

}