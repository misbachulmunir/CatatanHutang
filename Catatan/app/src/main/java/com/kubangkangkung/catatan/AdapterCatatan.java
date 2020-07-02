package com.kubangkangkung.catatan;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;

public class AdapterCatatan extends RecyclerView.Adapter<AdapterCatatan.MyViewHolder> {
    private Context context;
    private List<ModelCatatan>data=new ArrayList<>();
    private Realm realm;

    public AdapterCatatan(Context context, List<ModelCatatan> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    //sambungkan dengan layout
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView= LayoutInflater.from(context).inflate(R.layout.isi_catatan,parent,false);
        return new MyViewHolder(itemView);
    }
    //set data
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        holder.judul.setText(data.get(position).getJudul());
        holder.jumlah.setText("Rp "+data.get(position).getJumlah());
        holder.tanggal.setText(data.get(position).getTanggal());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pindah=new Intent(context,DetailActivity.class);
                pindah.putExtra(DetailActivity.KEY_ID,data.get(position).getId());
                context.startActivity(pindah);
            }
        });

    }


    @Override
    public int getItemCount() {
        return data.size();
    }
    //inisialisasi
    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView judul,jumlah,tanggal;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            judul=itemView.findViewById(R.id.id_judul);
            jumlah=itemView.findViewById(R.id.id_jumlah);
            tanggal=itemView.findViewById(R.id.id_tanggal);
        }
    }
}
