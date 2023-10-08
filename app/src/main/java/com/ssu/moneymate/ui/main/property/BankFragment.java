package com.ssu.moneymate.ui.main.property;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import com.ssu.moneymate.R;
import com.ssu.moneymate.databinding.FragmentBankBinding;

import java.util.ArrayList;
import java.util.List;

public class BankFragment extends Fragment {
        private FragmentBankBinding binding;

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            if (getArguments() != null) {
            }
        }

        /*@Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            // Inflate the layout for this fragment
            //return inflater.inflate(R.layout.fragment_bank, container, false);
        }*/

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentBankBinding.inflate(inflater, container, false);

        CheckBox kb_check = binding.check1;
        CheckBox nh_check = binding.check2;
        CheckBox nh_finance_check = binding.check3;

        binding.btnConnect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // CheckBox 상태를 확인하여 필요한 정보를 저장할 리스트를 만듭니다.
                List<String> selectedCheckBoxes = new ArrayList<>();

                if (kb_check.isChecked()) {
                    selectedCheckBoxes.add("KB 체크");
                }
                if (nh_check.isChecked()) {
                    selectedCheckBoxes.add("NH 체크");
                }
                if (nh_finance_check.isChecked()) {
                    selectedCheckBoxes.add("NH 금융 체크");
                }

                // 선택된 CheckBox 정보를 다른 곳으로 넘깁니다.
                for (String checkBoxInfo : selectedCheckBoxes) {
                    Log.d("CheckBoxInfo", checkBoxInfo);
                }
            }
        });
        return binding.getRoot();
    }
}