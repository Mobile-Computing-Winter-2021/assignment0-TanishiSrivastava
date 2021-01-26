package com.tanishi.assignment1;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class SecondActivity extends AppCompatActivity{
    TextView txt,txt2;
    Button check;
    private static final String Tag = MainActivity.class.getSimpleName();
    private void showLog(String text){
        Log.i(Tag, text);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        showLog("Activity Created");

        txt=(TextView)findViewById(R.id.textView6);
        Intent i=getIntent();
        String name=i.getStringExtra("Name");
        String details=i.getStringExtra("List");
        txt.setText(name);
        txt2=(TextView)findViewById(R.id.textView7);
        txt2.setText(details);
        int c=i.getIntExtra("Count",0);
        if(c==5){
            Toast.makeText(SecondActivity.this, "You are in Safe State",
                    Toast.LENGTH_LONG).show();
        }
        check=(Button)findViewById(R.id.button3);
        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i.putExtra("Data",c);
                setResult(RESULT_OK,i);
            }
        });
    }

    @Override
    public void onBackPressed(){
        super.finish();
    }
    @Override
    protected void onResume() {
        super.onResume();
        activity("Activity2 Resumed");
        showLog("Activity2 Resumed");
    }

    @Override
    protected void onPause() {
        super.onPause();
        activity("Activity2 Paused");
        showLog("Activity2 Paused");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        activity("Activity2 Restarted");
        showLog("Activity2 Restarted");
    }

    @Override
    protected void onStart() {
        super.onStart();
        activity("Activity2 Started");
        showLog("Activity2 Started");
    }

    @Override
    protected void onStop() {
        super.onStop();
        activity("Activity2 Stopped");
        showLog("Activity2 Stopped");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        activity("Activity2 Destroyed");
        showLog("Activity2 Destroyed");
    }

    public void activity(String string){
        Toast.makeText(this, string, Toast.LENGTH_SHORT).show();
    }

}
