package com.example.assignment2;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.fragment.NavHostFragment;

import java.util.zip.Inflater;

public class FirstFragment extends Fragment {

    Button button4, button5, button8, button9, stop, download,start;
    int flag=0;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_first, container, false);
        button4 = (Button) view.findViewById(R.id.button4);
        button5 = (Button) view.findViewById(R.id.button5);
        button8 = (Button) view.findViewById(R.id.button8);
        button9 = (Button) view.findViewById(R.id.button9);
        download = (Button) view.findViewById(R.id.download);
        stop = (Button) view.findViewById(R.id.stop);
        Intent i = new Intent(getActivity(),MyService.class);


        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(flag==0) {
                    flag = 1;
                    i.putExtra("song", "1");
                    getActivity().startService(i);
                }
            }
        });
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(flag==0) {
                    flag = 1;
                    //Intent i = new Intent(getActivity(),MyService.class);
                    i.putExtra("song", "2");
                    getActivity().startService(i);
                }

            }
        });
        button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(flag==0) {
                    flag = 1;
                    //Intent i = new Intent(getActivity(),MyService.class);
                    i.putExtra("song", "3");
                    getActivity().startService(i);
                }

               }
        });

        button9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(flag==0) {
                    flag = 1;
                    //Intent i = new Intent(getActivity(),MyService.class);
                    i.putExtra("song", "4");
                    getActivity().startService(i);
                }
                }
        });

        download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(),SecondActivity.class);
                startActivity(intent);
            }
        });

        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(flag==1) {
                    flag=0;
                    getActivity().stopService(new Intent(getActivity(), MyService.class));
                }
            }
        });
        return view;
    }

}