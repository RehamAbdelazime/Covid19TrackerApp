package com.reham.covid19tracker.ui.country;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.reham.covid19tracker.R;
import com.reham.covid19tracker.databinding.CountryItemBinding;
import com.reham.covid19tracker.pojo.CountryModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class CountryAdapter extends RecyclerView.Adapter<CountryAdapter.CountryViewHolder> implements Filterable
{
    private List<CountryModel> countries = new ArrayList<>();
    private List<CountryModel> allCountries;
    private CountryItemBinding binding;

    public CountryAdapter() {

    }

    void setCountries(List<CountryModel> countries) {
        this.countries = countries;
        allCountries = new ArrayList<>(countries);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CountryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.country_item, parent, false);
        return new CountryAdapter.CountryViewHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull CountryViewHolder holder, int position) {
        binding.setViewModel(countries.get(position));

        Picasso.get().load(countries.get(position).getCountryInfo().getFlag())
                .placeholder(R.drawable.ic_broken_image)
                .error(R.drawable.ic_broken_image)
                .into(holder.img_countryFlag);

    }

    @Override
    public int getItemCount() {
        return countries.size();
    }

    @Override
    public Filter getFilter() {
        return countryFilter;
    }
    private Filter countryFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();
            List<CountryModel> filteredCountries = new ArrayList<>();
            if (constraint == null || constraint.length() == 0)
            {
                filteredCountries.addAll(allCountries);
            }else
            {
                String filterPattern = constraint.toString().toLowerCase().trim();
                for (CountryModel country : allCountries )
                {
                    if (country.getCountry().toLowerCase().startsWith(filterPattern))
                    {
                        filteredCountries.add(country);
                    }
                }

            }
            results.values = filteredCountries;
            results.count = filteredCountries.size();
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            countries.clear();
            countries.addAll((List)results.values);
            notifyDataSetChanged();
        }

        @Override
        public CharSequence convertResultToString(Object resultValue) {
            return ((CountryModel) resultValue).getCountry();

        }
    };

    public class CountryViewHolder extends RecyclerView.ViewHolder
    {
        ImageView img_countryFlag;

        public CountryViewHolder(@NonNull View itemView) {
            super(itemView);
            img_countryFlag = itemView.findViewById(R.id.img_countryFlag);
        }
    }
}
