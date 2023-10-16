package com.ssu.moneymate.ui.main.solution;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ssu.moneymate.BuildConfig;
import com.ssu.moneymate.R;
import com.ssu.moneymate.databinding.FragmentBankBinding;
import com.ssu.moneymate.databinding.FragmentGoalBinding;
import com.ssu.moneymate.databinding.FragmentNonSolutionBinding;
import com.ssu.moneymate.ui.main.fixed.FixedData;
import com.ssu.moneymate.ui.main.fixed.RoomDB;
import com.ssu.moneymate.ui.main.goal.GoalActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class NonSolutionFragment extends Fragment {
    private FragmentNonSolutionBinding binding;

    List<FixedData> items = new ArrayList<>(); //리사이클러 뷰가 보여줄 대량의 데이터를 가지고 있는 리시트객체
    RoomDB database;

    Context context;

    public NonSolutionFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        binding = FragmentNonSolutionBinding.inflate(inflater, container, false);
        context = getContext();
        database = RoomDB.getInstance(context);
//        items = database.fixedDao().getAll();

        // 뷰 모델 초기화
//        viewModel = new ViewModelProvider(this).get(UserViewModel.class);

//        // 데이터 추가 예시
//        viewModel.addItem("아이템 1");
//        viewModel.addItem("아이템 2");
//
//        // 데이터 가져오기
//        List<String> itemList = viewModel.getItemList();
//        for (String item : itemList) {
//            // 데이터 처리
//        }



        binding.btnRecreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openFragment(view);
                makeSolution();
            }
        });
        return binding.getRoot();
    }

    // 이 메서드는 버튼을 클릭할 때 호출됩니다.
    public void openFragment(View view) {
        // 두 번째 프래그먼트를 열기 위한 트랜잭션을 시작합니다.
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.layout_non, new SolutionFragment())
                .addToBackStack(null)  // 이전 프래그먼트 스택에 추가
                .commit();
    }


    public String makeSolution(){
        items.addAll(database.fixedDao().getAll());
        long  total = 0;
        for (int i = 0; i < items.size(); i++) {
            total += Integer.parseInt(items.get(i).getMoney());
        }

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        String combinedText = sharedPreferences.getString("combinedText", "");

        Log.d("solution",combinedText);
        Log.d("solution", String.valueOf(total));

        String requestText = "안녕, 나의 자산 방법에 대해서 조언을 해줘." +
                "나는 매달 "+total+"원의 고정지출이 있어."+
                "나는 "+combinedText+"라는 목표를 가지고 있어"+
                "자산 관리 팁을 알려줄래?";

        String result = "";

        // 네트워크 요청을 백그라운드 스레드에서 실행
        new Thread(new Runnable() {
            @Override
            public void run() {
                // API 엔드포인트 URL
                String apiUrl = BuildConfig.gpt_url;


                // JSON 요청 바디 (이 부분은 API에 따라 다를 수 있음)
                String jsonRequestBody = "{\"content\":\"" + "hello" + "\"}";

                // OkHttp 클라이언트 생성
                OkHttpClient client = new OkHttpClient.Builder()
                        .connectTimeout(30, TimeUnit.SECONDS) // 연결 시간 초과 설정
                        .readTimeout(30, TimeUnit.SECONDS)    // 읽기 시간 초과 설정
                        .writeTimeout(30, TimeUnit.SECONDS)   // 쓰기 시간 초과 설정
                        .build();

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
                    Log.d("solution",apiResponse);

                    try {
                        JSONObject jsonObject = new JSONObject(apiResponse); // JSON 문자열을 JSONObject로 파싱
                        String result = jsonObject.getString("result"); // "result" 키의 값을 가져옴

//                        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("result", result);
                        editor.apply();


                        // UI를 업데이트하기 위해 runOnUiThread를 사용
//                        runOnUiThread(new Runnable() {
//                            @Override
//                            public void run() {
//                                Log.d("stockdata", firstKcisGoodNm);
//                                Log.d("stockdata", secondKcisGoodNm);
//
//                                binding.textStock1.setText(firstKcisGoodNm);
//                                binding.textStock2.setText(secondKcisGoodNm);
//                            }
//                        });
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start(); // 백그라운드 스레드 실행

        return result;
    }
}