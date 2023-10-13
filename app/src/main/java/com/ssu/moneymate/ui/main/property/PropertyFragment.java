package com.ssu.moneymate.ui.main.property;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import com.ssu.moneymate.R;
import com.ssu.moneymate.databinding.FragmentBankBinding;
import com.ssu.moneymate.databinding.FragmentPropertyBinding;
import com.ssu.moneymate.ui.main.MainActivity;

import java.util.ArrayList;
import java.util.List;

public class PropertyFragment extends Fragment {
    private FragmentPropertyBinding binding;
    private PropertyViewModel viewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentPropertyBinding.inflate(inflater, container, false);

        viewModel = new ViewModelProvider(this).get(PropertyViewModel.class);

        // ViewModel에서 체크박스 상태 가져오기
        LiveData<Boolean> kbCheckedLiveData = viewModel.isKbChecked();
        LiveData<Boolean> nhCheckedLiveData = viewModel.isNhChecked();
        // PropertyViewModel에서 balance와 nhBalance 가져오기
        LiveData<Integer> balanceLiveData = viewModel.getBalance();
        LiveData<Integer> nhBalanceLiveData = viewModel.getNhBalance();

        binding.layoutKbbank.setVisibility(View.GONE);
        binding.layoutNhbank.setVisibility(View.GONE);

        binding.btnPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ConnectPropertyActivity.class);
                startActivity(intent);
            }
        });

        return binding.getRoot();
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);

        // kbChecked 값을 관찰
        viewModel.isKbChecked().observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean kbChecked) {
                if (kbChecked != null && kbChecked) {
                    binding.layoutKbbank.setVisibility(View.VISIBLE);
                    Log.d("kbcheck", String.valueOf(kbChecked));
                }
            }
        });

        /*// LiveData를 Observer로 관찰하고 상태를 가져옵니다.
        viewModel.isKbChecked().observe(getViewLifecycleOwner(), kbChecked -> {
            if (kbChecked != null && kbChecked) {
                binding.layoutKbbank.setVisibility(View.VISIBLE);
                Log.d("kbcheck", String.valueOf(kbChecked));
            }
        });*/

        viewModel.isNhChecked().observe(getViewLifecycleOwner(), nhChecked -> {
            if (nhChecked != null && nhChecked) {
                binding.layoutNhbank.setVisibility(View.VISIBLE);
            }
        });

        // LiveData를 Observer로 관찰하고 값을 가져오기
        viewModel.getBalance().observe(getViewLifecycleOwner(), balance -> {
            if (balance != null) {
                // balance 값 사용
            }
        });

        viewModel.getNhBalance().observe(getViewLifecycleOwner(), nhBalance -> {
            if (nhBalance != null) {
                // nhBalance 값 사용
            }
        });
    }
}