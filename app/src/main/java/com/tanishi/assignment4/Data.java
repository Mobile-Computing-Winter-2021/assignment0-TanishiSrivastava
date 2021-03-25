package com.tanishi.assignment4;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.tanishi.assignment4.Temperature;

@Database(entities = {Temperature.class,Accelerometer.class,GPS.class,Light.class,Proximity.class,LinearAcceleration.class},version = 1,exportSchema = false)
public abstract class Data extends RoomDatabase {

    private static Data database;

    private static String DB_NAME = "database";

    public synchronized static Data getInstance(Context context) {

        if(database == null){
            database = Room.databaseBuilder(context.getApplicationContext(),Data.class,DB_NAME)
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return database;

    }

    public abstract TemperatureInterface tempIn();
    public abstract AccelerometerInterface accIn();
    public abstract GPSInterface gpsIn();
    public abstract LightInterface lightIn();
    public abstract ProximityInterface proxIn();
    public abstract LinearAccelerationInterface liaccIn();

}