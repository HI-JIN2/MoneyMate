package com.ssu.moneymate.ui.main.property;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;

import com.ssu.moneymate.R;
import com.ssu.moneymate.databinding.ActivityPropertyAgreeBinding;

import java.util.ArrayList;
import java.util.List;

public class PropertyAgreeActivity extends AppCompatActivity {

    private ActivityPropertyAgreeBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityPropertyAgreeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        CheckBox check1 = binding.check1;
        CheckBox check2 = binding.check2;

        Intent intent = getIntent();
        boolean kbChecked = intent.getBooleanExtra("kbChecked", false);
        boolean nhChecked = intent.getBooleanExtra("nhChecked", false);

        binding.btnConnect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // CheckBox 상태를 확인하여 필요한 정보를 저장할 리스트를 만듭니다.
                List<String> selectedCheckBoxes = new ArrayList<>();

                if (check1.isChecked()) {
                    selectedCheckBoxes.add("KB 체크");
                }
                if (check2.isChecked()) {
                    selectedCheckBoxes.add("NH 체크");
                }

                // 선택된 CheckBox 정보를 다른 곳으로 넘깁니다.
                for (String checkBoxInfo : selectedCheckBoxes) {
                    Log.d("Agree", checkBoxInfo);
                }

                Intent intent = new Intent(PropertyAgreeActivity.this, MyPropertyActivity.class);
                intent.putExtra("kbChecked", kbChecked);
                intent.putExtra("nhChecked", nhChecked);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}