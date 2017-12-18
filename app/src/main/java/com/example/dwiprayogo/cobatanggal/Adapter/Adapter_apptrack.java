package com.example.dwiprayogo.cobatanggal.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.dwiprayogo.cobatanggal.Model.model_apptracj;
import com.example.dwiprayogo.cobatanggal.R;

import java.util.List;

/**
 * Created by dwi.prayogo on 11/22/2017.
 */

public class Adapter_apptrack extends RecyclerView.Adapter<Adapter_apptrack.MyViewHolder> {

    //private List<model_refund> moviesList;
    private List<model_apptracj>  moviesList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name;
        public TextView nokontrak;



        public MyViewHolder(View view) {
            super(view);
            name = (TextView) view.findViewById(R.id.nama);
            nokontrak = (TextView) view.findViewById(R.id.nokontrak);



        }
    }

    public Adapter_apptrack(List<model_apptracj> moviesList) {
        this.moviesList = moviesList;
    }

    @Override
    public Adapter_apptrack.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_apptrack, parent, false);

        return new Adapter_apptrack.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(Adapter_apptrack.MyViewHolder holder, int position) {
        model_apptracj task = moviesList.get(position);
        holder.name.setText(task.getNama());
        holder.nokontrak.setText(task.getNokontrak());


    }

    @Override
    public int getItemCount() {
        return moviesList.size();
    }
}
