package com.ssu.moneymate.ui.main.goal;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

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
                Log.d("GoalFragment", "버튼 클릭 이벤트 실행");
            }
        });

        // 삭제 버튼을 클릭했을 때
        binding.ivGoalDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // ImageView와 그 부모 뷰를 가져옴
                ImageView imageViewToDelete = binding.ivGoalBackground;
                ViewGroup parentView = (ViewGroup) imageViewToDelete.getParent();

                if (parentView != null) {
                    parentView.removeView(imageViewToDelete);
                }
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