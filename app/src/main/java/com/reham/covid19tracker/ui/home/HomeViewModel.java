package com.reham.covid19tracker.ui.home;

import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

import com.reham.covid19tracker.data.AllClient;
import com.reham.covid19tracker.pojo.AllModel;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeViewModel extends ViewModel {

    MutableLiveData<AllModel> allData = new MutableLiveData<>();
    MutableLiveData<String> toastMessageObserver = new MutableLiveData();

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