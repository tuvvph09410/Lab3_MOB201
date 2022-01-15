package com.example.lab3_mob201;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Switch;

import com.example.lab3_mob201.Fragment.Fragment_Bai1;
import com.example.lab3_mob201.Fragment.Fragment_Bai2;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {
    private BottomNavigationView bottomNavigationView;
    private ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.initViewByID();
        this.initBottomNav();
    }

    private void initBottomNav() {
        this.bottomNavigationView.setOnNavigationItemSelectedListener(this);
        this.actionBar.setTitle("Bài 1");
        this.bottomNavigationView.getMenu().findItem(R.id.bai1).setChecked(true);
        loadFragment(new Fragment_Bai1());
    }

    private void initViewByID() {
        this.actionBar = getSupportActionBar();
        this.bottomNavigationView = findViewById(R.id.bottom_nav);
    }

    private void loadFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container_view_tag, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.bai1:
                this.actionBar.setTitle("Bài 1");
                Fragment_Bai1 fragment_bai1 = new Fragment_Bai1();
                loadFragment(fragment_bai1);
                checkItemBottomNav(R.id.bai1);
                break;
            case R.id.bai2:
                this.actionBar.setTitle("Bài 2");
                Fragment_Bai2 fragment_bai2 = new Fragment_Bai2();
                loadFragment(fragment_bai2);
                checkItemBottomNav(R.id.bai2);
                break;
        }
        return true;
    }

    private void checkItemBottomNav(int Item) {
        this.bottomNavigationView.getMenu().findItem(Item).setChecked(true);
    }
}