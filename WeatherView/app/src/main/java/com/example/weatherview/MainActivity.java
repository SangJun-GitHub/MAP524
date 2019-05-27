package com.example.weatherview;

import android.app.FragmentTransaction;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements OnWeatherRequestCompleted {

    public static final String WEATHER_KEY = "WEATHER_DATA";
    public static final String LOG_KEY = "WeatherViewer";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final AppDataManager appDataManager = new AppDataManager(this);
        final ImageButton searchButton = findViewById(R.id.searchButton);

        searchButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                EditText searchText = findViewById(R.id.searchText);
                String city = searchText.getText().toString();
                appDataManager.saveCity(city);

                WeatherRequest task = new WeatherRequest(MainActivity.this, getApplicationContext());
                task.execute(city);
            }
        });

        String city = appDataManager.getCity();

        if(city != null && !city.isEmpty()) {
            WeatherRequest task = new WeatherRequest(MainActivity.this, getApplicationContext());
            task.execute(city);
        }
    }

    @Override
    public void onTaskCompleted(WeatherData data) {
        // Show the WeatherData to the user

        Bundle args = new Bundle();
        args.putParcelable(WEATHER_KEY, data);

        WeatherDetails weatherDetails = new WeatherDetails();
        weatherDetails.setArguments(args);
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();

        fragmentTransaction.replace(R.id.weatherFragment, weatherDetails).commit();
    }

    public static class WeatherRequest extends AsyncTask<String, Void, WeatherData> {

        private OnWeatherRequestCompleted listener;
        private ApiException e;
        private Context context;

        public WeatherRequest(
                OnWeatherRequestCompleted listener,
                Context context
        ) {
            this.listener = listener;
            this.context = context;
        }

        @Override
        protected WeatherData doInBackground(String... params) {

            String city = params[0];
            WeatherData data = null;

            try {
                YahooApiManager apiManager = new YahooApiManager(city);
                data = apiManager.getWeather();
            } catch(ApiException e) {
                Log.e(LOG_KEY, e.getClass().getName() + ": " + e.getMessage());
                this.e = e;
            }

            return data;
        }

        @Override
        protected void onPostExecute(WeatherData data) {

            if(this.e != null) {

                String text = context.getResources().getString(R.string.search_error);
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
                return;
            }

            listener.onTaskCompleted(data);
        }
    }
}