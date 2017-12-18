package com.example.dwiprayogo.cobatanggal.Adapter;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.dwiprayogo.cobatanggal.Model.model_refund;
import com.example.dwiprayogo.cobatanggal.R;

import java.util.List;

/**
 * Created by dwi.prayogo on 11/14/2017.
 */

public class Adapter_refund  extends RecyclerView.Adapter<Adapter_refund.MyViewHolder> {

    private List<model_refund> moviesList;
    int white,green,grey,blue,yellow,teal,black,violet,orange;
    String status;
    RelativeLayout lay_list_refund_name;
    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name;
        public TextView posisi;
        public TextView date;
        public TextView status;


        public MyViewHolder(View view) {
            super(view);
            lay_list_refund_name = (RelativeLayout) view.findViewById(R.id.lay_list_refund_name);
            name = (TextView) view.findViewById(R.id.nama);
            posisi = (TextView) view.findViewById(R.id.posisi);
            status = (TextView) view.findViewById(R.id.status);
            date = (TextView) view.findViewById(R.id.date);
            white = view.getResources().getColor(R.color.md_white_1000);
            green = view.getResources().getColor(R.color.md_green_500);
            grey = view.getResources().getColor(R.color.md_grey_600);
            blue = view.getResources().getColor(R.color.md_blue_500);
            teal = view.getResources().getColor(R.color.md_teal_500);
            orange = view.getResources().getColor(R.color.md_deep_orange_500);
            yellow = view.getResources().getColor(R.color.md_yellow_800);
            black = view.getResources().getColor(R.color.md_black_1000);
            violet = view.getResources().getColor(R.color.md_purple_500);

        }
    }


    public Adapter_refund(List<model_refund> moviesList) {
        this.moviesList = moviesList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_payment_refund, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        model_refund task = moviesList.get(position);
        holder.name.setText(task.getNama());
        holder.posisi.setText(task.getPosisi());
        holder.status.setText(task.getStatus());
        holder.date.setText(task.getTanggal());

        status = task.getStatus();
        Log.d("cek", "onBindViewHolder: "+status);
        if (status.equals("Paid")){
            lay_list_refund_name.setBackgroundColor(Color.GREEN);
        }else{
            lay_list_refund_name.setBackgroundColor(Color.RED);
            holder.date.setVisibility(View.GONE);
        }

    }

    @Override
    public int getItemCount() {
        return moviesList.size();
    }
}
