package com.ssu.moneymate.ui.main.goal;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.ssu.moneymate.R;

public class GoalActivity extends AppCompatActivity {
    private EditText etGoalSettingTarget;
    private EditText etGoalSettingMoney;
    private EditText etGoalSettingYear1;
    private EditText etGoalSettingMonth1;
    private EditText etGoalSettingDay1;
    private EditText etGoalSettingYear2;
    private EditText etGoalSettingMonth2;
    private EditText etGoalSettingDay2;
    private TextView btnGoalSettingComplete;

    private String combinedText = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goal);

        // EditText 및 Button 요소를 레이아웃과 연결
        etGoalSettingTarget = findViewById(R.id.et_goal_setting_target);
        etGoalSettingMoney = findViewById(R.id.et_goal_setting_money);
        etGoalSettingYear1 = findViewById(R.id.et_goal_setting_year1);
        etGoalSettingMonth1 = findViewById(R.id.et_goal_setting_month1);
        etGoalSettingDay1 = findViewById(R.id.et_goal_setting_day1);
        etGoalSettingYear2 = findViewById(R.id.et_goal_setting_year2);
        etGoalSettingMonth2 = findViewById(R.id.et_goal_setting_month2);
        etGoalSettingDay2 = findViewById(R.id.et_goal_setting_day2);
        btnGoalSettingComplete = findViewById(R.id.btn_goal_setting_complete);

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        combinedText = sharedPreferences.getString("", "");

        // EditText의 변경 사항을 감지하는 TextWatcher를 설정
        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // 텍스트 변경 전에 수행할 작업
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // 텍스트 변경 시 호출되는 메서드
                checkEditTextInputs();
            }

            @Override
            public void afterTextChanged(Editable editable) {
                // 텍스트 변경 후에 수행할 작업
            }
        };

        etGoalSettingTarget.addTextChangedListener(textWatcher);
        etGoalSettingMoney.addTextChangedListener(textWatcher);
        etGoalSettingYear1.addTextChangedListener(textWatcher);
        etGoalSettingMonth1.addTextChangedListener(textWatcher);
        etGoalSettingDay1.addTextChangedListener(textWatcher);
        etGoalSettingYear2.addTextChangedListener(textWatcher);
        etGoalSettingMonth2.addTextChangedListener(textWatcher);
        etGoalSettingDay2.addTextChangedListener(textWatcher);

        // 초기에 버튼 비활성화
        btnGoalSettingComplete.setEnabled(false);
    }

    private void checkEditTextInputs() {
        // 모든 EditText에 입력이 있는지 확인하고 버튼 상태를 업데이트
        String targetText = etGoalSettingTarget.getText().toString().trim();
        String moneyText = etGoalSettingMoney.getText().toString().trim();
        String year1Text = etGoalSettingYear1.getText().toString().trim();
        String month1Text = etGoalSettingMonth1.getText().toString().trim();
        String day1Text = etGoalSettingDay1.getText().toString().trim();
        String year2Text = etGoalSettingYear2.getText().toString().trim();
        String month2Text = etGoalSettingMonth2.getText().toString().trim();
        String day2Text = etGoalSettingDay2.getText().toString().trim();

        // 모든 필수 입력 필드가 비어 있지 않을 때 버튼을 활성화
        boolean isAllFieldsFilled = !targetText.isEmpty() && !moneyText.isEmpty()
                && !year1Text.isEmpty() && !month1Text.isEmpty() && !day1Text.isEmpty()
                && !year2Text.isEmpty() && !month2Text.isEmpty() && !day2Text.isEmpty();

        // 버튼의 속성 변경
        btnGoalSettingComplete.setEnabled(isAllFieldsFilled);

        if (isAllFieldsFilled) {
            btnGoalSettingComplete.setTextColor(getResources().getColor(R.color.white)); // 텍스트 색상 변경
            btnGoalSettingComplete.setBackgroundResource(R.drawable.shape_diamond500_fill_20_rect); // 배경색상 변경
        } else {
            // 필드가 하나라도 빈 경우
            btnGoalSettingComplete.setTextColor(getResources().getColor(R.color.gray_200)); // 텍스트 색상 초기화
            btnGoalSettingComplete.setBackgroundResource(R.drawable.shape_gray500_fill_20_rect); // 배경색상 초기화
        }
        btnGoalSettingComplete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String targetText2 = String.valueOf(etGoalSettingTarget.getText());
                String moneyText2 = String.valueOf(etGoalSettingMoney.getText());
                String year2Text2 = String.valueOf(etGoalSettingYear2.getText());
                String month2Text2 = String.valueOf(etGoalSettingMonth2.getText());
                String day2Text2 = String.valueOf(etGoalSettingDay2.getText());

                combinedText = "나는 " + year2Text2 + "년 " + month2Text2 + "월 " + day2Text2 + "일까지\n" + targetText2 + "을(를) 위해\n" + moneyText2 + "을(를) 모을 거야";

                SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(GoalActivity.this);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("combinedText", combinedText);
                editor.apply();
                editor.commit();
                finish();
            }
        });
    }
}
