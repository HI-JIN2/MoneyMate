package com.ssu.moneymate.ui.main.property;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
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
import com.ssu.moneymate.ui.main.fixed.FixedData;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class PropertyFragment extends Fragment {
    private FragmentPropertyBinding binding;
    private PropertyViewModel viewModel;

    List<PropertyData> items = new ArrayList<>();
    PropertyDatabase database;

    /*// ViewModel에서 체크박스 상태 가져오기
    LiveData<Boolean> kbCheckedLiveData;
    LiveData<Boolean> nhCheckedLiveData;
    // PropertyViewModel에서 balance와 nhBalance 가져오기
    LiveData<Integer> balanceLiveData;
    LiveData<Integer> nhBalanceLiveData;*/

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentPropertyBinding.inflate(inflater, container, false);

        /*binding.layoutKbbank.setVisibility(View.GONE);
        binding.layoutNhbank.setVisibility(View.GONE);*/

        viewModel = new ViewModelProvider(requireActivity()).get(PropertyViewModel.class);
        database = PropertyDatabase.getInstance(getContext());
        items = database.propertyDataDao().getAll();

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
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        /*viewModel.isKbChecked().observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean kbCheckedValue) {
                if (kbCheckedValue != null && kbCheckedValue) {
                    binding.layoutKbbank.setVisibility(View.VISIBLE);
                } else {
                    binding.layoutKbbank.setVisibility(View.GONE);
                }
            }
        });


        viewModel.isNhChecked().observe(getViewLifecycleOwner(), aBoolean -> {
            if (Boolean.TRUE.equals(aBoolean))
                binding.layoutNhbank.setVisibility(View.VISIBLE);
            else
                binding.layoutNhbank.setVisibility(View.GONE);
        });*/
    }

    @Override
    public void onResume() {
        super.onResume();
        /*//옵저버 정의 - 데이터가 변하는 이벤트 발생시 처리할 핸들러(람다)
        Observer<Boolean> kbObserver = kbCheckedValue -> binding.layoutKbbank.setVisibility(View.VISIBLE);

        // LiveData를 관찰하고 UI 업데이트를 수행하는 Observer
        viewModel.isKbChecked().observe(this, kbObserver);

        //viewModel.isKbChecked().getValue()

            @Override
            public void onChanged(Boolean kbCheckedValue) {
                Log.d("kbsetproperty", String.valueOf(kbCheckedValue));

                if (kbCheckedValue != null && kbCheckedValue) {
                    binding.layoutKbbank.setVisibility(View.VISIBLE);
                } else {
                    binding.layoutKbbank.setVisibility(View.GONE);
                }
            }

        viewModel.isNhChecked().observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean nhCheckedValue) {
                if (nhCheckedValue != null && nhCheckedValue) {
                    binding.layoutNhbank.setVisibility(View.VISIBLE);
                } else {
                    binding.layoutNhbank.setVisibility(View.GONE);
                }
            }
        });*/

        /*binding.layoutKbbank.setVisibility(View.VISIBLE);
        binding.layoutNhbank.setVisibility(View.VISIBLE);

        NumberFormat numberFormat = NumberFormat.getNumberInstance(Locale.US); // 미국 로케일을 사용하여 쉼표(,)로 구분
        String formattedValue = numberFormat.format(3863175+4500000);
        binding.textMainProperty.setText(formattedValue);*/
    }
}