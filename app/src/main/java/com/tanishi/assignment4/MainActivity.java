package com.tanishi.assignment4;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Looper;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationAvailability;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.util.List;


public class MainActivity extends AppCompatActivity implements SensorEventListener{

    private Sensor linearacc,gravity,light,temp,proxi,acc;
    private FusedLocationProviderClient client;
    private SensorManager sensorManager;
    private double longtitude,latitude;
    private float x,y,z;
    private float xx,yy,zz;
    private float t,pro,li;
    private Context context;
    private Switch Toglinear,Togtemp,Toglight,Togprox,Toggps,Togacc;
    private Data data;
    private Button b1,b2;
    private TextView average;
    private float[] grav;
    private float accel,accCurr,accLast;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sensorManager=(SensorManager) getSystemService(SENSOR_SERVICE);

        //sensor initialization
        acc=sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        proxi=sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
        temp=sensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE);
        light=sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        gravity = sensorManager.getDefaultSensor(Sensor.TYPE_GRAVITY);
        linearacc = sensorManager.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION);
        Toglinear = findViewById(R.id.toglinear);
        Togtemp=findViewById(R.id.togtemp);
        Togprox=findViewById(R.id.togprox);
        Toglight=findViewById(R.id.toglight);
        Togacc=findViewById(R.id.togacc);
        Toggps=findViewById(R.id.toggps);
        data=Data.getInstance(this);
        client= LocationServices.getFusedLocationProviderClient(this);
        b1=findViewById(R.id.button);
        b2=findViewById(R.id.button2);
        average=findViewById(R.id.textView);
        context=this;

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Accelerometer> list=data.accIn().cal_average(System.currentTimeMillis()-360000,System.currentTimeMillis());
                long sum_a=0,sum_b=0,sum_c=0;
                for(int i=0;i<list.size();i++){
                    sum_a+=list.get(i).getX();
                    sum_b+=list.get(i).getY();
                    sum_c+=list.get(i).getZ();
                }
                long a=sum_a/(list.size()),b=sum_b/(list.size()),c=sum_c/(list.size());
                average.setText("X="+a+", Y="+b+"& Z="+c);
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Temperature> list=data.tempIn().cal_avg(System.currentTimeMillis()-360000,System.currentTimeMillis());
                long sum=0;
                for(int i=0;i<list.size();i++){
                    sum+=list.get(i).getTemperature();
                }
                long avg=sum/(list.size());
                average.setText("Value= "+avg);
            }
        });



        /*
        1. Linear acceleration
         */
        Toglinear.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    Toast.makeText(context,"ON.......",Toast.LENGTH_SHORT).show();
                    sensorManager.registerListener(MainActivity.this,gravity,SensorManager.SENSOR_DELAY_NORMAL);
                    sensorManager.registerListener(MainActivity.this,linearacc,SensorManager.SENSOR_DELAY_NORMAL);
                    Toast.makeText(context,"Registered.....",Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(context,"OFF.......",Toast.LENGTH_SHORT).show();
                    sensorManager.unregisterListener(MainActivity.this,linearacc);
                }
            }
        });
        /*
        2. temperature
         */
        Togtemp.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    Toast.makeText(context,"ON.......",Toast.LENGTH_SHORT).show();
                    sensorManager.registerListener(MainActivity.this,temp,SensorManager.SENSOR_DELAY_NORMAL);
                    Toast.makeText(context,"Registered.....",Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(context,"OFF.......",Toast.LENGTH_SHORT).show();
                    sensorManager.unregisterListener(MainActivity.this,temp);
                }
            }
        });

        /*
        3. Proximity
         */
        Togprox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    Toast.makeText(context,"ON.......",Toast.LENGTH_SHORT).show();
                    sensorManager.registerListener(MainActivity.this,proxi,SensorManager.SENSOR_DELAY_NORMAL);
                    Toast.makeText(context,"Registered.....",Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(context,"OFF.......",Toast.LENGTH_SHORT).show();
                    sensorManager.unregisterListener(MainActivity.this,proxi);
                }
            }
        });
        /*
        4. Light
         */

        Toglight.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    Toast.makeText(context,"ON.......",Toast.LENGTH_SHORT).show();
                    sensorManager.registerListener(MainActivity.this,light,SensorManager.SENSOR_DELAY_NORMAL);
                    Toast.makeText(context,"Registered.....",Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(context,"OFF.......",Toast.LENGTH_SHORT).show();
                    sensorManager.unregisterListener(MainActivity.this,light);
                }
            }
        });

        /*
        5. Accelerometer
         */
        Togacc.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    Toast.makeText(context,"ON.......",Toast.LENGTH_SHORT).show();
                    sensorManager.registerListener(MainActivity.this,acc,SensorManager.SENSOR_DELAY_NORMAL);
                    Toast.makeText(context,"Registered.....",Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(context,"OFF.......",Toast.LENGTH_SHORT).show();
                    sensorManager.unregisterListener(MainActivity.this,acc);
                }
            }
        });


        /*
        6. GPS
         */
        Toggps.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION)
                        == PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(context,Manifest.permission.ACCESS_COARSE_LOCATION)
                        ==PackageManager.PERMISSION_GRANTED) {
                    //Toast.makeText(context,"GPS mode on",Toast.LENGTH_SHORT).show();
                    getCurrentLocation();
                }
                else{
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.ACCESS_COARSE_LOCATION},100);
                    }
                }
            }
        });

    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults)
    {
        super.onRequestPermissionsResult(requestCode,permissions,grantResults);
        if(requestCode==100 && (grantResults.length>0) && (grantResults[0]+grantResults[1]==PackageManager.PERMISSION_GRANTED)){
            getCurrentLocation();
        }
        else{
            Toast.makeText(context,"Permission denied",Toast.LENGTH_SHORT).show();
        }
    }

    @SuppressLint("MissingPermission")
    private void getCurrentLocation() {
        LocationManager locationManager=(LocationManager)this.getSystemService(Context.LOCATION_SERVICE);
        if(locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
            client.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() {
                @Override
                public void onComplete(@NonNull Task<Location> task) {
                    Location location = task.getResult();
                    Log.i("location","value"+location);
                    if (location != null) {
                        latitude = location.getLatitude();
                        longtitude = location.getLongitude();
                        GPS g=new GPS();
                        g.setLatitude(latitude);
                        g.setLongtitute(longtitude);
                        Toast.makeText(context,"data-"+longtitude+latitude,Toast.LENGTH_SHORT).show();
                        data.gpsIn().insert(g);
                    } else {
                        Toast.makeText(context,"GPS mode on 2",Toast.LENGTH_SHORT).show();
                        LocationRequest locationRequest = new LocationRequest()
                                .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
                                .setInterval(10000)
                                .setFastestInterval(1000)
                                .setNumUpdates(1);
                        LocationCallback locationCallback = new LocationCallback() {
                            @Override
                            public void onLocationResult(LocationResult locationResult) {
                                Location location1 = locationResult.getLastLocation();
                                Log.i("location","value"+location1.getLatitude());
                                latitude = location1.getLatitude();
                                longtitude = location1.getLongitude();
                                GPS g=new GPS();
                                g.setLatitude(latitude);
                                g.setLongtitute(longtitude);
                                data.gpsIn().insert(g);
                                Toast.makeText(context,"data-"+longtitude+latitude,Toast.LENGTH_SHORT).show();
                            }

                        };
                        client.requestLocationUpdates(locationRequest, locationCallback
                                , Looper.myLooper());

                    }
                }
            });
        }
        else{
            startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
                    .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
        }
}

    @Override
    public void onSensorChanged(SensorEvent event) {
        float[] g = new float[3];
        float alpha = 0;
        try {
            switch (event.sensor.getType()) {
                case Sensor.TYPE_GRAVITY:
                    g[0] = alpha*g[0] + (1-alpha)*event.values[0];
                    g[1] = alpha*g[1] + (1-alpha)*event.values[1];
                    g[2] = alpha*g[2] + (1-alpha)*event.values[2];
                    break;
                case Sensor.TYPE_LINEAR_ACCELERATION:
                    x = event.values[0] - g[0];
                    y = event.values[1] - g[1];
                    z = event.values[2] - g[2];
                    Log.i("hello", String.valueOf(x) + " " + String.valueOf(y) + " " + String.valueOf(z));
                    LinearAcceleration linearAcceleration = new LinearAcceleration();
                    linearAcceleration.setX(x);
                    linearAcceleration.setY(y);
                    linearAcceleration.setZ(z);
                    data.liaccIn().insert(linearAcceleration);
                    break;
                case Sensor.TYPE_PROXIMITY:
                    pro=event.values[0];
                    Proximity proximity=new Proximity();
                    proximity.setProximity(pro);
                    data.proxIn().insert(proximity);
                    break;
                case Sensor.TYPE_LIGHT:
                    li=event.values[0];
                    Light obj=new Light();
                    obj.setLight(li);
                    data.lightIn().insert(obj);
                    break;
                case Sensor.TYPE_AMBIENT_TEMPERATURE:
                    t=event.values[0];
                    Temperature temperature=new Temperature();
                    temperature.setTemperature(t);
                    temperature.setTimestamp(System.currentTimeMillis());
                    data.tempIn().insert(temperature);
                    break;
                case Sensor.TYPE_ACCELEROMETER:
                    xx=event.values[0];
                    yy=event.values[1];
                    zz=event.values[2];
                    Accelerometer accelerometer=new Accelerometer();
                    accelerometer.setX(xx);
                    accelerometer.setY(yy);
                    accelerometer.setZ(zz);
                    accelerometer.setTimestamp(System.currentTimeMillis());
                    data.accIn().insert(accelerometer);
                    grav=event.values.clone();
                    accLast=accCurr;
                    float var1=grav[0];
                    float var2=grav[1];
                    float var3=grav[2];
                    accCurr=(float)Math.sqrt(var1*var1+var2*var2+var3*var3);
                    float diff=accCurr-accLast;
                    accel=accel*0.9f+diff;
                    if(accel>4){
                        Toast.makeText(context,"Motion state",Toast.LENGTH_SHORT).show();
                    }
                    break;
                default:
                    break;
            }
        } catch (Exception e) {

        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}