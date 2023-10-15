package com.ssu.moneymate.ui.main.fixed;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.ssu.moneymate.databinding.FragmentFixedBinding;

import java.util.ArrayList;

public class FixedFragment extends Fragment {
    Context context;
    FragmentFixedBinding binding;

    ArrayList<FixedData> items = new ArrayList<>(); //리사이클러 뷰가 보여줄 대량의 데이터를 가지고 있는 리시트객체
    FixedAdapter fixedAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentFixedBinding.inflate(inflater, container, false);

        items.add(new FixedData("적금", 200000));
        items.add(new FixedData("저축", 200000));
        items.add(new FixedData("교통", 230000));
        items.add(new FixedData("적금", 200000));
        items.add(new FixedData("저축", 400000));
        items.add(new FixedData("적금", 260000));
        items.add(new FixedData("저축", 400000));
        items.add(new FixedData("저축", 400000));

        fixedAdapter = new FixedAdapter(context, items);
        binding.rvFixedList.setAdapter(fixedAdapter);
        binding.rvFixedList.setLayoutManager(new LinearLayoutManager(getActivity()));

        // Inflate the layout for this fragment
        return binding.getRoot();
    }
}