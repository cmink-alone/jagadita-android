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

import com.example.uts.jagadita.adapter.DonasiUserAdapter;
import com.example.uts.jagadita.api.ApiClient;
import com.example.uts.jagadita.api.ApiService;
import com.example.uts.jagadita.models.Donasi;
import com.example.uts.jagadita.models.Donasi;
import com.example.uts.jagadita.utils.PreferencesHelper;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class DonasimuFragment extends Fragment {
    RecyclerView list_donasi;
    LinearLayout frame_list;
    LinearLayout frame_empty;
    ProgressBar progress_bar;

    ApiService apiService;
    PreferencesHelper preferencesHelper;

    public DonasiUserAdapter adapter;
    public List<Donasi> donasiList = new ArrayList<>();

    public DonasimuFragment() {
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
        return inflater.inflate(R.layout.fragment_donasimu, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        AppCompatActivity activity = (AppCompatActivity) getActivity();

        apiService = ApiClient.getService(activity);
        preferencesHelper = new PreferencesHelper(activity);

        list_donasi = view.findViewById(R.id.list_donasi);
        frame_list = view.findViewById(R.id.frame_list);
        frame_empty = view.findViewById(R.id.frame_empty);
        progress_bar = view.findViewById(R.id.progress_bar);

        frame_list.setVisibility(View.GONE);
        frame_empty.setVisibility(View.GONE);
        progress_bar.setVisibility(View.VISIBLE);

        adapter = new DonasiUserAdapter(activity, donasiList);
        list_donasi.setLayoutManager(new LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false));
        list_donasi.setAdapter(adapter);

        Observable<List<Donasi>> getDonasiUser = apiService.get_donasi_user(preferencesHelper.getUserId())
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
        getDonasiUser.subscribe(
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
                        Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onComplete() {
                    }
                }
        );
    }
}