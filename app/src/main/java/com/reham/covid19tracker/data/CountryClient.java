package com.reham.covid19tracker.data;

import com.reham.covid19tracker.pojo.AllModel;
import com.reham.covid19tracker.pojo.CountryModel;

import java.util.List;

import retrofit2.Call;

public class CountryClient
{
    private ICountry iCountry;
    private static CountryClient INSTANCE;

    public CountryClient()
    {
        iCountry = RetrofitClient.getInstance().create(ICountry.class);
    }
    public static CountryClient getInstance()
    {
        if (null == INSTANCE)
        {
            INSTANCE = new CountryClient();
        }
        return INSTANCE;
    }
    public Call<List<CountryModel>> getCountries()
    {
        return iCountry.getCountries();
    }
}
