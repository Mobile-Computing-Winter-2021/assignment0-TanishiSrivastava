package com.tanishi.assignment4;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;


@Dao
public interface LightInterface {
    @Insert
    void insert(Light light) ;

    @Query("Select * from Light")
    List<Light> data();

    @Query("delete from Light")
    void delete();
}
