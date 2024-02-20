package de.bcxp.challenge;

import java.util.List;

public class WeatherAPI {

    private IWeatherService service;
    private IFileParser parser;

    public WeatherAPI() {
        this.service = new WeatherService();
        this.parser = new CSVFileParser();
    }

    public void parseDataToDB() {
        String filePath = "src/main/resources/de/bcxp/challenge/weather.csv";
        char separator = ',';
        List<String[]> parsedData = parser.parseFile(filePath, separator);
        service.extractDataToDB(parsedData);
    }

    public String getDayWithSmallestTempSpread() {
        String day = service.getDayWithSmallestTempSpread();
        service.closeConnection();
        return day;
    }
}
