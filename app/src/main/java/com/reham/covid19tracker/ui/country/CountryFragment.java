package com.reham.covid19tracker.ui.country;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.reham.covid19tracker.R;
import com.reham.covid19tracker.pojo.CountryModel;

import java.util.List;

public class CountryFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    private CountryViewModel countryViewModel;
    private SwipeRefreshLayout swipeRefreshLayout;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        countryViewModel = new ViewModelProvider(this).get(CountryViewModel.class);
        countryViewModel.getAllCountriesData();

        View root = inflater.inflate(R.layout.fragment_country, container, false);
        final RecyclerView recyclerView = root.findViewById(R.id.recyclerView);

        swipeRefreshLayout = root.findViewById(R.id.swipeRefreshLayout);

        swipeRefreshLayout.setOnRefreshListener(this);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        final CountryAdapter  adapter = new CountryAdapter();
        recyclerView.setAdapter(adapter);

        countryViewModel.toastMessageObserver.observe(getViewLifecycleOwner(), new Observer<String>()
        {
            @Override
            public void onChanged(String s) {
                Toast.makeText(getContext(), s, Toast.LENGTH_SHORT).show();
            }
        });
        countryViewModel.countryModelMutableLiveData.observe(getViewLifecycleOwner(), new Observer<List<CountryModel>>() {
            @Override
            public void onChanged(List<CountryModel> countryModels) {
                adapter.setCountries(countryModels);
            }
        });

        return root;
    }

    @Override
    public void onRefresh() {
        countryViewModel.getAllCountriesData();
        swipeRefreshLayout.setRefreshing(false);
    }
}
