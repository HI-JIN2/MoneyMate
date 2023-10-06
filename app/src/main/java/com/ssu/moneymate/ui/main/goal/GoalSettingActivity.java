package com.ssu.moneymate.ui.main.goal;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.ssu.moneymate.R;

public class GoalSettingActivity extends AppCompatActivity {
    private EditText etGoalSettingTarget;
    private EditText etGoalSettingMoney;
    private EditText etGoalSettingYear1;
    private EditText etGoalSettingMonth1;
    private EditText etGoalSettingDay1;
    private EditText etGoalSettingYear2;
    private EditText etGoalSettingMonth2;
    private EditText etGoalSettingDay2;
    private TextView btnGoalSettingComplete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goal_setting);

        // EditText 및 Button 요소를 레이아웃과 연결합니다.
        etGoalSettingTarget = findViewById(R.id.et_goal_setting_target);
        etGoalSettingMoney = findViewById(R.id.et_goal_setting_money);
        etGoalSettingYear1 = findViewById(R.id.et_goal_setting_year1);
        etGoalSettingMonth1 = findViewById(R.id.et_goal_setting_month1);
        etGoalSettingDay1 = findViewById(R.id.et_goal_setting_day1);
        etGoalSettingYear2 = findViewById(R.id.et_goal_setting_year2);
        etGoalSettingMonth2 = findViewById(R.id.et_goal_setting_month2);
        etGoalSettingDay2 = findViewById(R.id.et_goal_setting_day2);
        btnGoalSettingComplete = findViewById(R.id.btn_goal_setting_complete);

        // EditText의 변경 사항을 감지하는 TextWatcher를 설정합니다.
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

        // 모든 EditText에 TextWatcher를 추가합니다.
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
        // 모든 EditText에 입력이 있는지 확인하고 버튼 상태를 업데이트합니다.
        String targetText = etGoalSettingTarget.getText().toString().trim();
        String moneyText = etGoalSettingMoney.getText().toString().trim();
        String year1Text = etGoalSettingYear1.getText().toString().trim();
        String month1Text = etGoalSettingMonth1.getText().toString().trim();
        String day1Text = etGoalSettingDay1.getText().toString().trim();
        String year2Text = etGoalSettingYear2.getText().toString().trim();
        String month2Text = etGoalSettingMonth2.getText().toString().trim();
        String day2Text = etGoalSettingDay2.getText().toString().trim();

        // 모든 필수 입력 필드가 비어 있지 않을 때 버튼을 활성화할 수 있습니다.
        boolean isAllFieldsFilled = !targetText.isEmpty() && !moneyText.isEmpty()
                && !year1Text.isEmpty() && !month1Text.isEmpty() && !day1Text.isEmpty()
                && !year2Text.isEmpty() && !month2Text.isEmpty() && !day2Text.isEmpty();

        btnGoalSettingComplete.setEnabled(isAllFieldsFilled);
    }
}
