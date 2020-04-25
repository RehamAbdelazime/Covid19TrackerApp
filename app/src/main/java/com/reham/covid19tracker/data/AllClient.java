package com.reham.covid19tracker.data;

import com.reham.covid19tracker.pojo.AllModel;

import retrofit2.Call;

public class AllClient
{
    private IAll allInterface;
    private static AllClient INSTANCE;

    public AllClient()
    {
        allInterface = RetrofitClient.getInstance().create(IAll.class);
    }
    public static AllClient getInstance()
    {
        if (null == INSTANCE)
        {
            INSTANCE = new AllClient();
        }
        return INSTANCE;
    }
    public Call<AllModel> getAll()
    {
        return allInterface.getAllData();
    }
}
