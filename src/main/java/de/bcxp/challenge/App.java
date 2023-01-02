package de.bcxp.challenge;

import de.bcxp.challenge.weather.DataSource;
import de.bcxp.challenge.weather.WeatherDataSourceCSV;
import de.bcxp.challenge.weather.WeatherRecord;
import de.bcxp.challenge.weather.WeatherStats;

import java.io.FileNotFoundException;
import java.nio.file.Path;


public final class App {

    private static final int EXIT_ERROR_DATA_WEATHER = 1;

    /**
     * This is the main entry method of your program.
     * @param args The CLI arguments passed
     */
    public static void main(String... args) {

        Iterable<WeatherRecord> weatherData = tryInitWeatherDataSource(
                Path.of("./src/main/resources/de/bcxp/challenge/weather.csv")
        ).getData();

        WeatherRecord recordWithSmallestTempSpread = WeatherStats.findMinTemperatureSpread(weatherData);
        System.out.printf("Day with smallest temperature spread: %s%n", recordWithSmallestTempSpread.getDay());

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
