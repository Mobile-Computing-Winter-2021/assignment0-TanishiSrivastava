package com.tanishi.assignment4;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface TemperatureInterface {

    @Insert
    void insert(Temperature t) ;

    @Query("Select * from Temperature where timestamp>=:a and timestamp<=:b")
    List<Temperature> cal_avg(Long a,Long b);

    @Query("delete from Temperature")
    void delete();
}
