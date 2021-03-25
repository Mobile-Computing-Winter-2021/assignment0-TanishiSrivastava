package com.tanishi.assignment4;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface AccelerometerInterface {
    @Insert
    void insert(Accelerometer a) ;

    @Query("Select * from Accelerometer where timestamp>=:a and timestamp<=:b")
    List<Accelerometer> cal_average(long a, long b);

    @Query("delete from Accelerometer")
    void delete();
}
