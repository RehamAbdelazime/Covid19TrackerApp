package com.reham.covid19tracker.data;

import com.reham.covid19tracker.pojo.AllModel;

import retrofit2.Call;
import retrofit2.http.GET;

public interface IAll {
    @GET("all")
    public Call<AllModel> getAllData();
}
