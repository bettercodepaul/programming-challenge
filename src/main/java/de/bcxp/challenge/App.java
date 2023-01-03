package de.bcxp.challenge;

import de.bcxp.challenge.weather.data.DataSource;
import de.bcxp.challenge.weather.data.WeatherDataSourceCSV;
import de.bcxp.challenge.weather.data.WeatherRecord;
import de.bcxp.challenge.weather.WeatherStatsApp;
import de.bcxp.challenge.weather.ui.WeatherUi;
import de.bcxp.challenge.weather.ui.WeatherUiCli;

import java.io.FileNotFoundException;
import java.nio.file.Path;


public final class App {

    private static final int EXIT_ERROR_DATA_WEATHER = 1;

    /**
     * The app's main entry point assembles all dependencies and injects them into the core modules.
     * @param args CLI arguments, currently not used
     */
    public static void main(String... args) {

        Path csvWeather = Path.of("./src/main/resources/de/bcxp/challenge/weather.csv");
        DataSource<WeatherRecord> weatherDataSource = tryInitWeatherDataSource(csvWeather);
        WeatherUi weatherUi = new WeatherUiCli();

        WeatherStatsApp weatherStatsApp = new WeatherStatsApp(weatherDataSource, weatherUi);
        weatherStatsApp.run();

        String countryWithHighestPopulationDensity = "Some country"; // Your population density analysis function call …
        System.out.printf("Country with highest population density: %s%n", countryWithHighestPopulationDensity);
    }

    private static DataSource<WeatherRecord> tryInitWeatherDataSource(Path csvPath) {

        try {
            return new WeatherDataSourceCSV(csvPath);
        } catch (FileNotFoundException e) {
            System.out.printf("Error: CSV file for weather data not found: %s%n", csvPath);
            System.exit(EXIT_ERROR_DATA_WEATHER);
        }

        return null;
    }

}
