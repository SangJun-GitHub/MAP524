package com.example.weatherview;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class WeatherDetails extends Fragment {

    public WeatherDetails() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(
            LayoutInflater inflater,
            ViewGroup container,
            Bundle savedInstanceState
    ) {

        WeatherData data = getArguments().getParcelable(MainActivity.WEATHER_KEY);

        View view = inflater.inflate(R.layout.fragment_weather_details, container, false);

        TextView cityTextView = view.findViewById(R.id.cityTextView);
        TextView currentTemperatureTextView = view.findViewById(R.id.currentTemperatureTextView);
        TextView highTemperatureTextView = view.findViewById(R.id.highTemperatureTextView);
        TextView lowTemperatureTextView = view.findViewById(R.id.lowTemperatureTextView);
        TextView descriptionTextView = view.findViewById(R.id.weatherDescriptionTextView);
        TextView tomorrowHighTemperatureTextView = view.findViewById(R.id.tomorrowHighTemperatureTextView);
        TextView tomorrowLowTemperatureTextView = view.findViewById(R.id.tomorrowLowTemperatureTextView);
        TextView tomorrowDescriptionTextView = view.findViewById(R.id.tomorrowWeatherDescriptionTextView);

        if( data != null ) {
            cityTextView.setText(data.getCity());

            currentTemperatureTextView.setText(
                    String.format(
                            getResources().getString(R.string.temperature_celsius),
                            data.getCurrentTemperature()
                    )
            );

            highTemperatureTextView.setText(
                    String.format(
                            getResources().getString(R.string.temperature_celsius),
                            data.getHighTemperature()
                    )
            );

            lowTemperatureTextView.setText(
                    String.format(
                            getResources().getString(R.string.temperature_celsius),
                            data.getLowTemperature()
                    )
            );

            descriptionTextView.setText(data.getDescription());

            tomorrowHighTemperatureTextView.setText(String.format(
                    getResources().getString(R.string.temperature_celsius),
                    data.getTomorrowHighTemperature()
            ));

            tomorrowLowTemperatureTextView.setText(String.format(
                    getResources().getString(R.string.temperature_celsius),
                    data.getTomorrowLowTemperature()
            ));

            tomorrowDescriptionTextView.setText(data.getTomorrowDescription());

        }

        return view;
    }
}
