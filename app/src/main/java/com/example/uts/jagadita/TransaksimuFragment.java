package com.example.uts.jagadita;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.uts.jagadita.adapter.TransaksiAdapter;
import com.example.uts.jagadita.adapter.TransaksiUserAdapter;
import com.example.uts.jagadita.api.ApiClient;
import com.example.uts.jagadita.api.ApiService;
import com.example.uts.jagadita.models.Transaksi;
import com.example.uts.jagadita.utils.PreferencesHelper;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class TransaksimuFragment extends Fragment {

    RecyclerView list_transaksi;
    LinearLayout frame_list;
    LinearLayout frame_empty;
    ProgressBar progress_bar;

    ApiService apiService;
    PreferencesHelper preferencesHelper;

    public TransaksiUserAdapter adapter;
    public List<Transaksi> transaksiList = new ArrayList<>();

    public TransaksimuFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_transaksimu, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        AppCompatActivity activity = (AppCompatActivity) getActivity();

        apiService = ApiClient.getService(activity);
        preferencesHelper = new PreferencesHelper(activity);

        list_transaksi = view.findViewById(R.id.list_transaksi);
        frame_list = view.findViewById(R.id.frame_list);
        frame_empty = view.findViewById(R.id.frame_empty);
        progress_bar = view.findViewById(R.id.progress_bar);

        frame_list.setVisibility(View.GONE);
        frame_empty.setVisibility(View.GONE);
        progress_bar.setVisibility(View.VISIBLE);

        adapter = new TransaksiUserAdapter(activity, transaksiList);
        list_transaksi.setLayoutManager(new LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false));
        list_transaksi.setAdapter(adapter);

        Observable<List<Transaksi>> getTransaksiUser = apiService.get_transaksi_user(preferencesHelper.getUserId())
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
        getTransaksiUser.subscribe(
                new Observer<List<Transaksi>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(List<Transaksi> listTrs) {
                        progress_bar.setVisibility(View.GONE);
                        if(listTrs.size()>0){
                            transaksiList = listTrs;
                            adapter.setItems(transaksiList);
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
                        Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onComplete() {
                    }
                }
        );
    }
}