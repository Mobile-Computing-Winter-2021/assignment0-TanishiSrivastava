package com.tanishi.assignment5;

import android.content.Context;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class BarFragment extends Fragment {
    BarChart barChart;
    BarData barData;
    BarDataSet barDataSet;
    ArrayList<BarEntry> barEntriesArrayList;
    List<ScanResult> wifiList;
    WifiManager wifiManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_bar, container, false);
        barChart = view.findViewById(R.id.barchart);
        Button submit = view.findViewById(R.id.bar_sumbit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<String> name=new ArrayList<>();
                ArrayList<Integer> rss=new ArrayList<>();
                wifiManager=(WifiManager)getActivity().getApplicationContext().getSystemService(Context.WIFI_SERVICE);
                wifiManager.setWifiEnabled(true);
                wifiManager.startScan();
                wifiList = wifiManager.getScanResults();
                for (int i = 0; i < wifiList.size(); i++) {
                    name.add(wifiList.get(i).SSID);
                    rss.add(WifiManager.calculateSignalLevel(wifiList.get(i).level, 10));
                }

                barChart.setVisibility(View.VISIBLE);
                barEntriesArrayList = new ArrayList<>();
                Log.i("hello", "priya");
                for (int i = 0; i < rss.size(); i++) {
                    Log.i("hello", "p" + rss.get(i));
                    barEntriesArrayList.add(new BarEntry(i, rss.get(i)));
                }
                ArrayList<String> labels = new ArrayList<>();
                for (int i = 0; i < name.size(); i++) {
                    labels.add(name.get(i));
                }


                XAxis xAxis = barChart.getXAxis();
                xAxis.setPosition(XAxis.XAxisPosition.BOTTOM_INSIDE);
                ValueFormatter formatter = new ValueFormatter() {


                    @Override
                    public String getFormattedValue(float value) {
                        return labels.get((int) value);
                    }
                };

                xAxis.setGranularity(1f);
                xAxis.setValueFormatter(formatter);

                barDataSet = new BarDataSet(barEntriesArrayList, "Wifi Networks");

                barData = new BarData(barDataSet);
                barChart.setData(barData);
                barChart.setFitBars(true);
                barChart.setTouchEnabled(true);
                barChart.setDragEnabled(true);
                barChart.setScaleEnabled(true);
                barChart.getDescription().setText("Representation of Signal Strength");
                barDataSet.setColors(ColorTemplate.MATERIAL_COLORS);
                barChart.animateY(5000);

            }
        });
        return view;
    }
}