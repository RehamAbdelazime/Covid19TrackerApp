package com.reham.covid19tracker.data;

import com.reham.covid19tracker.pojo.CountryModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ICountry
{
    @GET("countries")
    Call<List<CountryModel>> getCountries();
}
