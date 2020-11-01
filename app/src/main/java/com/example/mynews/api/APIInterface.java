package com.example.mynews.api;

import com.example.mynews.models.ResponseModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
public interface APIInterface {
    @GET("everything")
    Call<ResponseModel> getLatestNews(@Query("sources") String source, @Query("apiKey") String apiKey);
}
