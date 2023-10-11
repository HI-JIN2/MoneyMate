package com.ssu.moneymate.ui.main.data.entity.request;

import com.google.gson.annotations.SerializedName;

public class RequestDeleteGoalDto {
    @SerializedName("g_id")
    private String g_id;

    @SerializedName("u_id")
    private String u_id;

    @SerializedName("comment")
    private String comment;

    public RequestDeleteGoalDto(String g_id, String u_id, String comment) {
        this.g_id = g_id;
        this.u_id = u_id;
        this.comment = comment;
    }
}
