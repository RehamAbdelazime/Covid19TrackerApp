package com.reham.covid19tracker.view_model.home;

import android.util.Log;


import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.reham.covid19tracker.data.AllClient;
import com.reham.covid19tracker.pojo.AllModel;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeViewModel extends ViewModel {

    public MutableLiveData<AllModel> allData = new MutableLiveData<>();
    public MutableLiveData<String> toastMessageObserver = new MutableLiveData<String>();

    public HomeViewModel() {
    }
    public void getAllData()
    {
        AllClient.getInstance().getAll().enqueue(new Callback<AllModel>() {
            @Override
            public void onResponse(Call<AllModel> call, Response<AllModel> response) {
                if (response.isSuccessful()) {
                    String timeStamp = new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime());
                    response.body().setLastUpdated(timeStamp);
                    allData.setValue(response.body());
                } else
                {
                    try {
                        toastMessageObserver.setValue(response.errorBody().string());
                    } catch (IOException e) {
                        Log.e("mm", e.getLocalizedMessage());
                        e.printStackTrace();
                    }
                }

            }
            @Override
            public void onFailure(Call<AllModel> call, Throwable t)
            {
                Log.e("VM", t.getMessage());
                toastMessageObserver.setValue(t.getMessage());
            }
        });
    }
}