package com.example.uts.jagadita;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.uts.jagadita.api.ApiClient;
import com.example.uts.jagadita.api.ApiService;
import com.example.uts.jagadita.models.ApiResponse;
import com.example.uts.jagadita.models.Pengguna;
import com.example.uts.jagadita.models.Perusahaan;
import com.example.uts.jagadita.utils.PreferencesHelper;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DaftarUsahamuActivity extends AppCompatActivity {
    Button btnDaftar;
    EditText nama_perusahaan;
    EditText jenis_usaha;
    EditText alamat;
    EditText telp;
    EditText deskripsi;
    EditText kota;
    EditText harga;
    ProgressBar progress_bar;

    ApiService apiService;
    PreferencesHelper preferencesHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar_usahamu);

        apiService = ApiClient.getService(this);
        preferencesHelper = new PreferencesHelper(this);
        final Pengguna user_login = preferencesHelper.getUser();

        getSupportActionBar().setTitle("Daftar Usaha Baru");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        nama_perusahaan = findViewById(R.id.nama_perusahaan);
        jenis_usaha = findViewById(R.id.jenis_usaha);
        alamat = findViewById(R.id.alamat);
        telp = findViewById(R.id.telp);
        deskripsi = findViewById(R.id.deskripsi);
        kota = findViewById(R.id.kota);
        harga = findViewById(R.id.harga);
        progress_bar = findViewById(R.id.progress_bar);
        progress_bar.setVisibility(View.GONE);

        btnDaftar = findViewById(R.id.btnDaftar);
        btnDaftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnDaftar.setEnabled(false);
                progress_bar.setVisibility(View.VISIBLE);
                Perusahaan new_perusahaan = new Perusahaan(
                        -1,
                        user_login.getId(),
                        "",
                        nama_perusahaan.getText().toString(),
                        jenis_usaha.getText().toString(),
                        alamat.getText().toString(),
                        telp.getText().toString(),
                        deskripsi.getText().toString(),
                        kota.getText().toString(),
                        0,
                        Integer.parseInt(harga.getText().toString())
                );

                apiService.daftar_usaha(new_perusahaan)
                        .enqueue(new DaftarUsahamuActivity.DaftarCallback(new_perusahaan));
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                Intent returnIntent = new Intent();
                setResult(Activity.RESULT_CANCELED, returnIntent);
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public class DaftarCallback implements Callback<ApiResponse> {
        Perusahaan new_perusahaan;
        public DaftarCallback(Perusahaan new_perusahaan) {
            this.new_perusahaan = new_perusahaan;
        }

        @Override
        public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
            ApiResponse apiResponse = response.body();
            if(response.isSuccessful() && apiResponse.getStatus()){
                Toast.makeText(DaftarUsahamuActivity.this, apiResponse.getMessage() + " id:"+ apiResponse.getId(), Toast.LENGTH_LONG).show();
                Intent returnIntent = new Intent();
                returnIntent.putExtra("result",new Gson().toJson(new_perusahaan));
                setResult(Activity.RESULT_OK,returnIntent);
                finish();
            } else {
                Toast.makeText(DaftarUsahamuActivity.this, apiResponse.getMessage() , Toast.LENGTH_LONG).show();
            }
            btnDaftar.setEnabled(true);
            progress_bar.setVisibility(View.GONE);
        }

        @Override
        public void onFailure(Call<ApiResponse> call, Throwable t) {
            Toast.makeText(DaftarUsahamuActivity.this, "Gagal mendaftarkan usaha. ERR1", Toast.LENGTH_LONG).show();
            btnDaftar.setEnabled(true);
            progress_bar.setVisibility(View.GONE);
        }
    }
}