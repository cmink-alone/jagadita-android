package com.example.uts.jagadita.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.uts.jagadita.R;
import com.example.uts.jagadita.models.Transaksi;

import java.util.List;

public class TransaksiUserAdapter extends RecyclerView.Adapter<TransaksiUserAdapter.ViewHolder> {
    Context context;
    List<Transaksi> listTransaksi;


    public TransaksiUserAdapter(Context context, List<Transaksi> listTransaksi) {
        this.context = context;
        this.listTransaksi = listTransaksi;
    }

    public void setItems(List<Transaksi> listTransaksi){
        this.listTransaksi = listTransaksi;
    }

    @NonNull
    @Override
    public TransaksiUserAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_transaksimu, parent, false);
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull TransaksiUserAdapter.ViewHolder holder, int position) {
        Transaksi transaksi = listTransaksi.get(position);

        holder.nama_perusahaan.setText(transaksi.getNama_perusahaan());
        holder.tanggal.setText(transaksi.getTanggal());
        holder.total_beli.setText("Rp"+String.valueOf(transaksi.getTotal_beli()));
        holder.status.setText(transaksi.getStatus());
    }

    @Override
    public int getItemCount() {
        return listTransaksi.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView nama_perusahaan;
        TextView tanggal;
        TextView total_beli;
        TextView status;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            nama_perusahaan = itemView.findViewById(R.id.nama);
            tanggal = itemView.findViewById(R.id.tanggal);
            total_beli = itemView.findViewById(R.id.total_beli);
            status = itemView.findViewById(R.id.status);

        }
    }
}
