package com.tanishi.assignment5;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface RoomDataInterface {
    @Insert
    void insert(RoomData a) ;

    @Query("Select * from RoomData")
    List<RoomData> data();

    @Query("delete from RoomData")
    void delete();
}
