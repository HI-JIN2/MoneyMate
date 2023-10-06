package com.ssu.moneymate.ui.main.goal;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;

import com.ssu.moneymate.R;

public class GoalFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_goal, container, false);
        ImageView addButton = view.findViewById(R.id.btn_goal_add);
        addButton.setClickable(true);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), GoalActivity.class);
                startActivity(intent);
                Log.d("GoalFragment", "버튼 클릭 이벤트 실행");
            }
        });

        return view;
    }
}