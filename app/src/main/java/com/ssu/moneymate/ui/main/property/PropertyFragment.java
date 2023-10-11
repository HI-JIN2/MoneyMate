package com.ssu.moneymate.ui.main.property;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentPropertyBinding.inflate(inflater, container, false);

        binding.btnPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ConnectPropertyActivity.class);
                startActivity(intent);
            }
        });

        return binding.getRoot();
    }
}