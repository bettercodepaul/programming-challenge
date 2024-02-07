package de.bcxp.challenge.task1;

import org.apache.commons.csv.CSVRecord;
import java.util.List;

/**
 * Task1: Analyze weather
 */
public class WeatherAnalyzerTaskOne {

    /**
     * Analyzes the weather data to find the day with the smallest temperature spread.
     *
     * @param records List of CSVRecord objects representing the weather data.
     * @return The day number with the smallest temperature spread.
     */
    public String findDayWithSmallestTempSpread(List<CSVRecord> records) {
        int smallestSpread = Integer.MAX_VALUE;
        String dayWithSmallestSpread = "";

        for (CSVRecord record : records) {
            int maxTemp = Integer.parseInt(record.get("MxT").trim());
            int minTemp = Integer.parseInt(record.get("MnT").trim());
            int spread = maxTemp - minTemp;

            if (spread < smallestSpread) {
                smallestSpread = spread;
                dayWithSmallestSpread = record.get("Day").trim();
            }
        }

        return dayWithSmallestSpread;
    }
}
