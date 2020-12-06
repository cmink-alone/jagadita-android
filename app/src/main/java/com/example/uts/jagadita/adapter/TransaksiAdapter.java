package com.example.uts.jagadita.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.uts.jagadita.DetailUsahaActivity;
import com.example.uts.jagadita.R;
import com.example.uts.jagadita.models.Transaksi;
import com.example.uts.jagadita.models.Transaksi;
import com.google.gson.Gson;

import java.util.List;

public class TransaksiAdapter extends RecyclerView.Adapter<TransaksiAdapter.ViewHolder> {
    Context context;
    List<Transaksi> listTransaksi;


    public TransaksiAdapter(Context context, List<Transaksi> listTransaksi) {
        this.context = context;
        this.listTransaksi = listTransaksi;
    }

    public void setItems(List<Transaksi> listTransaksi){
        this.listTransaksi = listTransaksi;
    }

    @NonNull
    @Override
    public TransaksiAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_transaksi, parent, false);
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull TransaksiAdapter.ViewHolder holder, int position) {
        Transaksi transaksi = listTransaksi.get(position);

        holder.nama_pembeli.setText(transaksi.getNama_pembeli());
        holder.tanggal.setText(transaksi.getTanggal());
        holder.total_beli.setText("Rp"+String.valueOf(transaksi.getTotal_beli()));
    }

    @Override
    public int getItemCount() {
        return listTransaksi.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView nama_pembeli;
        TextView tanggal;
        TextView total_beli;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            nama_pembeli = itemView.findViewById(R.id.nama);
            tanggal = itemView.findViewById(R.id.tanggal);
            total_beli = itemView.findViewById(R.id.total_beli);

        }
    }
}
