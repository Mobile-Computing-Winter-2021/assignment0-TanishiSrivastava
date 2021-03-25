package com.tanishi.assignment4;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Accelerometer")
public class Accelerometer {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name="timestamp")
    private long timestamp;

    @ColumnInfo(name = "x")
    private float x;
    @ColumnInfo(name = "y")
    private float y;
    @ColumnInfo(name = "z")

    private float z;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getZ() {
        return z;
    }

    public void setZ(float z) {
        this.z = z;
    }
}
