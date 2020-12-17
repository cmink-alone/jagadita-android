package com.example.uts.jagadita.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.uts.jagadita.R;
import com.example.uts.jagadita.models.Donasi;

import java.util.List;

public class DonasiAdapter extends RecyclerView.Adapter<DonasiAdapter.ViewHolder> {
    Context context;
    List<Donasi> listDonasi;


    public DonasiAdapter(Context context, List<Donasi> listDonasi) {
        this.context = context;
        this.listDonasi = listDonasi;
    }

    public void setItems(List<Donasi> listDonasi){
        this.listDonasi = listDonasi;
    }

    @NonNull
    @Override
    public DonasiAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_transaksi, parent, false);
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull DonasiAdapter.ViewHolder holder, int position) {
        Donasi donasi = listDonasi.get(position);

        holder.nama_donatur.setText(donasi.getNama_donatur());
        holder.tanggal.setText(donasi.getTanggal());
        holder.total_beli.setText("Rp"+String.valueOf(donasi.getTotal_beli()));
    }

    @Override
    public int getItemCount() {
        return listDonasi.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView nama_donatur;
        TextView tanggal;
        TextView total_beli;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            nama_donatur = itemView.findViewById(R.id.nama);
            tanggal = itemView.findViewById(R.id.tanggal);
            total_beli = itemView.findViewById(R.id.total_beli);

        }
    }
}
