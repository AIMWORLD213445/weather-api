package com.example.guest.weather_api;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.guest.weather_api.adapters.WeatherListAdapter;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class WeatherActivity extends AppCompatActivity {
    public ArrayList<Weather> mWeathers = new ArrayList<>();
//    @Bind(R.id.listView) ListView mListView;
    @Bind(R.id.textView) TextView mText;
    @Bind(R.id.recyclerView) RecyclerView mRecyclerView;
    private WeatherListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);

        final WeatherService weatherservice = new WeatherService();
        ButterKnife.bind(this);
        Intent myIntent = getIntent();
        String place = myIntent.getStringExtra("place");

        weatherservice.findWeather(place, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    String jsonData = response.body().string();
                    mWeathers = weatherservice.processResults(jsonData);

                    WeatherActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
//                            String[] DayData = new String[mWeathers.size()];
//                            mText.setText(mWeathers.get(0).getName() + ", " + mWeathers.get(0).getCountry());
//                            for (int i = 0; i < DayData.length; i++) {
//                                DayData[i] = mWeathers.get(i).getTime() + "\n\n";
//                                DayData[i] += "The High will be " + mWeathers.get(i).getMax() + " F";
//                                DayData[i] += "\nThe AVG will be " + mWeathers.get(i).getDay()+ " F";
//                                DayData[i] += "\nThe Low will be " + mWeathers.get(i).getMin()+ " F";
//                                DayData[i] += "\n\n";
//                                DayData[i] += "The Pressure will be " + mWeathers.get(i).getPressure() + " ft above sea level";
//                                DayData[i] += "\nThe humidity will be " + mWeathers.get(i).getHumidity() + "%";
//                                DayData[i] += "\n\n";
//                                DayData[i] += mWeathers.get(i).getDescription();
//                                DayData[i] += "\n\n";
//                                DayData[i] += "Speed of the wind will be " + mWeathers.get(i).getSpeed() + " miles per hour";
//                                DayData[i] += "\nClouds in the sky will be " + mWeathers.get(i).getClouds() + "%";
//                            }
//
//                            ArrayAdapter adapter = new ArrayAdapter(WeatherActivity.this,
//                                    android.R.layout.simple_list_item_1, DayData);
//                            mListView.setAdapter(adapter);
                            mText.setText(mWeathers.get(0).getName() + ", " + mWeathers.get(0).getCountry());
                            mAdapter = new WeatherListAdapter(getApplicationContext(), mWeathers);
                            mRecyclerView.setAdapter(mAdapter);
                            RecyclerView.LayoutManager layoutManager =
                                    new LinearLayoutManager(WeatherActivity.this);
                            mRecyclerView.setLayoutManager(layoutManager);
                            mRecyclerView.setHasFixedSize(true);
                        }
                    });
                }
            }
        });

    }
}
