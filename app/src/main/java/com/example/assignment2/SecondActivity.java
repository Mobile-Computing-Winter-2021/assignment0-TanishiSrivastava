package com.example.assignment2;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class SecondActivity extends AppCompatActivity {
    Button download, check, play,stop2;
    ProgressDialog prog;
    String link = "http://faculty.iiitd.ac.in/~mukulika/s1.mp3";
    Boolean flag=false;
    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        download = (Button) findViewById(R.id.download1);
        check = (Button) findViewById(R.id.check);
        play=(Button)findViewById(R.id.play);
        stop2=(Button)findViewById(R.id.stop2);


        download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
                    if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                            == PackageManager.PERMISSION_DENIED) {
                        Log.d("permission", "permission denied to WRITE_EXTERNAL_STORAGE - requesting it");
                        String[] permissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE};
                        requestPermissions(permissions, 1);
                    }
                }
               new Downloadfile().execute(link);
            }
        });

        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (CheckingNet())
                    Toast.makeText(SecondActivity.this, "Internet is working fine", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(SecondActivity.this, "No network", Toast.LENGTH_SHORT).show();
            }
        });


        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(flag){

                    mediaPlayer = MediaPlayer.create(SecondActivity.this, R.raw.s1);
                    mediaPlayer.start();
                }
                else
                    Toast.makeText(SecondActivity.this,"Please download the song",Toast.LENGTH_SHORT).show();

                }

        });
        stop2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(flag)
                mediaPlayer.stop();
                else
                    Toast.makeText(SecondActivity.this,"No song to stop",Toast.LENGTH_SHORT).show();
            }
        });
    }


    private boolean CheckingNet() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo wifiConn = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        NetworkInfo mobile = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        if ((wifiConn != null && wifiConn.isConnected()) || (mobile != null && mobile.isConnected()))
            return true;
        else
            return false;

    }
    private class Downloadfile extends AsyncTask<String,Integer,String>{
        @Override
        protected void onPreExecute(){
            super.onPreExecute();
            prog=new ProgressDialog(SecondActivity.this);
            prog.setTitle("Progress");
            prog.setTitle("Downloading....");
            prog.setIndeterminate(false);
            prog.setMax(100);
            prog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            prog.show();
        }
        @Override
        protected String doInBackground(String... strings){
            try{
                URL url=new URL(strings[0]);
                URLConnection connection=url.openConnection();
                connection.connect();
                int filelength=connection.getContentLength();
                FileOutputStream fileOutputStream = getApplicationContext().openFileOutput("s1.mp3", Context.MODE_PRIVATE);
                InputStream input=new BufferedInputStream(url.openStream());
               // OutputStream out=new FileOutputStream(filePath+"/tanishi.mp3");
                byte data[]=new byte[1024];
                long total=0;
                int c;
                while((c=input.read(data))!=-1) {
                    total += c;
                    publishProgress((int) (total * 100 / filelength));
                    fileOutputStream.write(data, 0, c);
                }
                input.close();
                fileOutputStream.flush();
                fileOutputStream.close();

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            prog.setProgress(values[0]);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            flag=true;
        }
    }

}