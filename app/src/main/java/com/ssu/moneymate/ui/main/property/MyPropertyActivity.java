package com.ssu.moneymate.ui.main.property;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;

import com.ssu.moneymate.R;
import com.ssu.moneymate.databinding.ActivityMyPropertyBinding;
import com.ssu.moneymate.databinding.ActivityPropertyAgreeBinding;
import com.ssu.moneymate.ui.main.MainActivity;

import java.util.ArrayList;
import java.util.List;

public class MyPropertyActivity extends AppCompatActivity {

    private ActivityMyPropertyBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMyPropertyBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyPropertyActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}