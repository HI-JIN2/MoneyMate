package com.ssu.moneymate.ui.main.property;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;

import com.google.gson.Gson;
import com.ssu.moneymate.R;
import com.ssu.moneymate.databinding.ActivityMyPropertyBinding;
import com.ssu.moneymate.databinding.ActivityPropertyAgreeBinding;
import com.ssu.moneymate.ui.main.MainActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import com.google.gson.annotations.SerializedName;
import com.ssu.moneymate.ui.main.fixed.FixedData;

public class MyPropertyActivity extends AppCompatActivity {

    private ActivityMyPropertyBinding binding;
    private PropertyViewModel viewModel;

    PropertyDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMyPropertyBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent intent = getIntent();
        boolean kbChecked = intent.getBooleanExtra("kbChecked", false);
        boolean nhChecked = intent.getBooleanExtra("nhChecked", false);

        viewModel = new ViewModelProvider(this).get(PropertyViewModel.class);
        database = PropertyDatabase.getInstance(this);

        binding.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyPropertyActivity.this, MainActivity.class);
                intent.putExtra("kbChecked", kbChecked);
                intent.putExtra("nhChecked", nhChecked);

                viewModel.setKbChecked(kbChecked);
                viewModel.setNhChecked(nhChecked);

                startActivity(intent);
            }
        });

        // 네트워크 요청을 백그라운드 스레드에서 실행
        new Thread(new Runnable() {
            @Override
            public void run() {
                // API 엔드포인트 URL
                String apiUrl = "https://8012502446.for-seoul.synctreengine.com/api/bank";

                // JSON 요청 바디 (이 부분은 API에 따라 다를 수 있음)
                String jsonRequestBody = "{\"key\":\"value\"}"; // 예시 요청 바디

                // OkHttp 클라이언트 생성
                OkHttpClient client = new OkHttpClient();

                // JSON 요청 바디의 MediaType 설정
                MediaType JSON = MediaType.parse("application/json; charset=utf-8");

                // API 요청 생성
                Request request = new Request.Builder()
                        .url(apiUrl)
                        .post(RequestBody.create(jsonRequestBody, JSON))
                        .build();

                try {
                    // API 요청 실행
                    Response response = client.newCall(request).execute();

                    // API 응답 문자열 가져오기
                    String apiResponse = response.body().string();

                    try {
                        // 전체 응답을 JSON 객체로 파싱
                        JSONObject responseObject = new JSONObject(apiResponse);

                        // KBresult의 response의 body의 dataBody에서 데이터부 가져오기
                        JSONObject kbResultDataBody = responseObject.getJSONObject("KBresult").getJSONObject("response").getJSONObject("body").getJSONObject("dataBody");

                        // NHresult의 response의 body에서 RlpmAbamt 가져오기
                        JSONObject nhResultResponse = responseObject.getJSONObject("NHresult").getJSONObject("response");
                        String NHbalance = nhResultResponse.getJSONObject("body").getString("RlpmAbamt");


                        // 데이터부에서 잔액 가져오기
                        JSONArray dataItems = kbResultDataBody.getJSONArray("데이터부");
                        JSONObject dataItem = dataItems.getJSONObject(0); // 이 예시에서는 첫 번째 아이템을 가져옴
                        String balanceStr = dataItem.getString("잔액");

                        // 잔액을 정수로 변환
                        int balance = Integer.parseInt(balanceStr);
                        int nhbalance = Integer.parseInt(NHbalance);

                        // UI를 업데이트하기 위해 runOnUiThread를 사용
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                viewModel.setBalance(balance);
                                viewModel.setNhBalance(nhbalance);

                                // 데이터를 Room 데이터베이스에 삽입
                                PropertyData propertyData = new PropertyData();
                                propertyData.setBalance(balance);

                                PropertyData nhPropertyData = new PropertyData();
                                nhPropertyData.setBalance(nhbalance);

                                database.propertyDataDao().insert(propertyData);
                                database.propertyDataDao().insert(nhPropertyData);

                                // 이제 'balance' 변수에 잔액 값이 저장되어 있습니다.
                                // NumberFormat 사용
                                NumberFormat numberFormat = NumberFormat.getNumberInstance(Locale.US); // 미국 로케일을 사용하여 쉼표(,)로 구분
                                String formattedValue = numberFormat.format(balance);
                                String formattedNHValue = numberFormat.format(nhbalance);

                                binding.textKBbalance.setText(formattedValue);
                                binding.textNHbalance.setText(formattedNHValue);

                                Log.d("propertydata", String.valueOf(nhbalance));
                            }
                        });

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start(); // 백그라운드 스레드 실행

        if (!kbChecked)
            binding.layoutMypropertyKb.setVisibility(View.GONE);
        if (!nhChecked)
            binding.layoutPropertyNh.setVisibility(View.GONE);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}