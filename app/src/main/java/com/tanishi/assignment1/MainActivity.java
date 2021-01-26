package com.tanishi.assignment1;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    CheckBox c1,c2,c3,c4,c5;
    Button submit,clear;
    EditText name;
    int count=0;

    private static final String Tag = MainActivity.class.getSimpleName();
    private void showLog(String text){
        Log.i(Tag, text);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        showLog("Activity Created");

        name=(EditText)findViewById(R.id.editTextTextPersonName2);
        c1=(CheckBox)findViewById(R.id.checkBox);
        c2=(CheckBox)findViewById(R.id.checkBox2);
        c3=(CheckBox)findViewById(R.id.checkBox3);
        c4=(CheckBox)findViewById(R.id.checkBox4);
        c5=(CheckBox)findViewById(R.id.checkBox5);
        submit=(Button)findViewById(R.id.button);
        clear=(Button)findViewById(R.id.button2);

        submit.setOnClickListener(new View.OnClickListener(){

        @Override
        public void onClick(View v){

            StringBuffer lis = new StringBuffer();
            lis.append("Precautions followed by you-");
            if (c1.isChecked()) {
                count++;
                lis.append("\nWearing a mask when outside");
            }
            if (c2.isChecked()) {
                count++;
                lis.append("\nWashing hands frequently");
            }
            if (c3.isChecked()) {
                count++;
                lis.append("\nMaintaining 2 feet distance");
            }
            if (c4.isChecked()) {
                count++;
                lis.append("\nCovering nose and mouth while sneezing and coughing");
            }
            if (c5.isChecked()){
                count++;
                lis.append("\nTaking health diets");
            }
            String s = lis.toString();
            Intent i = new Intent(MainActivity.this, SecondActivity.class);
            i.putExtra("Name",name.getText().toString());
            i.putExtra("List",s);
            i.putExtra("Count",count);
            startActivityForResult(i,0);
        }
    });
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, MainActivity.class));
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK && requestCode==0) {
            Intent i=getIntent();
            String da=i.getStringExtra("Data");
            if (da.equals(5)){
                TextView textView = (TextView) findViewById(R.id.textView8);
                textView.setText("You are in Safe state");
            } else {
                TextView textView = (TextView) findViewById(R.id.textView8);
                textView.setText("You are in Unsafe state");
            }
        }
    }


    @Override
    protected void onResume() {
        super.onResume();
        activity("Activity1 Resumed");
        showLog("Activity1 Resumed");
    }

    @Override
    protected void onPause() {
        super.onPause();
        activity("Activity1 Paused");
        showLog("Activity1 Paused");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        activity("Activity1 Restarted");
        showLog("Activity1 Restarted");
    }

    @Override
    protected void onStart() {
        super.onStart();
        activity("Activity1 Started");
        showLog("Activity1 Started");
    }

    @Override
    protected void onStop() {
        super.onStop();
        activity("Activity1 Stopped");
        showLog("Activity1 Stopped");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        activity("Activity1 Destroyed");
        showLog("Activity1 Destroyed");
    }

    public void activity(String string){
        Toast.makeText(this, string, Toast.LENGTH_SHORT).show();
    }

}

