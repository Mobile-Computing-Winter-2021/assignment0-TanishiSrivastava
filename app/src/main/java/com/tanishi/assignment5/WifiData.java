package com.tanishi.assignment5;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Wifi")
public class WifiData {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "room")
    private String room;

    @ColumnInfo(name="WifiName")
    private String WifiName;

    @ColumnInfo(name="rssi")
    private int rssi;

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

    public String getWifiName() {
        return WifiName;
    }

    public void setWifiName(String wifiName) {
        WifiName = wifiName;
    }

    public int getRssi() {
        return rssi;
    }

    public void setRssi(int rssi) {
        this.rssi = rssi;
    }
}
