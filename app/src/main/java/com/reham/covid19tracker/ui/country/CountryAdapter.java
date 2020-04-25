package com.reham.covid19tracker.ui.country;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.reham.covid19tracker.R;
import com.reham.covid19tracker.pojo.CountryModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class CountryAdapter extends RecyclerView.Adapter<CountryAdapter.CountryViewHolder>
{
    private List<CountryModel> countries = new ArrayList<>();

    public CountryAdapter() { }

    public void setCountries(List<CountryModel> countries) {
        this.countries = countries;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CountryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.country_item, parent, false);
        return new CountryAdapter.CountryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CountryViewHolder holder, int position) {
        holder.tv_countryName.setText(countries.get(position).getCountry());
        holder.tv_countryCases.setText(countries.get(position).getCases());
        holder.tv_countryRecovered.setText(countries.get(position).getRecovered());
        holder.tv_countryDeaths.setText(countries.get(position).getDeaths());
        Picasso.get().load(countries.get(position).getCountryInfo().getFlag()).into(holder.img_countryFlag);

    }

    @Override
    public int getItemCount() {
        return countries.size();
    }

    public class CountryViewHolder extends RecyclerView.ViewHolder
    {
        TextView tv_countryName, tv_countryCases, tv_countryRecovered, tv_countryDeaths;
        ImageView img_countryFlag;

        public CountryViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_countryName = itemView.findViewById(R.id.tv_countryName);
            tv_countryCases = itemView.findViewById(R.id.tv_countryCases);
            tv_countryRecovered = itemView.findViewById(R.id.tv_countryRecovered);
            tv_countryDeaths = itemView.findViewById(R.id.tv_countryDeaths);
            img_countryFlag = itemView.findViewById(R.id.img_countryFlag);
        }
    }
}
