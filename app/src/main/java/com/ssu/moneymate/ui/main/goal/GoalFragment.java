package com.ssu.moneymate.ui.main.goal;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import com.ssu.moneymate.R;

public class GoalFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_goal, container, false);

        // "hello" 버튼을 가져오고 클릭 이벤트 처리
        Button addButton = view.findViewById(R.id.btn_goal_add);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), GoalSettingActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }
}