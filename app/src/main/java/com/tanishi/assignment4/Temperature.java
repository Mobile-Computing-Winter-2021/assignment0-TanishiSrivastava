package com.tanishi.assignment4;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Temperature")
public class Temperature {

    @ColumnInfo(name="timestamp")
    private long timestamp;

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getTemperature() {
        return temperature;
    }

    public void setTemperature(float temperature) {
        this.temperature = temperature;
    }

    @PrimaryKey(autoGenerate = true)
        private int id;

        @ColumnInfo(name = "temperature")
        private float temperature;


}
