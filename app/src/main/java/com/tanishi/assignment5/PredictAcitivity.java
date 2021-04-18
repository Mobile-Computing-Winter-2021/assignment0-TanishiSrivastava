package com.tanishi.assignment5;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Context;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class PredictAcitivity extends AppCompatActivity{
    Button start_scan, stop_scan, predict;
    TextView showresult;
    Spinner sp;
    List<ScanResult> wifiList;
    ListView listView;
    WifiManager wifiManager;
    String wifi1="dlink-A550";
    String wifi2="dlink-A550-5GHz";
//    String wifi3="Galaxy A519D6E";
    ArrayAdapter<String> arrayAdapter;
    ArrayList<Integer> rss1=new ArrayList<>();
    ArrayList<Integer> rss2=new ArrayList<>();
//    ArrayList<Integer> rss3=new ArrayList<>();
    private List<String> options;
    int flag=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_predict);

        start_scan = findViewById(R.id.scan_wifi);
        stop_scan = findViewById(R.id.stop_scan);
        sp = findViewById(R.id.spinner);
        listView=findViewById(R.id.show_k);
        showresult = findViewById(R.id.printresult);

        start_scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flag=1;
                wifiManager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
                wifiManager.setWifiEnabled(true);
                Toast.makeText(PredictAcitivity.this, "WIFI enabled", Toast.LENGTH_SHORT).show();
                wifiManager.startScan();
                wifiList = wifiManager.getScanResults();
                for (int i = 0; i < wifiList.size(); i++) {
                    String name = wifiList.get(i).SSID;
                    int rssi = wifiList.get(i).level;
                    if (wifi1.equals(name)) {
                        rss1.add(rssi);
                    }
                    if (wifi2.equals(name)) {
                        rss2.add(rssi);
                    }
                }
            }
        });
        stop_scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WifiManager wifi = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
                wifi.setWifiEnabled(false);
                Toast.makeText(PredictAcitivity.this, "WIFI disabled", Toast.LENGTH_SHORT).show();
            }
        });

        options = Arrays.asList(getResources().getStringArray(R.array.options));
        ArrayAdapter<String> adapter = new ArrayAdapter<>(PredictAcitivity.this, android.R.layout.simple_spinner_item, options);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp.setAdapter(adapter);


        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (flag == 1) {
                    String option = options.get(position);
                    ArrayList<String> room = new ArrayList<>();
                    ArrayList<Double> copy = new ArrayList<>();
                    ArrayList<Double> score = new ArrayList<>();
                    Data data = Data.getInstance(getApplicationContext());
                    List<RoomData> ls = data.roomDataInterface().data();

                    for (int i = 0; i < rss1.size(); i++) {
                        int x = rss1.get(i);
                        int y = rss2.get(i);
//                        int z = rss3.get(i);
                        for (int j = 0; j < ls.size(); j++) {
                            String RNo = ls.get(i).getRoom();
                            int x1 = ls.get(i).getRssi1(), y1 = ls.get(i).getRssi2(), z1 = ls.get(i).getRssi3();
                            double s = Math.sqrt(Math.pow((x1 - x), 2)) + Math.sqrt(Math.pow((y1 - y), 2));
                            room.add(RNo);
                            score.add(s);
                            copy.add(s);
                        }
                    }
                    List<String> result = new ArrayList<>();
                    Collections.sort(score);

                    for (int i = 0; i < 10; i++) {
                        result.add(room.get(copy.indexOf(score.get(i))));
                    }
                    Log.i("hello","p"+result);
                    if (option.equals("Get top K rooms")) {
                        listView.setVisibility(View.VISIBLE);
                        showresult.setVisibility(View.INVISIBLE);
                        arrayAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, result);
                        listView.setAdapter(arrayAdapter);

                    }
                    if (option.equals("Predict your room")) {
                        listView.setVisibility(View.INVISIBLE);
                        showresult.setVisibility(View.VISIBLE);
                        showresult.setText("You are in "+result.get(0));
                    }

                }
                if(flag==0){
                    Toast.makeText(getApplicationContext(),"Kindly scan wifi of your room",Toast.LENGTH_SHORT).show();
                }
            }

                @Override
                public void onNothingSelected (AdapterView < ? > parent){

                }

        });
    }
}