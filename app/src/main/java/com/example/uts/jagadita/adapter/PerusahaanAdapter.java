package com.example.uts.jagadita.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.uts.jagadita.R;
import com.example.uts.jagadita.models.Perusahaan;

import java.util.List;

public class PerusahaanAdapter extends RecyclerView.Adapter<PerusahaanAdapter.ViewHolder> {
    Context context;
    List<Perusahaan> perusahaans;


    public PerusahaanAdapter(Context context, List<Perusahaan> perusahaans) {
        this.context = context;
        this.perusahaans = perusahaans;
    }

    public void setItems(List<Perusahaan> perusahaans){
        this.perusahaans = perusahaans;
    }

    @NonNull
    @Override
    public PerusahaanAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_perusahaan, parent, false);
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull PerusahaanAdapter.ViewHolder holder, int position) {
        Perusahaan perusahaan = perusahaans.get(position);

        holder.nama_perusahaan.setText(perusahaan.getNama_perusahaan());
        holder.harga.setText(String.valueOf(perusahaan.getHarga()));
        holder.jenis_usaha.setText(perusahaan.getJenis_usaha());
        holder.pemilik.setText(perusahaan.getNama_pemilik());
        holder.kota.setText(perusahaan.getKota());
    }

    @Override
    public int getItemCount() {
        return perusahaans.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView nama_perusahaan;
        TextView harga;
        TextView jenis_usaha;
        TextView pemilik;
        TextView kota;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            nama_perusahaan = itemView.findViewById(R.id.nama_perusahaan);
            harga = itemView.findViewById(R.id.harga);
            jenis_usaha = itemView.findViewById(R.id.jenis_usaha);
            pemilik = itemView.findViewById(R.id.pemilik);
            kota = itemView.findViewById(R.id.kota);
        }
    }
}
