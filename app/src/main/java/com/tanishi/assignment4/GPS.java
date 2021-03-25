package com.tanishi.assignment4;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

//2
@Entity(tableName = "GPS")
public class GPS {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "longtitude")
    private double longtitute;

    @ColumnInfo(name="latitude")
    private double latitude;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getLongtitute() {
        return longtitute;
    }

    public void setLongtitute(double longtitute) {
        this.longtitute = longtitute;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }
}

