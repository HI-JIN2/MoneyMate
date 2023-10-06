package com.ssu.moneymate.ui.main;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.ssu.moneymate.R;
import com.ssu.moneymate.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        PagerAdapter viewPager2Adapter
                = new PagerAdapter(getSupportFragmentManager(), getLifecycle());
        ViewPager2 viewPager2 = findViewById(R.id.pager);
        viewPager2.setAdapter(viewPager2Adapter);

        //=== TabLayout기능 추가 부분 ============================================
        TabLayout tabLayout = findViewById(R.id.tab_layout);
        new TabLayoutMediator(tabLayout, viewPager2, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                // position에 따라 각 탭에 원하는 텍스트를 설정합니다.
                switch (position) {
                    case 0:
                        tab.setText("자산");
                        break;
                    case 1:
                        tab.setText("고정지출");
                        break;
                    case 2:
                        tab.setText("목표설정");
                        break;
                    case 3:
                        tab.setText("솔루션");
                        break;
                    // 필요한 만큼 case를 추가하여 각 탭에 원하는 텍스트를 설정합니다.
                    default:
                        tab.setText("Tab " + (position + 1));
                        break;
                }
            }
        }).attach();
    }
}