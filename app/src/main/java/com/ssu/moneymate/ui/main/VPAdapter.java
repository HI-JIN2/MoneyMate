package com.ssu.moneymate.ui.main;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.ssu.moneymate.ui.main.fixed.FixedFragment;
import com.ssu.moneymate.ui.main.goal.GoalFragment;
import com.ssu.moneymate.ui.main.property.PropertyFragment;
import com.ssu.moneymate.ui.main.solution.SolutionFragment;

import java.util.ArrayList;

public class VPAdapter extends FragmentPagerAdapter {
    private ArrayList<Fragment> items;
    public VPAdapter(FragmentManager fm) {
        super(fm);
        items = new ArrayList<Fragment>();
        items.add(new PropertyFragment());
        items.add(new FixedFragment());
        items.add(new GoalFragment());
        items.add(new SolutionFragment());
    }

    @Override
    public Fragment getItem(int position) {
        return items.get(position);
    }

    @Override
    public int getCount() {
        return items.size();
    }
}