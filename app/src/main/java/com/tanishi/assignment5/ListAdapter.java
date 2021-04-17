package com.tanishi.assignment5;

import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ListAdapter extends BaseAdapter {

    private Context context;
    private LayoutInflater inflater;
    private List<ScanResult> wifiList;

    public ListAdapter(Context context,List<ScanResult> wifiList) {
        this.context=context;
        this.wifiList=wifiList;
        inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return wifiList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Holder holder;
        View view=convertView;

        view=inflater.inflate(R.layout.list_item,null);
        holder=new Holder();
        holder.tvDetails=(TextView)view.findViewById(R.id.textWifi);
        holder.rssi=(TextView)view.findViewById(R.id.rssi);
        holder.bssi=view.findViewById(R.id.bssi);
        holder.strength=view.findViewById(R.id.strength);
        view.setTag(holder);

        holder.tvDetails.setText(wifiList.get(position).SSID);
        holder.rssi.setText("RSSI: "+wifiList.get(position).level);
        holder.bssi.setText("BSSID: "+wifiList.get(position).BSSID);


        String name=holder.tvDetails.getText().toString();
        int rss=wifiList.get(position).level;
        int level= WifiManager.calculateSignalLevel(rss,10);
        int percentage =(int)((level/10.0) * 100);
        holder.strength.setText("Strength: "+percentage);

        return view;
    }

   class Holder {
        TextView tvDetails;
        TextView rssi;
        TextView bssi;
        TextView strength;
//        EditText room;
    }
}