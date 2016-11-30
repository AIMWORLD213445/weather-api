package com.example.guest.weather_api.adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Parcel;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.guest.weather_api.R;
import com.example.guest.weather_api.Weather;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

import layout.WeatherDetailFragment;

import com.example.guest.weather_api.WeatherDetailActivity;


import org.parceler.Parcels;

import java.util.ArrayList;

/**
 * Created by Guest on 11/29/16.
 */
public class WeatherListAdapter extends RecyclerView.Adapter<WeatherListAdapter.WeatherViewHolder> {
    private ArrayList<Weather> mWeathers = new ArrayList<>();
    private Context mContext;

    public WeatherListAdapter(Context context, ArrayList<Weather> weathers) {
        mContext = context;
        mWeathers = weathers;
    }


    @Override
    public WeatherListAdapter.WeatherViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.weather_item, parent, false);
        WeatherViewHolder viewHolder = new WeatherViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(WeatherListAdapter.WeatherViewHolder holder, int position) {
        holder.bindWeather(mWeathers.get(position));
    }

    @Override
    public int getItemCount() {
        return mWeathers.size();
    }

    public class WeatherViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @Bind(R.id.weatherTime) TextView mweatherTime;

        private Context mContext;

        public WeatherViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            mContext = itemView.getContext();
            itemView.setOnClickListener(this);
        }

        public void bindWeather(Weather weather) {
            mweatherTime.setText(weather.getTime());
        }

        @Override
        public void onClick(View view) {
            Log.d("click listener", "working!");
            int itemPosition = getLayoutPosition();
            Intent intent = new Intent(mContext, WeatherDetailActivity.class);
            intent.putExtra("position", itemPosition + "");
            intent.putExtra("weather", Parcels.wrap(mWeathers));
            mContext.startActivity(intent);
       }
    }
}
