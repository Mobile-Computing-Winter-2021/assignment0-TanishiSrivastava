package com.tanishi.assignment5;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "RoomData")
public class RoomData {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "room")
    private String room;

    @ColumnInfo(name="rssi1")
    private int rssi1;

    @ColumnInfo(name="rssi2")
    private int rssi2;

    @ColumnInfo(name="rssi3")
    private int rssi3;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public int getRssi1() {
        return rssi1;
    }

    public void setRssi1(int rssi1) {
        this.rssi1 = rssi1;
    }

    public int getRssi2() {
        return rssi2;
    }

    public void setRssi2(int rssi2) {
        this.rssi2 = rssi2;
    }

    public int getRssi3() {
        return rssi3;
    }

    public void setRssi3(int rssi3) {
        this.rssi3 = rssi3;
    }
}
