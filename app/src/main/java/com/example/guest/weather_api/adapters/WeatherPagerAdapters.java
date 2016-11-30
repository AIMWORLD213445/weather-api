package com.example.guest.weather_api.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import layout.WeatherDetailFragment;
import com.example.guest.weather_api.R;
import com.example.guest.weather_api.Weather;


import java.util.ArrayList;

/**
 * Created by Guest on 11/30/16.
 */
public class WeatherPagerAdapters extends FragmentPagerAdapter {
    private ArrayList<Weather> mWeather;

    public WeatherPagerAdapters(FragmentManager fm, ArrayList<Weather> weathers) {
        super(fm);
        mWeather = weathers;
    }

    @Override
    public Fragment getItem(int position) {
        return WeatherDetailFragment.newInstance(mWeather.get(position));
    }

    @Override
    public int getCount() {
        return mWeather.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mWeather.get(position).getTime();
    }
}
