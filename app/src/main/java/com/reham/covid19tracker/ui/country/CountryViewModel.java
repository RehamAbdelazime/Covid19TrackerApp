package com.reham.covid19tracker.ui.country;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.reham.covid19tracker.data.CountryClient;
import com.reham.covid19tracker.pojo.CountryModel;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CountryViewModel extends ViewModel {

    MutableLiveData<List<CountryModel>> countryModelMutableLiveData = new MutableLiveData<>();
    MutableLiveData<String> toastMessageObserver = new MutableLiveData();

    public void getAllCountriesData()
    {
        CountryClient.getInstance().getCountries().enqueue(new Callback<List<CountryModel>>() {
            @Override
            public void onResponse(Call<List<CountryModel>> call, Response<List<CountryModel>> response) {
                if (response.isSuccessful()) {
                    countryModelMutableLiveData.setValue(response.body());
                }else
                {
                    try {
                        toastMessageObserver.setValue(response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<List<CountryModel>> call, Throwable t) {
                Log.e("VM", t.getMessage());
                toastMessageObserver.setValue(t.getMessage());
            }
        });
    }
}