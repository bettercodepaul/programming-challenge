package de.bcxp.challenge.task2;

import org.apache.commons.csv.CSVRecord;
import java.util.List;

public class WeatherAnalyzer implements DataAnalyzer {
    private final String dayColumn;
    private final String maxTempColumn;
    private final String minTempColumn;

    public WeatherAnalyzer(String dayColumn, String maxTempColumn, String minTempColumn) {
        this.dayColumn = dayColumn;
        this.maxTempColumn = maxTempColumn;
        this.minTempColumn = minTempColumn;
    }

    @Override
    public String analyzeData(List<CSVRecord> records) {
        int smallestSpread = Integer.MAX_VALUE;
        String dayWithSmallestSpread = "";

        for (CSVRecord record : records) {
            int maxTemp = Integer.parseInt(record.get(maxTempColumn).trim());
            int minTemp = Integer.parseInt(record.get(minTempColumn).trim());
            int spread = maxTemp - minTemp;

            if (spread < smallestSpread) {
                smallestSpread = spread;
                dayWithSmallestSpread = record.get(dayColumn).trim();
            }
        }

        return dayWithSmallestSpread;
    }
}
