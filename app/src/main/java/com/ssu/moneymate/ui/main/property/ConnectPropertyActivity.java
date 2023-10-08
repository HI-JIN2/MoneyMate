package com.ssu.moneymate.ui.main.property;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.ssu.moneymate.R;

public class ConnectPropertyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connect_property);

        ImageButton btnReviewClose = findViewById(R.id.btn_review_close);

        // 현재 Fragment 또는 Activity를 종료하고 이전 화면으로 돌아가기
        btnReviewClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        PropertyAdapter viewPager2Adapter
                = new PropertyAdapter(getSupportFragmentManager(), getLifecycle());
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
                        tab.setText("은행");
                        break;
                    case 1:
                        tab.setText("보험");
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