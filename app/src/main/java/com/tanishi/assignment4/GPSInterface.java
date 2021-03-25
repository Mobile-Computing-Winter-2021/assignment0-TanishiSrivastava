package com.tanishi.assignment4;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface GPSInterface {
    @Insert
    void insert(GPS a) ;

    @Query("Select * from GPS")
    List<GPS> data();

    @Query("delete from GPS")
    void delete();
}
