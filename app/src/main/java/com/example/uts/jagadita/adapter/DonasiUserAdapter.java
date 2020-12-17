package com.example.uts.jagadita.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.ColorStateList;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.core.widget.ImageViewCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.uts.jagadita.R;
import com.example.uts.jagadita.models.Donasi;

import java.util.List;

public class DonasiUserAdapter extends RecyclerView.Adapter<DonasiUserAdapter.ViewHolder> {
    Context context;
    List<Donasi> listDonasi;


    public DonasiUserAdapter(Context context, List<Donasi> listDonasi) {
        this.context = context;
        this.listDonasi = listDonasi;
    }

    public void setItems(List<Donasi> listDonasi){
        this.listDonasi = listDonasi;
    }

    @NonNull
    @Override
    public DonasiUserAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_donasimu, parent, false);
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull DonasiUserAdapter.ViewHolder holder, int position) {
        Donasi donasi = listDonasi.get(position);

        holder.nama_perusahaan.setText(donasi.getNama_perusahaan());
        holder.tanggal.setText(donasi.getTanggal());
        holder.total_beli.setText("Rp"+String.valueOf(donasi.getTotal_beli()));
        holder.status.setText(donasi.getStatus());
        int color = R.color.colorPrimary;
        if(donasi.getStatus().equals("pending") || donasi.getStatus().equals("dibatalkan")) {
            color = R.color.colorAccent;
        } else if(donasi.getStatus().equals("gagal")){
            color = R.color.red;
        }
        holder.status.setTextColor(ContextCompat.getColor(context, color));
        holder.total_beli.setTextColor(ContextCompat.getColor(context, color));
        ImageViewCompat.setImageTintList(holder.ic_store, ColorStateList.valueOf(ContextCompat.getColor(context, color)));

    }

    @Override
    public int getItemCount() {
        return listDonasi.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ic_store;
        TextView nama_perusahaan;
        TextView tanggal;
        TextView total_beli;
        TextView status;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            ic_store = itemView.findViewById(R.id.ic_store);
            nama_perusahaan = itemView.findViewById(R.id.nama);
            tanggal = itemView.findViewById(R.id.tanggal);
            total_beli = itemView.findViewById(R.id.total_beli);
            status = itemView.findViewById(R.id.status);

        }
    }
}
