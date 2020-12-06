package com.example.uts.jagadita;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.uts.jagadita.api.ApiClient;
import com.example.uts.jagadita.api.ApiService;
import com.example.uts.jagadita.models.ApiResponse;
import com.example.uts.jagadita.models.Pengguna;
import com.example.uts.jagadita.utils.PreferencesHelper;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfilFragment extends Fragment {
    Button btnSimpan;
    Button btnLogout;
    EditText nama;
    EditText telp;
    EditText alamat;
    EditText pekerjaan;
    EditText tanggal_lahir;
    EditText jenis_kelamin;
    ProgressBar progress_bar;

    ApiService apiService;
    PreferencesHelper preferencesHelper;

    public ProfilFragment() {
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
        return inflater.inflate(R.layout.fragment_profil, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        apiService = ApiClient.getService(getContext());
        preferencesHelper = new PreferencesHelper(getActivity());

        final Pengguna user_login = preferencesHelper.getUser();

        nama = view.findViewById(R.id.nama);
        telp = view.findViewById(R.id.telp);
        alamat = view.findViewById(R.id.alamat);
        pekerjaan = view.findViewById(R.id.pekerjaan);
        tanggal_lahir = view.findViewById(R.id.tanggal_lahir);
        jenis_kelamin = view.findViewById(R.id.jenis_kelamin);
        progress_bar = view.findViewById(R.id.progress_bar);
        progress_bar.setVisibility(View.GONE);

        nama.setText(user_login.getNama());
        telp.setText(user_login.getTelp());
        alamat.setText(user_login.getAlamat());
        pekerjaan.setText(user_login.getPekerjaan());
        tanggal_lahir.setText(user_login.getTanggal_lahir());
        jenis_kelamin.setText(user_login.getJenis_kelamin());

        btnLogout = view.findViewById(R.id.btnLogout);
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                preferencesHelper.logout();
                Intent login = new Intent(getActivity(), LoginActivity.class);
                getActivity().startActivity(login);
                getActivity().finish();
            }
        });

        btnSimpan = view.findViewById(R.id.btnSimpan);
        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View view) {
                progress_bar.setVisibility(View.VISIBLE);
                btnSimpan.setEnabled(false);
                Pengguna new_pengguna = new Pengguna(
                        user_login.getId(),
                        user_login.getUsername(),
                        user_login.getPassword(),
                        nama.getText().toString(),
                        telp.getText().toString(),
                        alamat.getText().toString(),
                        pekerjaan.getText().toString(),
                        tanggal_lahir.getText().toString(),
                        jenis_kelamin.getText().toString()
                        );

                apiService.update_profil(new_pengguna)
                        .enqueue(new ProfilFragment.UpdateProfilCallback(new_pengguna));
            }
        });
    }


    class UpdateProfilCallback implements Callback<ApiResponse> {
        Pengguna new_pengguna;
        public UpdateProfilCallback(Pengguna new_pengguna){
            this.new_pengguna = new_pengguna;
        }
        @SuppressLint("ResourceAsColor")
        @Override
        public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
            ApiResponse apiResponse = response.body();
            if(response.isSuccessful() && apiResponse.getStatus()){
                preferencesHelper.setUserLogin(new_pengguna);

                Toast.makeText(getContext(), apiResponse.getMessage(), Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(getContext(), "Gagal mengubah profil", Toast.LENGTH_LONG).show();
            }
            progress_bar.setVisibility(View.GONE);
            btnSimpan.setEnabled(true);
        }

        @Override
        public void onFailure(Call<ApiResponse> call, Throwable t) {
            Toast.makeText(getContext(), "Gagal mengubah profil: "+t.getMessage(), Toast.LENGTH_LONG).show();
            progress_bar.setVisibility(View.GONE);
            btnSimpan.setEnabled(true);
        }
    }
}