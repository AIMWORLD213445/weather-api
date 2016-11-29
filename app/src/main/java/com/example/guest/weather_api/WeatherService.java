package com.example.guest.weather_api;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Guest on 11/29/16.
 */
public class WeatherService {
    public static void findWeather(String location, Callback callback) {
        OkHttpClient weather = new OkHttpClient();
        HttpUrl.Builder urlBuilder = HttpUrl.parse(Constants.weather_url).newBuilder();
        urlBuilder.addQueryParameter(Constants.appid, Constants.weather_API);
        urlBuilder.addQueryParameter(Constants.weather_location, location);
        String url = urlBuilder.build().toString();

        Request request = new Request.Builder()
                .url(url).build();

        Call call = weather.newCall(request);

        call.enqueue(callback);
    }

    public ArrayList<Weather> processResults (String Data) {
        ArrayList<Weather> weathers = new ArrayList<>();
        try {
                String jsonData = Data;
                JSONObject weatherJSON = new JSONObject(jsonData);
                JSONArray list = weatherJSON.getJSONArray("list");
                for (int i = 0; i < list.length(); i++) {
                    JSONObject listJSON = list.getJSONObject(i);
                    String pressure = listJSON.getString("pressure");
                    String humidity = listJSON.getString("humidity");
                    String day = listJSON.getJSONObject("temp").getString("day");
                    String min = listJSON.getJSONObject("temp").getString("min");
                    String max = listJSON.getJSONObject("temp").getString("max");
                    String night = listJSON.getJSONObject("temp").getString("night");
                    String eve = listJSON.getJSONObject("temp").getString("eve");
                    String morn = listJSON.getJSONObject("temp").getString("morn");
                    JSONArray weatherData =  listJSON.getJSONArray("weather");
                    JSONObject data = weatherData.getJSONObject(0);
                    String main = data.getString("main");
                    String description = data.getString("description");
                    String speed = listJSON.getString("speed");
                    String deg = listJSON.getString("deg");
                    String clouds = listJSON.getString("clouds");
                    String rain = "0";
                    if (listJSON.isNull("rain")) {
                        rain = "0";
                    } else {
                        rain = listJSON.getString("rain");
                    }

                    Weather weather = new Weather(day, min, max, night, eve, morn, pressure, humidity, main, description, deg, speed, clouds, rain);
                    weathers.add(weather);
                }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return weathers;
    }

}
