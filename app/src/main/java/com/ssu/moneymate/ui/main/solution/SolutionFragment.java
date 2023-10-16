package com.ssu.moneymate.ui.main.solution;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ssu.moneymate.databinding.FragmentSolutionBinding;

public class SolutionFragment extends Fragment {
    private FragmentSolutionBinding binding;

    public SolutionFragment() {
        // Required empty public constructor
    }
    public static SolutionFragment newInstance(String param1, String param2) {
        SolutionFragment fragment = new SolutionFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d("view","solution-onCreateView");

        binding = FragmentSolutionBinding.inflate(inflater, container, false);


        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        String result = sharedPreferences.getString("result", "");

        binding.tvSolution.setText(result);
        // Inflate the layout for this fragment
        return binding.getRoot();
    }


    @Override
    public void onResume() {
        super.onResume();
        Log.d("view","solution-onResume");
// 화면이 다시 활성화될 때 SharedPreferences에서 데이터를 가져와서 화면에 설정
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        String result = sharedPreferences.getString("result", "");

        binding.tvSolution.setText(result);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d("view","solution-onDestroyView");

        binding = null;
    }
}