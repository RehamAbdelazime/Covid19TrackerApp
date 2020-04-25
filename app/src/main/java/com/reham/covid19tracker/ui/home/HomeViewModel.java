package com.reham.covid19tracker.ui.home;

import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.reham.covid19tracker.data.AllClient;
import com.reham.covid19tracker.pojo.AllModel;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeViewModel extends ViewModel {

    MutableLiveData<AllModel> allData = new MutableLiveData<>();

    public HomeViewModel() {
    }
    public void getAllData()
    {
        AllClient.getInstance().getAll().enqueue(new Callback<AllModel>() {
            @Override
            public void onResponse(Call<AllModel> call, Response<AllModel> response) {
                String timeStamp = new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime());
                response.body().setLastUpdated(timeStamp);
                allData.setValue(response.body());
            }
            @Override
            public void onFailure(Call<AllModel> call, Throwable t)
            {
                Log.e("VM", t.getMessage());
            }
        });
    }
}