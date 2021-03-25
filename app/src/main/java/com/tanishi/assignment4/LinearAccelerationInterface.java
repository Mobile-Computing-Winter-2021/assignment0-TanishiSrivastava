package com.tanishi.assignment4;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;
@Dao
public interface LinearAccelerationInterface {
    @Insert
    void insert(LinearAcceleration linearAcceleration) ;

    @Query("Select * from LinearAcceleration")
    List<LinearAcceleration> data();

    @Query("delete from LinearAcceleration")
    void delete();
}
