package com.ssu.moneymate.ui.main.solution;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ssu.moneymate.BuildConfig;
import com.ssu.moneymate.databinding.FragmentSolutionBinding;
import com.ssu.moneymate.ui.main.fixed.FixedData;
import com.ssu.moneymate.ui.main.fixed.RoomDB;

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

public class SolutionFragment extends Fragment {
    private FragmentSolutionBinding binding;

    List<FixedData> items = new ArrayList<>(); //리사이클러 뷰가 보여줄 대량의 데이터를 가지고 있는 리시트객체
    RoomDB database;
    Context context;
    private ProgressDialog progressDialog;


    public SolutionFragment() {
        // Required empty public constructor
    }
    public static SolutionFragment newInstance(String param1, String param2) {
        SolutionFragment fragment = new SolutionFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d("view","solution-onCreateView");

        binding = FragmentSolutionBinding.inflate(inflater, container, false);
        context = getContext();
        database = RoomDB.getInstance(context);

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        String result = sharedPreferences.getString("result", "");

        binding.tvSolution.setText(result);

        binding.btnRecreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                makeSolution(view);
//                Log.d("solution-new",newResult);
//                binding.tvSolution.setText(newResult);
//                openFragment(view);
            }
        });
        // Inflate the layout for this fragment
        return binding.getRoot();
    }


    @Override
    public void onResume() {
        super.onResume();
        Log.d("view","solution-onResume");
// 화면이 다시 활성화될 때 SharedPreferences에서 데이터를 가져와서 화면에 설정
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        String result = sharedPreferences.getString("result", "");

        binding.tvSolution.setText(result);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d("view","solution-onDestroyView");

        binding = null;
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
                        String newResult = jsonObject.getString("result"); // "result" 키의 값을 가져옴

                        String prefixToRemove = "야 합니다";
                        if (newResult.startsWith(prefixToRemove)) {
                            // Remove the prefix from the newResult
                            newResult = newResult.substring(prefixToRemove.length());
                        }
                        // Update the UI on the main thread
                        String finalNewResult = newResult;
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                // Set the newResult as the text for binding.tvSolution
                                binding.tvSolution.setText(finalNewResult);
                            }
                        });

                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("result", newResult);
                        editor.apply();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    // ProgressDialog 닫기
                    progressDialog.dismiss();
                }
            }
        }).start(); // 백그라운드 스레드 실행

        return result;
    }
}