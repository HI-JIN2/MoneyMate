package com.ssu.moneymate.ui.main.fixed;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.ssu.moneymate.ui.main.goal.GoalFragment;
import com.ssu.moneymate.ui.main.property.PropertyFragment;
import com.ssu.moneymate.ui.main.solution.SolutionFragment;

public class PropertyAdapter extends FragmentStateAdapter {

    public PropertyAdapter (@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new BankFragment();
            case 1:
                return new StockFragment();
            default:
                return null;
        }
    }

    @Override
    public int getItemCount() {
        return 2;       // 페이지 수
    }
}
