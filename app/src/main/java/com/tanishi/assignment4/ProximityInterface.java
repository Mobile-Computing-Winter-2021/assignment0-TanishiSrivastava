package com.tanishi.assignment4;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ProximityInterface {
    @Insert
    void insert(Proximity proximity) ;

    @Query("Select * from Proximity")
    List<Proximity> data();

    @Query("delete from Proximity")
    void delete();
}
