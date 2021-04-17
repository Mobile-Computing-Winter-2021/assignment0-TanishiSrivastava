package com.tanishi.assignment5;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

@Database(entities = {WifiData.class,RoomData.class},version = 1,exportSchema = false)
public abstract class Data extends RoomDatabase {

    private static Data database;

    private static String DB_NAME = "WifiData";

    public synchronized static Data getInstance(Context context) {

        if (database == null) {
            database = Room.databaseBuilder(context.getApplicationContext(), Data.class, DB_NAME)
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return database;

    }

    public abstract WifiInterface wifiInterface();
    public abstract RoomDataInterface roomDataInterface();
}


