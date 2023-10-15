package com.ssu.moneymate.ui.main.property;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.ssu.moneymate.R;
import com.ssu.moneymate.databinding.ActivityMyPropertyBinding;
import com.ssu.moneymate.databinding.ActivityMyStockBinding;
import com.ssu.moneymate.ui.main.MainActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.NumberFormat;
import java.util.Locale;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MyStockActivity extends AppCompatActivity {

    private ActivityMyStockBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMyStockBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyStockActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        // 네트워크 요청을 백그라운드 스레드에서 실행
        new Thread(new Runnable() {
            @Override
            public void run() {
                // API 엔드포인트 URL
                String apiUrl = "https://8012502446.for-seoul.synctreengine.com/api/stock";

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
                        JSONObject kyoboResultDataBody = responseObject.getJSONObject("KyoboResult").getJSONObject("response").getJSONObject("body");

                        // 데이터부에서 "goodList" 배열 가져오기
                        JSONArray goodList = kyoboResultDataBody.getJSONArray("goodList");

                        // "kcisGoodNm" 값을 저장할 변수들
                        String firstKcisGoodNm = goodList.getJSONObject(20).getString("kcisGoodNm");
                        String secondKcisGoodNm = goodList.getJSONObject(30).getString("kcisGoodNm");

                        // UI를 업데이트하기 위해 runOnUiThread를 사용
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Log.d("stockdata", firstKcisGoodNm);
                                Log.d("stockdata", secondKcisGoodNm);

                                binding.textStock1.setText(firstKcisGoodNm);
                                binding.textStock2.setText(secondKcisGoodNm);
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
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}