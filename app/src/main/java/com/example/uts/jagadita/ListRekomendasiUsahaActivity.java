package com.example.uts.jagadita;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.uts.jagadita.adapter.PerusahaanAdapter;
import com.example.uts.jagadita.api.ApiClient;
import com.example.uts.jagadita.api.ApiService;
import com.example.uts.jagadita.models.Perusahaan;
import com.example.uts.jagadita.utils.PreferencesHelper;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ListRekomendasiUsahaActivity extends AppCompatActivity {
    public static final int LAUNCH_DETAIL_USAHA = 1;
    RecyclerView list_usaha;
    ProgressBar progress_bar;

    public PerusahaanAdapter adapter;
    public List<Perusahaan> perusahaanList = new ArrayList<>();	;

    ApiService apiService;
    PreferencesHelper preferencesHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_usaha);

        getSupportActionBar().setTitle("Perusahaan");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        apiService = ApiClient.getService(this);
        preferencesHelper = new PreferencesHelper(this);

        list_usaha = findViewById(R.id.list_usaha);
        progress_bar = findViewById(R.id.progress_bar);

        adapter = new PerusahaanAdapter(this, perusahaanList);
        list_usaha.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        list_usaha.setAdapter(adapter);

        Observable<List<Perusahaan>> getUsaha = apiService.get_usaha_rekomendasi(preferencesHelper.getUserId())
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
        getUsaha.subscribe(
                new Observer<List<Perusahaan>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(List<Perusahaan> perusahaans) {
                        progress_bar.setVisibility(View.GONE);
                        if(perusahaans.size()>0){
                            perusahaanList = perusahaans;
                            adapter.setItems(perusahaans);
                            adapter.notifyDataSetChanged();
                        }
//                        Toast.makeText(getActivity(), "SUKSES MENDAPAT DATA", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(ListRekomendasiUsahaActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onComplete() {
                    }
                }
        );
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == LAUNCH_DETAIL_USAHA) {
            if(resultCode == Activity.RESULT_OK){
                int total_saham=data.getIntExtra("total_saham", 0);
                int position=data.getIntExtra("position", 0);
                perusahaanList.get(position).setTotal_saham(total_saham);
                Perusahaan p = perusahaanList.get(position);
                if(p.getTotal_saham() >= p.getHarga()){
                    perusahaanList.remove(position);
                }
                adapter.setItems(perusahaanList);
                adapter.notifyDataSetChanged();
                Toast.makeText(this, "MASUK" , Toast.LENGTH_LONG).show();
            }
        }
    }
}