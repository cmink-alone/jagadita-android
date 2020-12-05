package com.example.uts.jagadita;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.uts.jagadita.utils.PreferencesHelper;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigation;
    Button btnLogout;

    PreferencesHelper preferencesHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        preferencesHelper = new PreferencesHelper(this);

        bottomNavigation = findViewById(R.id.bottom_navigation);
        btnLogout = findViewById(R.id.btnLogout);
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                preferencesHelper.logout();
                Intent login = new Intent(MainActivity.this, LoginActivity.class);
                MainActivity.this.startActivity(login);
                finish();
            }
        });

        bottomNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.nav_beranda:
                        getSupportFragmentManager()
                                .beginTransaction()
                                .replace(R.id.main_container, new HomeFragment(), HomeFragment.class.getSimpleName())
                                .commit();

                        break;
                    case R.id.nav_usaha:
                        getSupportFragmentManager()
                                .beginTransaction()
                                .replace(R.id.main_container, new UsahamuFragment(MainActivity.this), UsahamuFragment.class.getSimpleName())
                                .commit();
                        break;
                    case R.id.nav_profil:
                        getSupportFragmentManager()
                                .beginTransaction()
                                .replace(R.id.main_container, new ProfilFragment(), ProfilFragment.class.getSimpleName())
                                .commit();
                        break;
                }
                return true;
            }

        });

        bottomNavigation.setSelectedItemId(R.id.nav_beranda);
    }
}