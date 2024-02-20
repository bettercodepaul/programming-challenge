package de.bcxp.challenge;

import java.util.List;

public class CountriesAPI {

    private ICountriesService service;
    private IFileParser parser;

    public CountriesAPI() {
        this.service = new CountriesService();
        this.parser = new CSVFileParser();
    }

    public void parseDataToDB() {
        String filePath = "src/main/resources/de/bcxp/challenge/countries.csv";
        char separator = ';';
        List<String[]> parsedData = parser.parseFile(filePath, separator);
        service.extractDataToDB(parsedData);
    }

    public String getCountryWithHighestPopulationDensity() {
        String name = service.getCountryWithHighestPopulationDensity();
        service.closeConnection();
        return name;
    }
}
