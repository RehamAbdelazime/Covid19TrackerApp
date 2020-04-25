package com.reham.covid19tracker.ui.country;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.reham.covid19tracker.R;

public class CountryFragment extends Fragment {

    private CountryViewModel countryViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        countryViewModel =
                ViewModelProviders.of(this).get(CountryViewModel.class);
        View root = inflater.inflate(R.layout.fragment_country, container, false);
        final TextView textView = root.findViewById(R.id.text_dashboard);
        countryViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}
