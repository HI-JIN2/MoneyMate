package com.ssu.moneymate.ui.main;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.ssu.moneymate.ui.main.fixed.FixedFragment;
import com.ssu.moneymate.ui.main.goal.GoalFragment;
import com.ssu.moneymate.ui.main.property.PropertyFragment;
import com.ssu.moneymate.ui.main.solution.NonSolutionFragment;
import com.ssu.moneymate.ui.main.solution.SolutionFragment;

import java.util.ArrayList;

public class PagerAdapter extends FragmentStateAdapter {

    public PagerAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new PropertyFragment();
            case 1:
                return new FixedFragment();
            case 2:
                return new GoalFragment();
            case 3:
                return new NonSolutionFragment();
            default:
                return null;
        }
    }

    @Override
    public int getItemCount() {
        return 4;       // 페이지 수
    }
}