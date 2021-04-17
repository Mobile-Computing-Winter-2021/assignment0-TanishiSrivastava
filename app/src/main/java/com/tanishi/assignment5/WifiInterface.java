package com.tanishi.assignment5;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface WifiInterface {
    @Insert
    void insert(WifiData a) ;

    @Query("Select * from Wifi ")
    List<WifiData> data();

    @Query("delete from Wifi")
    void delete();

}
