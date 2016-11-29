package com.example.guest.weather_api;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = MainActivity.class.getSimpleName();
    @Bind(R.id.weather) ListView mListView;
    public ArrayList<Weather> mWeathers = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        final WeatherService weatherservice = new WeatherService();


        weatherservice.findWeather("London", new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    String jsonData = response.body().string();
                    Log.d(TAG, jsonData);
                    mWeathers = weatherservice.processResults(jsonData);

                    MainActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            String[] DayData = new String[mWeathers.size()];
                            for (int i = 0; i < DayData.length; i++) {
                                DayData[i] = mWeathers.get(i).getDay();
                            }
                            ArrayAdapter adapter = new ArrayAdapter(MainActivity.this,
                                    android.R.layout.simple_list_item_1, DayData);
                            mListView.setAdapter(adapter);
                        }
                    });
                }
            }
        });


    }
}
