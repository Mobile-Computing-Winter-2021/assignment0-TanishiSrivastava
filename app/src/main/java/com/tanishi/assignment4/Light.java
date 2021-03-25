package com.tanishi.assignment4;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Light")
public class Light {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "light")
    private float light;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getLight() {
        return light;
    }

    public void setLight(float light) {
        this.light = light;
    }
}
