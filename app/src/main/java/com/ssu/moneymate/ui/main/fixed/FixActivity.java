package com.ssu.moneymate.ui.main.fixed;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.ssu.moneymate.R;
import com.ssu.moneymate.databinding.ActivityFixedBinding;

import java.util.zip.Inflater;

public class FixActivity extends AppCompatActivity {

    private ActivityFixedBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFixedBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                checkEditTextInputs();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        };

        binding.etFixMoney.addTextChangedListener(textWatcher);
        binding.btnFixSettingComplete.setEnabled(false);
    }

    private void checkEditTextInputs() {
        String targetText = binding.etFixMoney.getText().toString().trim();
        boolean isFieldFilled = !targetText.isEmpty();
        binding.btnFixSettingComplete.setEnabled(isFieldFilled);

        if (isFieldFilled) {
            binding.btnFixSettingComplete.setTextColor(getResources().getColor(R.color.white));
            binding.btnFixSettingComplete.setBackgroundResource(R.drawable.shape_diamond500_fill_20_rect);
        } else {
            binding.btnFixSettingComplete.setTextColor(getResources().getColor(R.color.Gray_50)); // 텍스트 색상 초기화
            binding.btnFixSettingComplete.setBackgroundResource(R.drawable.shape_gray500_fill_20_rect);
        }

        binding.btnFixSettingComplete.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
