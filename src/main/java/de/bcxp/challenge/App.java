package de.bcxp.challenge;

import java.io.FileNotFoundException;
import java.sql.SQLException;

/**
 * The entry class for your solution. This class is only aimed as starting point and not intended as baseline for your software
 * design. Read: create your own classes and packages as appropriate.
 */
public final class App {

    /**
     * This is the main entry method of your program.
     *
     * @param args The CLI arguments passed
     */
    public static void main(String... args) {

        WeatherAPI weatherAPI = new WeatherAPI();
        weatherAPI.parseDataToDB();
        String dayWithSmallestTempSpread = weatherAPI.getDayWithSmallestTempSpread();     // Your day analysis function call …
        System.out.printf("Day with smallest temperature spread: %s%n", dayWithSmallestTempSpread);

        CountriesAPI countriesAPI = new CountriesAPI();
        countriesAPI.parseDataToDB();
        String countryWithHighestPopulationDensity = countriesAPI.getCountryWithHighestPopulationDensity(); // Your population density analysis function call …
        System.out.printf("Country with highest population density: %s%n", countryWithHighestPopulationDensity);
    }
}
