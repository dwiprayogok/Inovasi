package com.example.dwiprayogo.cobatanggal.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.dwiprayogo.cobatanggal.Model.model_apptracj;
import com.example.dwiprayogo.cobatanggal.Model.model_po;
import com.example.dwiprayogo.cobatanggal.R;

import java.util.List;

/**
 * Created by dwi.prayogo on 11/22/2017.
 */

public class Adapter_PO extends RecyclerView.Adapter<Adapter_PO.MyViewHolder> {


    private List<model_po> moviesList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name;
        public TextView nokontrak;



        public MyViewHolder(View view) {
            super(view);
            name = (TextView) view.findViewById(R.id.nama);
            nokontrak = (TextView) view.findViewById(R.id.nokontrak);



        }
    }

    public Adapter_PO(List<model_po> moviesList) {
        this.moviesList = moviesList;
    }

    @Override
    public Adapter_PO.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_apptrack, parent, false);

        return new Adapter_PO.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(Adapter_PO.MyViewHolder holder, int position) {
        model_po task = moviesList.get(position);
        holder.name.setText(task.getNama());
        holder.nokontrak.setText(task.getNokontrak());


    }

    @Override
    public int getItemCount() {
        return moviesList.size();
    }
}
