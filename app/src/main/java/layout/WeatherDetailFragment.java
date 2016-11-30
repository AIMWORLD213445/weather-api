package layout;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.guest.weather_api.R;
import com.example.guest.weather_api.Weather;

import org.parceler.Parcels;

import butterknife.Bind;
import butterknife.ButterKnife;

public class WeatherDetailFragment extends Fragment {
    @Bind(R.id.DateTitle) TextView mDateTile;
    @Bind(R.id.WeatherInfo) TextView mWeatherInfo;
    @Bind(R.id.WeatherData) TextView mWeatherData;

    private Weather mWeather;

    public WeatherDetailFragment() {
        // Required empty public constructor
    }

    public static WeatherDetailFragment newInstance(Weather weather) {
        WeatherDetailFragment mWeatherListener = new WeatherDetailFragment();
        Bundle args = new Bundle();
        args.putParcelable("weather", Parcels.wrap(weather));
        mWeatherListener.setArguments(args);
        return mWeatherListener;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mWeather = Parcels.unwrap(getArguments().getParcelable("weather"));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_weather_detail, container, false);
        ButterKnife.bind(this, view);
        mDateTile.setText(mWeather.getTime());
        mWeatherInfo.setText(mWeather.getDescription());
        String DayData = "";
        DayData += "The High will be " + mWeather.getMax() + " F";
        DayData += "\nThe AVG will be " + mWeather.getDay()+ " F";
        DayData += "\nThe Low will be " + mWeather.getMin()+ " F";
        DayData += "\n\n";
        DayData += "The Pressure will be " + mWeather.getPressure() + " ft above sea level";
        DayData += "\nThe humidity will be " + mWeather.getHumidity() + "%";
        DayData += "\n\n";
        DayData += "Speed of the wind will be " + mWeather.getSpeed() + " miles per hour";
        DayData += "\nClouds in the sky will be " + mWeather.getClouds() + "%";
        mWeatherData.setText(DayData);

        return view;
    }

}
