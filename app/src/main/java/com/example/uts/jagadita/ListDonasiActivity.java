package com.example.uts.jagadita;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.uts.jagadita.adapter.DonasiAdapter;
import com.example.uts.jagadita.api.ApiClient;
import com.example.uts.jagadita.api.ApiService;
import com.example.uts.jagadita.models.Donasi;
import com.example.uts.jagadita.models.Perusahaan;
import com.example.uts.jagadita.models.Donasi;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ListDonasiActivity extends AppCompatActivity {
    public static final String EXTRA_SESSION_ID = "DATA_DONASI";

    RecyclerView list_donasi;
    LinearLayout frame_list;
    LinearLayout frame_empty;
    ProgressBar progress_bar;

    ApiService apiService;

    public DonasiAdapter adapter;
    public List<Donasi> donasiList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_donasi);

        getSupportActionBar().setTitle("Data Donasi");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        apiService = ApiClient.getService(this);

        String perusahaanJson = getIntent().getStringExtra(EXTRA_SESSION_ID);
        Perusahaan perusahaan = new Gson().fromJson(perusahaanJson, Perusahaan.class);

        list_donasi = findViewById(R.id.list_donasi);
        frame_list = findViewById(R.id.frame_list);
        frame_empty = findViewById(R.id.frame_empty);
        progress_bar = findViewById(R.id.progress_bar);

        frame_list.setVisibility(View.GONE);
        frame_empty.setVisibility(View.GONE);
        progress_bar.setVisibility(View.VISIBLE);

        adapter = new DonasiAdapter(this, donasiList);
        list_donasi.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        list_donasi.setAdapter(adapter);

        Observable<List<Donasi>> getDonasi = apiService.get_donasi(perusahaan.getId())
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
        getDonasi.subscribe(
                new Observer<List<Donasi>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(List<Donasi> listTrs) {
                        progress_bar.setVisibility(View.GONE);
                        if(listTrs.size()>0){
                            donasiList = listTrs;
                            adapter.setItems(donasiList);
                            adapter.notifyDataSetChanged();
                            frame_list.setVisibility(View.VISIBLE);
                            frame_empty.setVisibility(View.GONE);
                        } else {
                            frame_list.setVisibility(View.GONE);
                            frame_empty.setVisibility(View.VISIBLE);
                        }
//                        Toast.makeText(getActivity(), "SUKSES MENDAPAT DATA", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(ListDonasiActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onComplete() {
                    }
                }
        );
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}