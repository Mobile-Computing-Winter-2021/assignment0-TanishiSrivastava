package com.tanishi.myapp3;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.lang.reflect.Array;


public class SecondFragment extends Fragment {
   private EditText name,email,dept;
   private TextView roll;
   private Button edit;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        super.onCreateView(inflater,container,savedInstanceState);
        View v= inflater.inflate(R.layout.fragment_second, container, false);
        name=(EditText) v.findViewById(R.id.name1);
        roll=(TextView) v.findViewById(R.id.rollno1);
        email=(EditText)v.findViewById(R.id.email);
        dept=(EditText)v.findViewById(R.id.dept);
        edit=(Button)v.findViewById(R.id.edit);

        String[] array=this.getArguments().getStringArray("Array");
        int i=this.getArguments().getInt("Position");
        roll.setText(array[0]);
        name.setText(array[1]);
        dept.setText(array[2]);
        email.setText(array[3]);
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OurData.Roll[i]=roll.getText().toString();
                OurData.name[i]=name.getText().toString();
                OurData.Email[i]=email.getText().toString();
                OurData.Dept[i]=dept.getText().toString();
            }
        });

        return v;



    }
}