package com.ssu.moneymate.ui.main.solution;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ssu.moneymate.R;
import com.ssu.moneymate.databinding.FragmentBankBinding;
import com.ssu.moneymate.databinding.FragmentGoalBinding;
import com.ssu.moneymate.databinding.FragmentNonSolutionBinding;

public class NonSolutionFragment extends Fragment {
    private FragmentNonSolutionBinding binding;

    public NonSolutionFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        binding = FragmentNonSolutionBinding.inflate(inflater, container, false);

        binding.btnRecreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openFragment(view);
            }
        });
        return binding.getRoot();
    }

    // 이 메서드는 버튼을 클릭할 때 호출됩니다.
    public void openFragment(View view) {
        // 두 번째 프래그먼트를 열기 위한 트랜잭션을 시작합니다.
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.layout_non, new SolutionFragment())
                .addToBackStack(null)  // 이전 프래그먼트 스택에 추가
                .commit();
    }
}