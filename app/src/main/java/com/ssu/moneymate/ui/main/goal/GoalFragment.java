package com.ssu.moneymate.ui.main.goal;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.ssu.moneymate.databinding.FragmentGoalBinding;

public class GoalFragment extends Fragment {
    private FragmentGoalBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentGoalBinding.inflate(inflater, container, false);

        binding.btnGoalAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), GoalActivity.class);
                startActivity(intent);
            }
        });

        binding.ivGoalModify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), GoalActivity.class);
                startActivity(intent);
            }
        });


        // 삭제 버튼을 클릭했을 때
        binding.ivGoalDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CardView imageViewToDelete = binding.ivGoalBackground;
                TextView textViewToDelete = binding.tvGoalSettingContent;
                ImageView deleteImageViewToDelete = binding.ivGoalDelete;
                ImageView modifyImageViewToDelete = binding.ivGoalModify;

                ViewGroup parentView = (ViewGroup) imageViewToDelete.getParent();

                if (parentView != null) {
                    parentView.removeView(imageViewToDelete);
                    parentView.removeView(textViewToDelete);
                    parentView.removeView(deleteImageViewToDelete);
                    parentView.removeView(modifyImageViewToDelete);
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
    public void onResume() {
        super.onResume();
        // 화면이 다시 활성화될 때 SharedPreferences에서 데이터를 가져와서 화면에 설정
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        String combinedText = sharedPreferences.getString("combinedText", "");

        if (!combinedText.isEmpty()) {
            binding.tvGoalSettingContent.setText(combinedText);
            binding.ivGoalBackground.setVisibility(View.VISIBLE);
            binding.tvGoalSettingContent.setVisibility(View.VISIBLE);
            binding.ivGoalDelete.setVisibility(View.VISIBLE);
            binding.ivGoalModify.setVisibility(View.VISIBLE);
            binding.btnGoalAdd.setEnabled(false);
        } else {
            binding.ivGoalBackground.setVisibility(View.GONE);
            binding.tvGoalSettingContent.setVisibility(View.GONE);
            binding.ivGoalDelete.setVisibility(View.GONE);
            binding.ivGoalModify.setVisibility(View.GONE);
            binding.btnGoalAdd.setEnabled(true);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}