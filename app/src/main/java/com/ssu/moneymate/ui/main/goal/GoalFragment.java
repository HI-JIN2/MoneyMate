package com.ssu.moneymate.ui.main.goal;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.ssu.moneymate.databinding.FragmentGoalBinding;

public class GoalFragment extends Fragment {
    private FragmentGoalBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentGoalBinding.inflate(inflater, container, false);

        // SharedPreferences에서 데이터를 가져옴
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        String combinedText = sharedPreferences.getString("combinedText", "");
        binding.btnGoalAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), GoalActivity.class);
                startActivity(intent);
                Log.d("GoalFragment", "버튼 클릭 이벤트 실행");
            }
        });

        if (!combinedText.isEmpty()) {
            Log.d("1", "please");
            binding.tvGoalSettingContent.setText(combinedText);
            binding.ivGoalBackground.setVisibility(View.VISIBLE);
            binding.tvGoalSettingContent.setVisibility(View.VISIBLE);
            binding.ivGoalDelete.setVisibility(View.VISIBLE);
        } else {
            binding.ivGoalBackground.setVisibility(View.GONE);
            binding.tvGoalSettingContent.setVisibility(View.GONE);
            binding.ivGoalDelete.setVisibility(View.GONE);
        }

        // 삭제 버튼을 클릭했을 때
        binding.ivGoalDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImageView imageViewToDelete = binding.ivGoalBackground;
                TextView textViewToDelete = binding.tvGoalSettingContent;
                ImageView deleteImageViewToDelete = binding.ivGoalDelete;

                ViewGroup parentView = (ViewGroup) imageViewToDelete.getParent();

                if (parentView != null) {
                    parentView.removeView(imageViewToDelete);
                    parentView.removeView(textViewToDelete);
                    parentView.removeView(deleteImageViewToDelete);
                }

                // SharedPreferences에서 데이터를 가져와서 비우고 저장
                SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.remove("combinedText"); // combinedText 제거
                editor.apply();
            }
        });
        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}