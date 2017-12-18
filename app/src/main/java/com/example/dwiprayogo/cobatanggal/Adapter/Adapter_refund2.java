package com.example.dwiprayogo.cobatanggal.Adapter;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.dwiprayogo.cobatanggal.Detail_refund;
import com.example.dwiprayogo.cobatanggal.Model.model_po;
import com.example.dwiprayogo.cobatanggal.Model.model_refund;
import com.example.dwiprayogo.cobatanggal.R;

import java.util.List;

/**
 * Created by dwi.prayogo on 11/23/2017.
 */

public class Adapter_refund2  extends RecyclerView.Adapter<Adapter_refund2.MyViewHolder> {
    private Activity activity;
    private List<model_po> moviesList;
    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name;
        public TextView nokontrak;
        public TextView appid;



        public MyViewHolder(View view) {
            super(view);
            name = (TextView) view.findViewById(R.id.nama);
            nokontrak = (TextView) view.findViewById(R.id.nokontrak);
            appid = (TextView) view.findViewById(R.id.appid);


        }
    }


    public Adapter_refund2(List<model_po> moviesList) {
        this.moviesList = moviesList;
    }

    @Override
    public Adapter_refund2.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_apptrack, parent, false);

        return new Adapter_refund2.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(Adapter_refund2.MyViewHolder holder, int position) {
        model_po task = moviesList.get(position);
        holder.name.setText(task.getNama());
        holder.nokontrak.setText(task.getNokontrak());
        holder.appid.setText(task.getAppid());



    }



    @Override
    public int getItemCount() {
        return moviesList.size();
    }
}
