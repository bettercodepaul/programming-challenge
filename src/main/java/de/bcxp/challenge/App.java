package de.bcxp.challenge;

import de.bcxp.challenge.task1.WeatherAnalyzerTaskOne;
import de.bcxp.challenge.task2.CountryAnalyzer;
import de.bcxp.challenge.task2.DataAnalyzer;
import de.bcxp.challenge.task2.WeatherAnalyzer;
import org.apache.commons.csv.CSVRecord;

import java.io.IOException;
import java.util.List;

/**
 * The entry class for your solution. This class is only aimed as starting point and not intended as baseline for your software
 * design. Read: create your own classes and packages as appropriate.
 */
public final class App {

    /**
     * This is the main entry method of your program.
     * @param args The CLI arguments passed
     */
    public static void main(String... args) throws IOException {

        // task1
        // Analyzing weather data
        CsvReader weatherReader = new CsvReader("src/main/resources/de/bcxp/challenge/weather.csv", ',');
        List<CSVRecord> weatherRecords = weatherReader.readRecords();
        WeatherAnalyzerTaskOne weatherAnalyzerTaskOne = new WeatherAnalyzerTaskOne();
        System.out.printf("Day with smallest temperature spread: %s%n", weatherAnalyzerTaskOne.findDayWithSmallestTempSpread(weatherRecords));


        // task2
        // Analyzing weather data
        DataAnalyzer weatherAnalyzer = new WeatherAnalyzer("Day", "MxT", "MnT");
        String dayWithSmallestTempSpread = weatherAnalyzer.analyzeData(weatherRecords);
        System.out.printf("Day with smallest temperature spread: %s%n", dayWithSmallestTempSpread);

        // Analyzing country data
        CsvReader countriesReader = new CsvReader("src/main/resources/de/bcxp/challenge/countries.csv", ';');
        List<CSVRecord> countryRecords = countriesReader.readRecords();
        DataAnalyzer countryAnalyzer = new CountryAnalyzer("Name", "Population", "Area (km²)");
        String countryWithHighestPopulationDensity = countryAnalyzer.analyzeData(countryRecords);
        System.out.printf("Country with highest population density: %s%n", countryWithHighestPopulationDensity);
    }
}
