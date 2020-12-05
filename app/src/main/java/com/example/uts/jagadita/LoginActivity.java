package com.example.uts.jagadita;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.uts.jagadita.api.ApiClient;
import com.example.uts.jagadita.api.ApiService;
import com.example.uts.jagadita.models.Pengguna;
import com.example.uts.jagadita.utils.PreferencesHelper;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    Button btnLogin;
    EditText username;
    EditText password;
    TextView register;
    ProgressBar progress_bar;

    ApiService apiService;
    PreferencesHelper preferencesHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        apiService = ApiClient.getService(getApplicationContext());
        preferencesHelper = new PreferencesHelper(this);
        if(preferencesHelper.getLogin()){
            loginSuccess();
        }

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        register = findViewById(R.id.register);
        progress_bar = findViewById(R.id.progress_bar);
        progress_bar.setVisibility(View.GONE);

        btnLogin = findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnLogin.setEnabled(false);
                progress_bar.setVisibility(View.VISIBLE);

                apiService.login(username.getText().toString(),password.getText().toString())
                        .enqueue(new LoginCallback());
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                LoginActivity.this.startActivity(intent);
            }
        });
    }


    public void loginSuccess(){
        Intent main = new Intent(LoginActivity.this, MainActivity.class);
        LoginActivity.this.startActivity(main);
        finish();
    }

    class LoginCallback implements Callback<Pengguna> {
        @Override
        public void onResponse(Call<Pengguna> call, Response<Pengguna> response) {
            Log.d("ADAD", response.toString());
            if(response.isSuccessful()){
                Pengguna auth = response.body();

                preferencesHelper.setUserLogin(auth);

                loginSuccess();
                Toast.makeText(LoginActivity.this, "Login Success", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(LoginActivity.this, "Login Failed"+response.body(), Toast.LENGTH_SHORT).show();
            }
            btnLogin.setEnabled(true);
            progress_bar.setVisibility(View.GONE);
        }

        @Override
        public void onFailure(Call<Pengguna> call, Throwable t) {
            btnLogin.setEnabled(true);
            progress_bar.setVisibility(View.GONE);
            Toast.makeText(LoginActivity.this, "Login Failed"+t.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}