package de.bcxp.challenge;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.List;

public class CountriesService implements ICountriesService {

    ICountriesRepository repository;

    public CountriesService() {
        this.repository = new CountriesRepository();
        this.repository.createDB();
    }

    @Override
    public String getCountryWithHighestPopulationDensity() {
        return repository.selectCountryWithHighestPopulationDensity();
    }

    @Override
    public void extractDataToDB(List<String[]> data) {

        String name;
        int population;
        float area;
        float density;

        // read data line by line
        for(String[] nextRecord : data) {
            name = nextRecord[0].replaceAll("\\s","");
            try {
                population = (int) (long) NumberFormat.getIntegerInstance().parse(nextRecord[3]);
                area = Float.parseFloat(nextRecord[4]);
                density = population / area;
                repository.insertRow(name, population, area, density);
            } catch (ParseException e) {
                e.getMessage();
            }
        }
    }

    @Override
    public void closeConnection() {
        repository.closeConnection();
    }
}
