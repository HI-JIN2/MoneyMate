package com.ssu.moneymate.ui.main.solution;

import android.app.ProgressDialog;
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
import java.time.LocalDate;
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
    private ProgressDialog progressDialog;

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
        Log.d("view","non-solution-onCreateView");

        binding.btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                makeSolution(view);
//                openFragment(view);
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


    public String makeSolution(View myView) {
        items.addAll(database.fixedDao().getAll());
        long total = 0;
        for (int i = 0; i < items.size(); i++) {
            total += Integer.parseInt(items.get(i).getMoney());
        }

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        String combinedText = sharedPreferences.getString("combinedText", "");
        int balance = sharedPreferences.getInt("balance", 0);
        int nhbalance = sharedPreferences.getInt("nhbalance", 0);


        Log.d("solution", String.valueOf(total));
        Log.d("solution",combinedText);
        Log.d("solution", String.valueOf(balance+nhbalance));
        // 현재 날짜 구하기
        LocalDate now = LocalDate.now();



        String requestText = "오늘은 "+now+"이다. "+
                "현재 자산 : "+balance+nhbalance+"원, "+
                "부채 : 0원," +
                "고정지출 : "+total+"원, "+
                "세운 목표들 : "+combinedText+
                " → 자산 관리를 위한 계획을 세워줘";

//        String requestText = "나는 현재 60만원을 가지고 있고 매달 5만원의 고정지출이 있어. 2달 뒤에 일본으로 여행을 가고 싶어. 자산 관리를 위한 계획을 세워줘";
        String result = "";

        // ProgressDialog 생성 및 설정
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("로딩 중..."); // 로딩 중 메시지 설정
        progressDialog.setCancelable(false); // 사용자가 취소할 수 없도록 설정
        progressDialog.show(); // ProgressDialog 표시

        // 네트워크 요청을 백그라운드 스레드에서 실행
        new Thread(new Runnable() {
            @Override
            public void run() {
                // API 엔드포인트 URL
                String apiUrl = BuildConfig.gpt;

                // JSON 요청 바디 (이 부분은 API에 따라 다를 수 있음)
                String jsonRequestBody = "{\"prompt\":\"" + requestText+ "\"}";

                // OkHttp 클라이언트 생성
                OkHttpClient client = new OkHttpClient.Builder()
                        .connectTimeout(120, TimeUnit.SECONDS) // 연결 시간 초과 설정
                        .readTimeout(120, TimeUnit.SECONDS)    // 읽기 시간 초과 설정
                        .writeTimeout(120, TimeUnit.SECONDS)   // 쓰기 시간 초과 설정
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
                    Log.d("GPT-re",request.toString());

                    Log.d("GPT-json",jsonRequestBody);
                    // API 응답 문자열 가져오기
                    String apiResponse = response.body().string();
                    Log.d("GPT", apiResponse);

                    try {
                        JSONObject jsonObject = new JSONObject(apiResponse); // JSON 문자열을 JSONObject로 파싱
                        String result = jsonObject.getString("result"); // "result" 키의 값을 가져옴

                        String prefixToRemove = "야 합니다";
                        if (result.startsWith(prefixToRemove)) {
                            // Remove the prefix from the newResult
                            result = result.substring(prefixToRemove.length());
                        }
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("result", result);
                        editor.apply();

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    // ProgressDialog 닫기
                    progressDialog.dismiss();
                    openFragment(myView);
                }
            }
        }).start(); // 백그라운드 스레드 실행

        return result;
    }
}