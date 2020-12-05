package com.example.uts.jagadita;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.uts.jagadita.api.ApiClient;
import com.example.uts.jagadita.api.ApiService;
import com.example.uts.jagadita.models.ApiResponse;
import com.example.uts.jagadita.models.Pengguna;
import com.example.uts.jagadita.utils.PreferencesHelper;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {


    Button btnDaftar;
    Button btnLogin;
    EditText nama;
    EditText telp;
    EditText alamat;
    EditText pekerjaan;
    EditText tanggal_lahir;
    EditText jenis_kelamin;
    EditText username;
    EditText password;
    EditText re_password;
    ProgressBar progress_bar;

    ApiService apiService;
    PreferencesHelper preferencesHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Daftar Akun");

        apiService = ApiClient.getService(this);
        preferencesHelper = new PreferencesHelper(this);

        nama = findViewById(R.id.nama);
        telp = findViewById(R.id.telp);
        alamat = findViewById(R.id.alamat);
        pekerjaan = findViewById(R.id.pekerjaan);
        tanggal_lahir = findViewById(R.id.tanggal_lahir);
        jenis_kelamin = findViewById(R.id.jenis_kelamin);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        re_password = findViewById(R.id.re_password);
        progress_bar = findViewById(R.id.progress_bar);
        progress_bar.setVisibility(View.GONE);

        btnLogin = findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RegisterActivity.this.finish();
            }
        });

        btnDaftar = findViewById(R.id.btnDaftar);
        btnDaftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!password.getText().toString().equals(re_password.getText().toString())){
                    Toast.makeText(RegisterActivity.this, "Pastikan masukan password dan ulangi password sesuai!", Toast.LENGTH_LONG).show();
                } else {
                    btnDaftar.setEnabled(false);
                    progress_bar.setVisibility(View.VISIBLE);
                    Pengguna new_pengguna = new Pengguna(
                            -1,
                            username.getText().toString(),
                            password.getText().toString(),
                            nama.getText().toString(),
                            telp.getText().toString(),
                            alamat.getText().toString(),
                            pekerjaan.getText().toString(),
                            tanggal_lahir.getText().toString(),
                            jenis_kelamin.getText().toString()
                    );

                    apiService.register(new_pengguna)
                            .enqueue(new RegisterActivity.RegisterCallback(new_pengguna));
                }
            }
        });
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

    public class RegisterCallback implements Callback<ApiResponse> {
        public Pengguna new_pengguna;
        public RegisterCallback(Pengguna new_pengguna) {
            this.new_pengguna = new_pengguna;
        }

        @Override
        public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
            ApiResponse apiResponse = response.body();
            if(response.isSuccessful() && apiResponse.getStatus()){
                new_pengguna.setId(apiResponse.getId());
                preferencesHelper.setUserLogin(new_pengguna);

                Toast.makeText(RegisterActivity.this, apiResponse.getMessage(), Toast.LENGTH_LONG).show();

                Intent main = new Intent(RegisterActivity.this, MainActivity.class);
                RegisterActivity.this.startActivity(main);
                finish();
            } else {
                Toast.makeText(RegisterActivity.this, "Gagal mendaftar", Toast.LENGTH_LONG).show();
            }
            btnDaftar.setEnabled(true);
            progress_bar.setVisibility(View.GONE);
        }

        @Override
        public void onFailure(Call<ApiResponse> call, Throwable t) {
            Toast.makeText(RegisterActivity.this, "Gagal mendaftar: "+t.getMessage(), Toast.LENGTH_LONG).show();
            btnDaftar.setEnabled(true);
            progress_bar.setVisibility(View.GONE);
        }
    }
}