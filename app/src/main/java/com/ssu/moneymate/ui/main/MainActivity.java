package com.ssu.moneymate.ui.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.ssu.moneymate.R;
import com.ssu.moneymate.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater()); // 1
        setContentView(binding.getRoot()); // 2

        VPAdapter adapter = new VPAdapter(getSupportFragmentManager());
        binding.viewpager.setAdapter(adapter);
    }
}