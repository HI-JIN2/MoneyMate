package com.ssu.moneymate.ui.main.data.service;

import com.ssu.moneymate.ui.main.data.entity.request.RequestDeleteGoalDto;
import com.ssu.moneymate.ui.main.data.entity.request.RequestPostGoalDto;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiService {
    @POST("api/v1/goal")
    Call<RequestPostGoalDto> postGoal(
            @Body RequestPostGoalDto requestPostGoalDto
    );

    @POST("api/v1/goal")
    Call<RequestDeleteGoalDto> deleteGoal(
            @Body RequestDeleteGoalDto requestDeleteGoalDto
    );
}
