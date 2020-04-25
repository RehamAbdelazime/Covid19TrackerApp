package com.reham.covid19tracker.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.reham.covid19tracker.R;
import com.reham.covid19tracker.pojo.AllModel;

public class HomeFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    private HomeViewModel homeViewModel;
    private SwipeRefreshLayout swipeRefreshLayout;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        homeViewModel.getAllData();

        final TextView tv_ConfirmedNo = root.findViewById(R.id.tv_ConfirmedNo);
        final TextView tv_PositiveNo = root.findViewById(R.id.tv_PositiveNo);
        final TextView tv_RecoveredNo = root.findViewById(R.id.tv_RecoveredNo);
        final TextView tv_DeathsNo = root.findViewById(R.id.tv_DeathsNo);
        final TextView tv_todayCases = root.findViewById(R.id.tv_todayCases);
        final TextView tv_todayDeaths = root.findViewById(R.id.tv_todayDeaths);
        final TextView tv_criticalCases = root.findViewById(R.id.tv_criticalCases);
        final TextView tv_casesPerOneMillion = root.findViewById(R.id.tv_casesPerOneMillion);
        final TextView tv_deathsPerOneMillion = root.findViewById(R.id.tv_deathsPerOneMillion);
        final TextView tv_affectedCountries = root.findViewById(R.id.tv_affectedCountries);
        final TextView tv_lastUpdated = root.findViewById(R.id.tv_lastUpdated);

        swipeRefreshLayout = root.findViewById(R.id.swipeRefreshLayout);

        swipeRefreshLayout.setOnRefreshListener(this);

        homeViewModel.allData.observe(getViewLifecycleOwner(), new Observer<AllModel>() {
            @Override
            public void onChanged(AllModel allModel) {
                tv_ConfirmedNo.setText(allModel.getCases());
                tv_PositiveNo.setText(allModel.getActive());
                tv_RecoveredNo.setText(allModel.getRecovered());
                tv_DeathsNo.setText(allModel.getDeaths());
                tv_todayCases.setText(allModel.getTodayCases());
                tv_todayDeaths.setText(allModel.getTodayDeaths());
                tv_criticalCases.setText(allModel.getCritical());
                tv_casesPerOneMillion.setText(allModel.getCasesPerOneMillion());
                tv_deathsPerOneMillion.setText(allModel.getDeathsPerOneMillion());
                tv_affectedCountries.setText(allModel.getAffectedCountries());
                tv_lastUpdated.setText(allModel.getLastUpdated());
            }
        });
        return root;
    }

    @Override
    public void onRefresh() {
        homeViewModel.getAllData();
        swipeRefreshLayout.setRefreshing(false);
    }
}
