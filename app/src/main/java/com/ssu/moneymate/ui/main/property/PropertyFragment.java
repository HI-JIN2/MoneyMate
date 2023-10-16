package com.ssu.moneymate.ui.main.property;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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

        // PropertyFragment에서 값을 가져오려면 다음과 같이 SharedPreferences에서 값을 읽을 수 있습니다.
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("MyPropertySharedPreferences", Context.MODE_PRIVATE);
        boolean kbChecked = sharedPreferences.getBoolean("kbChecked", false);
        boolean nhChecked = sharedPreferences.getBoolean("nhChecked", false);
        int balance = sharedPreferences.getInt("balance", 0);
        int nhbalance = sharedPreferences.getInt("nhbalance", 0);

        NumberFormat numberFormat = NumberFormat.getNumberInstance(Locale.US); // 미국 로케일을 사용하여 쉼표(,)로 구분
        String formattedValue = numberFormat.format(balance+nhbalance);
        binding.textMainProperty.setText(formattedValue);

        binding.layoutKbbank.setVisibility(View.GONE);
        binding.layoutNhbank.setVisibility(View.GONE);
    }

    @Override
    public void onResume() {
        super.onResume();

        // PropertyFragment에서 값을 가져오려면 다음과 같이 SharedPreferences에서 값을 읽을 수 있습니다.
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("MyPropertySharedPreferences", Context.MODE_PRIVATE);
        boolean kbChecked = sharedPreferences.getBoolean("kbChecked", false);
        boolean nhChecked = sharedPreferences.getBoolean("nhChecked", false);
        int balance = sharedPreferences.getInt("balance", 0);
        int nhbalance = sharedPreferences.getInt("nhbalance", 0);

        Log.d("kbproperty", String.valueOf(kbChecked));

        if (kbChecked && nhChecked) {
            NumberFormat numberFormat = NumberFormat.getNumberInstance(Locale.US); // 미국 로케일을 사용하여 쉼표(,)로 구분
            String formattedValue = numberFormat.format(balance+nhbalance);
            binding.textMainProperty.setText(formattedValue);

            binding.layoutKbbank.setVisibility(View.VISIBLE);
            binding.layoutNhbank.setVisibility(View.VISIBLE);
        }
        else if (kbChecked) {
            NumberFormat numberFormat = NumberFormat.getNumberInstance(Locale.US);
            String formattedValue = numberFormat.format(balance);
            binding.textMainProperty.setText(formattedValue);
            binding.layoutKbbank.setVisibility(View.VISIBLE);
        }
        else if (nhChecked) {
            NumberFormat numberFormat = NumberFormat.getNumberInstance(Locale.US);
            String formattedValue = numberFormat.format(nhbalance);
            binding.textMainProperty.setText(formattedValue);
            binding.layoutNhbank.setVisibility(View.VISIBLE);
        }
        else if (!kbChecked && !nhChecked){
            binding.textMainProperty.setText("0");
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        // SharedPreferences에서 값 제거
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("MyPropertySharedPreferences", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        // 모든 값을 제거하려면 clear()를 사용할 수 있습니다.
        editor.clear();

        editor.apply(); // 변경 사항을 저장
    }
}