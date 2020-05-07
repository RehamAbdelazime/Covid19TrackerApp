package com.reham.covid19tracker.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import com.reham.covid19tracker.R;
import com.reham.covid19tracker.databinding.FragmentHomeBinding;
import com.reham.covid19tracker.pojo.AllModel;
import com.reham.covid19tracker.view_model.home.HomeViewModel;

public class HomeFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    public static final String TAG = "HOME_FRAGMENT";
    private HomeViewModel homeViewModel;
    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState)
    {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false);

        View root = binding.getRoot();

        homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        homeViewModel.getAllData();

        binding.setViewModel(homeViewModel);
        binding.setLifecycleOwner(this);

        binding.swipeRefreshLayout.setOnRefreshListener(this);

        homeViewModel.toastMessageObserver.observe(getViewLifecycleOwner(), new Observer<String>()
        {
            @Override
            public void onChanged(String s) {
                Toast.makeText(getContext(), s, Toast.LENGTH_SHORT).show();
            }
        });

        return root;
    }

    @Override
    public void onRefresh() {
        homeViewModel.getAllData();
        binding.swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if(savedInstanceState != null)
        {
//TODO
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
//TODO
    }
}
