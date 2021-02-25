package com.tanishi.myapp3;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

public class ListAdapter extends RecyclerView.Adapter {
    Context context;

    public ListAdapter(Context context){
        this.context=context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item,parent,false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((ListViewHolder)holder).bindView(position);
        ((ListViewHolder) holder).parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,"Clicked",Toast.LENGTH_SHORT).show();
                Bundle bundle=new Bundle();
                String[] array=new String[4];
                array[0]=OurData.Roll[position];
                array[1]=OurData.name[position];
                array[2]=OurData.Dept[position];
                array[3]=OurData.Email[position];
                bundle.putStringArray("Array",array);
                bundle.putInt("Position",position);
                SecondFragment frag=new SecondFragment();
                frag.setArguments(bundle);
                AppCompatActivity aca=(AppCompatActivity)v.getContext();
                aca.getSupportFragmentManager().beginTransaction().replace(R.id.mainid,frag).addToBackStack(null).commit();
            }
        });
    }

    @Override
    public int getItemCount() {
        return OurData.name.length;
    }
    private class ListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView Roll;
        private TextView Name;
        private LinearLayout parent;
        public ListViewHolder(View itemview){
            super(itemview);
            Roll=(TextView)itemview.findViewById(R.id.roll);
            Name=(TextView)itemview.findViewById(R.id.name);
            parent=(LinearLayout)itemview.findViewById(R.id.parentLayout);
            itemView.setOnClickListener(this);
        }

        public void bindView(int position){
            Roll.setText(OurData.Roll[position]);
            Name.setText(OurData.name[position]);
        }
        public void onClick(View view){

        }
    }
}
