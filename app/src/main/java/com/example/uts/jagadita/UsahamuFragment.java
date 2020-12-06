package com.example.uts.jagadita;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
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
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.android.schedulers.AndroidSchedulers;

public class UsahamuFragment extends Fragment {
    final int LAUNCH_DAFTAR_USAHA = 1;
    Button btnDaftarUsaha;
    Button btnDaftarUsahaNew;
    RecyclerView list_usaha;
    LinearLayout frame_list;
    LinearLayout frame_empty;
    ProgressBar progress_bar;

    public PerusahaanAdapter adapter;
    public List<Perusahaan> perusahaanList = new ArrayList<>();	;

    ApiService apiService;
    PreferencesHelper preferencesHelper;

    public UsahamuFragment(Context context) {
        apiService = ApiClient.getService(context);
        preferencesHelper = new PreferencesHelper(context);
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_usahamu, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        list_usaha = view.findViewById(R.id.list_usaha);

        frame_list = view.findViewById(R.id.frame_list);
        frame_empty = view.findViewById(R.id.frame_empty);
        progress_bar = view.findViewById(R.id.progress_bar);

        frame_list.setVisibility(View.GONE);
        frame_empty.setVisibility(View.GONE);
        progress_bar.setVisibility(View.VISIBLE);

        btnDaftarUsaha = view.findViewById(R.id.btnDaftarUsaha);
        btnDaftarUsahaNew = view.findViewById(R.id.btnDaftarUsahaNew);
        btnDaftarUsaha.setOnClickListener(new UsahamuFragment.ClickDaftarUsaha());
        btnDaftarUsahaNew.setOnClickListener(new UsahamuFragment.ClickDaftarUsaha());

        adapter = new PerusahaanAdapter(getActivity(), perusahaanList);
        list_usaha.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        list_usaha.setAdapter(adapter);

        Observable<List<Perusahaan>> getUsaha = apiService.get_usaha(preferencesHelper.getUserId())
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
                        Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onComplete() {
                    }
                }
        );
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == LAUNCH_DAFTAR_USAHA) {
            if(resultCode == Activity.RESULT_OK){
                String result=data.getStringExtra("result");
                Perusahaan perusahaan = new Gson().fromJson(result, Perusahaan.class);
                perusahaanList.add(perusahaan);
                adapter.setItems(perusahaanList);
                adapter.notifyDataSetChanged();
                Toast.makeText(getContext(), "MASUK" , Toast.LENGTH_LONG).show();
            }
        }
    }

    class ClickDaftarUsaha implements View.OnClickListener{

        @Override
        public void onClick(View view) {
            Intent intent = new Intent(getActivity(), DaftarUsahamuActivity.class);
            startActivityForResult(intent, LAUNCH_DAFTAR_USAHA);
        }
    }
}