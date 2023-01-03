package de.bcxp.challenge.weather;

import de.bcxp.challenge.weather.data.DataSource;
import de.bcxp.challenge.weather.data.WeatherRecord;
import de.bcxp.challenge.weather.stats.WeatherStats;
import de.bcxp.challenge.weather.ui.WeatherUi;


public class WeatherStatsApp {

    private final DataSource<WeatherRecord> dataSource;
    private final WeatherUi weatherUi;

    public WeatherStatsApp(DataSource<WeatherRecord> dataSource, WeatherUi weatherUi) {
        this.dataSource = dataSource;
        this.weatherUi = weatherUi;
    }

    /**
     * Core business logic: Read data, calculate stats, display them.
     */
    public void run() {

        try {

            Iterable<WeatherRecord> data = this.dataSource.getData();
            WeatherRecord minSpreadRecord = WeatherStats.findMinTemperatureSpread(data);
            this.weatherUi.showMinTempSpread(minSpreadRecord);

        } catch (Exception e) {
            // There's not much error handling going on here. But this block is a good way to indicate that high-level
            // error handling is part of the business logic and should probably go here.
            System.out.printf("Error while calculating weather stats:%n%s%n", e);
        }

    }

}
