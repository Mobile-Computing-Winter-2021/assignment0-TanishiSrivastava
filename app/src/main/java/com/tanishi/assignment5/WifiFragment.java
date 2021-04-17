package com.tanishi.assignment5;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.room.Room;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.Toast;

import java.io.FileOutputStream;
import java.util.List;



public class WifiFragment extends Fragment {
    WifiManager wifiManager;
    WifiReceiver wifiReceiver;
    ListAdapter listAdapter;
    ListView wifiList;
    List wifi;
    Button submit;
    EditText room;
    Switch sw;
    public static int flag=0;
    Button predict;



    @SuppressLint("WifiManagerLeak")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_wifi, container, false);
        submit=view.findViewById(R.id.submit);
        room=view.findViewById(R.id.myroom);
        sw=view.findViewById(R.id.switch_wifi);
        predict=view.findViewById(R.id.predict);
        predict.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getActivity(),PredictAcitivity.class);
                startActivity(i);
            }
        });


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flag=1;
                WifiManager wifi = (WifiManager) getActivity().getSystemService(Context.WIFI_SERVICE);
                wifi.setWifiEnabled(true);
                wifiList=(ListView)view.findViewById(R.id.mylist);
                wifiManager=(WifiManager)getActivity().getSystemService(Context.WIFI_SERVICE);
                wifiReceiver=new WifiReceiver(getContext(),wifiManager,room);
                getActivity().registerReceiver(wifiReceiver,new IntentFilter(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION));

                if(ContextCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION)!=
                        PackageManager.PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions(getActivity(),new String[]{Manifest.permission.ACCESS_FINE_LOCATION},0);
                }
                else{
                    scanWifiList();

                }
                sw.setChecked(true);
                Toast.makeText(getContext(),"WIFI enabled",Toast.LENGTH_SHORT).show();
            }
        });
        sw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    Toast.makeText(getContext(),"WIFI enabled",Toast.LENGTH_SHORT).show();
                    flag=1;
                    wifiList=(ListView)view.findViewById(R.id.mylist);
                    wifiManager=(WifiManager)getActivity().getSystemService(Context.WIFI_SERVICE);
                    wifiManager.setWifiEnabled(true);
                    wifiReceiver=new WifiReceiver(getContext(),wifiManager,room);
                    getActivity().registerReceiver(wifiReceiver,new IntentFilter(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION));

                    if(ContextCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION)!=
                            PackageManager.PERMISSION_GRANTED){
                        ActivityCompat.requestPermissions(getActivity(),new String[]{Manifest.permission.ACCESS_FINE_LOCATION},0);
                    }
                    else{
                        scanWifiList();

                    }
                }
                else{
                    flag=0;
                    WifiManager wifi = (WifiManager)getActivity().getSystemService(Context.WIFI_SERVICE);
                    wifi.setWifiEnabled(false);
                    Toast.makeText(getContext(),"WIFI disabled",Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;
    }

    private void scanWifiList() {
        wifiManager.startScan();
        wifi=wifiManager.getScanResults();
        setAdapter();
    }

    private void setAdapter() {

        listAdapter=new ListAdapter(getContext(),wifi);
        wifiList.setAdapter(listAdapter);
    }

    class WifiReceiver extends BroadcastReceiver{
        List<ScanResult> wifiList;
        Data data;
        Context context;
        WifiManager wifiManager;
        EditText room;
        String wifi1="dlink-A550";
        String wifi2="dlink-A550-5GHz";
        String wifi3="Galaxy A519D6E";
        public WifiReceiver(Context context,WifiManager wifiManager,EditText room) {
            this.context=context;
            this.wifiManager=wifiManager;
            this.room=room;
        }

        @Override
        public void onReceive(Context context, Intent intent) {
            if(flag==1) {
                String file="wifi_data.csv";
                try {
                    FileOutputStream out=getActivity().openFileOutput(file,Context.MODE_APPEND);
                    //Writing data to a csv file
                    data = Data.getInstance(context);
                    wifiManager.startScan();
                    wifiList = wifiManager.getScanResults();
                    for (int i = 0; i < wifiList.size(); i++) {
                        WifiData wifiData = new WifiData();
                        String roomNo = room.getText().toString();
                        String name = wifiList.get(i).SSID;
                        int rssi = wifiList.get(i).level;
                        wifiData.setWifiName(name);
                        wifiData.setRssi(rssi);
                        wifiData.setRoom(roomNo);
                        data.wifiInterface().insert(wifiData);
                        String line=String.valueOf(i)+","+name+","+String.valueOf(rssi)+","+roomNo+"\n";
                        out.write(line.getBytes());

                        RoomData roomData=new RoomData();
                        if(wifi1.equals(name)){
                            roomData.setRoom(roomNo);
                            roomData.setRssi1(rssi);
                            data.roomDataInterface().insert(roomData);
                        }
                        if(wifi2.equals(name)){
                            roomData.setRoom(roomNo);
                            roomData.setRssi2(rssi);
                            data.roomDataInterface().insert(roomData);
                        }
                        if(wifi3.equals(name)){
                            roomData.setRoom(roomNo);
                            roomData.setRssi3(rssi);
                            data.roomDataInterface().insert(roomData);
                        }

                    }
                    out.close();
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }


        }
    }
}
