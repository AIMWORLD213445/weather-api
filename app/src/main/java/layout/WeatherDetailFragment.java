package layout;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.guest.weather_api.R;
import com.example.guest.weather_api.Weather;
import com.example.guest.weather_api.adapters.FontManager;

import org.parceler.Parcels;

import butterknife.Bind;
import butterknife.ButterKnife;

public class WeatherDetailFragment extends Fragment {
    @Bind(R.id.DateTitle) TextView mDateTile;
    @Bind(R.id.WeatherInfo) TextView mWeatherInfo;
    @Bind(R.id.WeatherData) TextView mWeatherData;
    Context context;

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

        int id = 0;

        if (mWeather.getMain().equals("Clear")) {
            id = R.string.wi_forecast_io_clear_day;
        } else if (mWeather.getMain().equals("Rain")) {
            id = R.string.wi_day_rain;
        }
        else if (mWeather.getMain().equals("Storm")) {
            id = R.string.wi_day_lightning;
        }
        else if (mWeather.getMain().equals("Snow")) {
            id = R.string.wi_day_snow;
        } else {
            id = R.string.wi_volcano;
        }

        mWeatherInfo.setTypeface(FontManager.getTypeface(getActivity(), "weathericons.ttf"));

        mWeatherInfo.setText(FontManager.setIcon(getActivity(),id));

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
