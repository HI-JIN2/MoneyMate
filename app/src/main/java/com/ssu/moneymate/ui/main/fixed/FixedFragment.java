package com.ssu.moneymate.ui.main.fixed;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.ssu.moneymate.databinding.FragmentFixedBinding;

import java.util.ArrayList;
import java.util.List;

public class FixedFragment extends Fragment {
    Context context;
    FragmentFixedBinding binding;

    List<FixedData> items = new ArrayList<>(); //리사이클러 뷰가 보여줄 대량의 데이터를 가지고 있는 리시트객체
    FixedAdapter fixedAdapter;
    RoomDB database;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        context = getContext();
        binding = FragmentFixedBinding.inflate(inflater, container, false);
        database = RoomDB.getInstance(context);
//        database.fixedDao().deleteAll();
        items = database.fixedDao().getAll();

        binding.btnFixAdd.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), FixActivity.class);
                startActivity(intent);
            }
        });

        fixedAdapter = new FixedAdapter(context, items);
        binding.rvFixedList.setAdapter(fixedAdapter);
        binding.rvFixedList.setLayoutManager(new LinearLayoutManager(getActivity()));

        // Inflate the layout for this fragment
        return binding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();

        // 화면이 다시 보일 때 RecyclerView를 업데이트
        items.clear();
        items.addAll(database.fixedDao().getAll());
        fixedAdapter.notifyDataSetChanged();
    }
}