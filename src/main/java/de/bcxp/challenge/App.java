package de.bcxp.challenge;

import de.bcxp.challenge.data.DataSource;
import de.bcxp.challenge.data.weather.WeatherDataSourceCSV;
import de.bcxp.challenge.data.weather.WeatherRecord;

import java.io.FileNotFoundException;
import java.nio.file.Path;


public final class App {

    private static final int EXIT_ERROR_DATA_WEATHER = 1;

    /**
     * This is the main entry method of your program.
     * @param args The CLI arguments passed
     */
    public static void main(String... args) {

        DataSource<WeatherRecord> weatherDataSource = tryInitWeatherDataSource(
                Path.of("./src/main/resources/de/bcxp/challenge/weather.csv")
        );

        int dayWithSmallestTempSpread = -1;
        System.out.printf("Day with smallest temperature spread: %s%n", dayWithSmallestTempSpread);

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
